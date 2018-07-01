package fr.epita.iam.services.conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 */
public class ConfigurationServiceAsSingleton {

	private final Properties properties;
	private static ConfigurationServiceAsSingleton instance;

	private ConfigurationServiceAsSingleton() throws FileNotFoundException, IOException {
		properties = new Properties();
		properties.load(new FileInputStream(new File(System.getProperty("conf.file.path"))));
	}

	public static ConfigurationServiceAsSingleton getInstance() {
		if (instance == null) {
			try {
				instance = new ConfigurationServiceAsSingleton();
			} catch (final FileNotFoundException e) {
				// FIXME Use a logger to trace the following error
				// LOGGER.error("An error occured", ${exception_var})
			} catch (final IOException e) {
				// FIXME Use a logger to trace the following error
				// LOGGER.error("An error occured", ${exception_var})
			}
		}
		return instance;
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

}
