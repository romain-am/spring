package main.com.dragonsoft.onlineapp.config;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import main.com.dragonsoft.utils.Log4j2XmlCreator;
import main.com.dragonsoft.utils.SocketChecker;

@WebListener
public class ContextListener implements ServletContextListener {
	/**
	 * Launch Elastic Search Instance on port 9200
	 * Initialize log4j2 when the application is being started
	 */

	private static final Logger LOG
	= LogManager.getLogger();

	@Override
	public void contextInitialized(ServletContextEvent event) {
		//Create log4j2 xml and start logging
		Log4j2XmlCreator log = new Log4j2XmlCreator(event);
		log.create();

		//Get properties
		Resource resource = new ClassPathResource("/resources/application.properties");
		Properties props = null;
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e0) {
			// TODO Auto-generated catch block
			LOG.warn("Project missing application.properties in src/resources folder");
			LOG.warn(e0.getMessage());
		}

		//Launch Elastic Search Instance on port 9200
		if(SocketChecker.isReachable("127.0.0.1", 9200, 10000) != true) {
			try {
				String launcherPath = props.getProperty("elasticsearch.launcher");
				Runtime.
				getRuntime().
				exec("cmd /c start \"\" "+launcherPath);

				//Wait for socket response
				while(SocketChecker.isReachable("127.0.0.1", 9200, 10000) != true) {
					LOG.warn("Waiting for ElasticSearch instance to respond on port 9200, can you reach localhost:9200 ? ..");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LOG.error("Can't find ElasticSearch Launcher file , is your application.properties pointing to the good file ?");
			}
		}

		//Launch Logstash
		try {
			String launcherPath = props.getProperty("logstash.launcher");
			String logstashConfFile = props.getProperty("logstash.conf");

			File launcher = new File(launcherPath);
			if(launcher.exists()) {
				Runtime.
				getRuntime().
				exec("cmd /c start \"\" "+launcherPath + " -f " + logstashConfFile);
			}
			else {
				LOG.error("Can't find Logstash Launcher file , is your application.properties pointing to the good file ?");
				LOG.error("Exiting application");
				//Shut down application
				System.exit(1);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOG.error("Error while launching Logstash ..");
			LOG.error(e.getMessage());
			LOG.error("Exiting application");

			//Shut down application
			System.exit(1);
		}
	}

}
