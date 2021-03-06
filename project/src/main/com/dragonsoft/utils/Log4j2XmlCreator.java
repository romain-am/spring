package main.com.dragonsoft.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.api.LayoutComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.RootLoggerComponentBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.apache.logging.log4j.core.config.xml.XmlConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class Log4j2XmlCreator {
	private ServletContextEvent event;

	public Log4j2XmlCreator(ServletContextEvent event) {
		this.event = event;

	}

	public void create() {
		//Get properties
		Resource resource = new ClassPathResource("/resources/application.properties");
		Properties props = null;
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e0) {
			// TODO Auto-generated catch block
			System.out.println("Project missing application.properties in src/resources folder");
			System.out.println(e0.getMessage());
			System.out.println("Exiting application..");
			System.exit(1);
		}

		ServletContext context = event.getServletContext();

		// get FileSystem separator
		FileSystem fileSystem = FileSystems.getDefault();
		String fileSystemSeparator = fileSystem.getSeparator();
		if(fileSystemSeparator.equals("\\")){
			fileSystemSeparator = "\\\\";
		}

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
				props.getProperty("logfile.path").lastIndexOf(fileSystem.getSeparator()) + 1);
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

		//Write xml configuration file
		String xml = builder.toXmlConfiguration();

		//Get project path
		String workspacePath = context.getRealPath("resources/").split(".metadata")[0];

		String projectName = context.getRealPath("resources/").split("wtpwebapps")[1].split(fileSystemSeparator)[1];
		String projectPath = workspacePath  + projectName + fileSystem.getSeparator();

		File projectFile = new File(projectPath);

		//Find src folder and create log4j.xml
		CustomFileUtil fileUtil = new CustomFileUtil();
		fileUtil.findDir(projectFile, "src");
		String srcFolder = fileUtil.getResults();
		String logFilePath = srcFolder + fileSystem.getSeparator() + "log4j2.xml";

		File logFile = new File(logFilePath);
		try {
			logFile.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(logFile));
			writer.write(xml);		     
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Project can't create log4j2.xml in src folder :");
			System.out.println(e1.getMessage());
			System.out.println("You can add it manually :");
			System.out.println("log4j2.xml generated :");
			System.out.println(xml);
		}


		//Tell log4j2 to use our configuration

		//Load the xml configuration
	/*	InputStream inputStream;
		try {
			inputStream = new FileInputStream(logFile);
			LoggerContext.getContext(false)
			.start(new XmlConfiguration(null, new ConfigurationSource(inputStream)));
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			System.out.println("Problem encoutered while loading log4j2.xml :");
			System.out.println(e2.getMessage());
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			System.out.println("Problem encoutered while loading log4j2.xml :");
			System.out.println(e3.getMessage());
		}
		
		*/
	}

}
