package main.com.dragonsoft.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.api.LayoutComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.RootLoggerComponentBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Log4j2XmlCreator {
	private ServletContextEvent event;
	private FileSystemOperations fsOperations;

	public Log4j2XmlCreator(ServletContextEvent event) {
		this.event = event;
		fsOperations = new FileSystemOperations();

	}

	public void generateLogFile(String xml) throws IOException {
		//Get project Path
		File projectPath = fsOperations.getProjectPath();
		
		//Find src folder
		fsOperations.findDirfromRoot(projectPath, "src");
		String logFilefinalPath = fsOperations.getDirectoryFound() + fsOperations.getFileSystemSeparator("dont_escape") + "log4j2.xml";
		
		//Create log4j.xml
		File logFile = new File(logFilefinalPath);
		try {
			logFile.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(logFile));
			writer.write(xml);		     
			writer.close();
		} catch (IOException e1) {
			System.out.println("Project can't create log4j2.xml in src folder :");
			System.out.println(e1.getMessage());
			System.out.println("You can add it manually :");
			System.out.println("log4j2.xml generated :");
			System.out.println(xml);
		}

	}

	public void createAndGenerate() {
		//Get properties
		Resource resource = new ClassPathResource("/resources/application.properties");

		Properties props = fsOperations.getProperties(resource);
		if(props == null) System.exit(1);

		//Create the configuration builder
		ConfigurationBuilder<BuiltConfiguration> builder
		= ConfigurationBuilderFactory.newConfigurationBuilder();

		//Add configuration status (TRACE for development)
		builder.setStatusLevel( Level.TRACE);

		//Configure page layout for appenders
		LayoutComponentBuilder standard
		= builder.newLayout("PatternLayout");
		standard.addAttribute("pattern", "%d[%t]%-5level: %msg%n%throwable");

		//Add console and file appenders to the builder
		AppenderComponentBuilder console
		= builder.newAppender("stdout", "Console").addAttribute("target",
				ConsoleAppender.Target.SYSTEM_OUT);

		console.add(standard);
		builder.add(console);

		AppenderComponentBuilder rollingFile
		= builder.newAppender("rolling", "RollingFile");
		rollingFile.addAttribute("fileName", props.getProperty("logfile.path"));
		String filename = 
				props.getProperty("logfile.path").substring(
						props.getProperty("logfile.path").lastIndexOf(fsOperations.getFileSystemSeparator("dont_escape")) + 1);
		rollingFile.addAttribute("filePattern", props.getProperty("logfile.path").split(filename)[0]+"rolling-%d{MM-dd-yy}.log.gz");
		rollingFile.addAttribute("immediateFlush", false);
		rollingFile.addAttribute("append", true);

		//Configure triggering policy for rolling
		ComponentBuilder triggeringPolicies = builder.newComponent("Policies")
				.addComponent(builder.newComponent("CronTriggeringPolicy")
						.addAttribute("schedule", "0 0 0 **  **  ?"))
				.addComponent(builder.newComponent("SizeBasedTriggeringPolicy")
						.addAttribute("size", "100K"));

		rollingFile.addComponent(triggeringPolicies);

		rollingFile.add(standard);
		builder.add(rollingFile);

		//Configure root logger with stdout and rolling appenders and default error level
		RootLoggerComponentBuilder rootLogger
		= builder.newRootLogger(Level.WARN);
		rootLogger.add(builder.newAppenderRef("stdout"));
		rootLogger.add(builder.newAppenderRef("rolling"));

		builder.add(rootLogger);

		//Add logger with rolling file appender for com package (here not needed)
		/*	LoggerComponentBuilder logger = builder.newLogger("main", Level.INFO);
			logger.add(builder.newAppenderRef("rolling"));
			logger.addAttribute("additivity", true); 

			builder.add(logger); */

		//Get xml configuration file
		String xml = builder.toXmlConfiguration();
		try {
			generateLogFile(xml);
		} catch (IOException e) {
			System.out.println("Could not generate log file : ");
			System.out.println(e.getMessage());
		}
		
	}

}
