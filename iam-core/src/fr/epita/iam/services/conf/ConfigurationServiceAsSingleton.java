package fr.epita.iam.services.conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * <h3>Description</h3>
 * <p>
 * This class is a used to get configuration service singleton instance.
 * </p>
 * 
 * @author Shantanu Kamble
 *
 */
public class ConfigurationServiceAsSingleton {

	/** The properties */
	private final Properties properties;

	/** The configuration service singleton instance */
	private static ConfigurationServiceAsSingleton instance;

	/**
	 * The constructor
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private ConfigurationServiceAsSingleton() throws FileNotFoundException, IOException {
		properties = new Properties();
		properties.load(new FileInputStream(new File(System.getProperty("conf.file.path"))));
	}

	/**
	 * This method will get configuration service instance
	 * 
	 * @return the singleton configuration service instance
	 */
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

	/**
	 * Gets the property
	 * 
	 * @param key
	 * @return the string value of the property
	 */
	public String getProperty(String key) {
		return properties.getProperty(key);
	}

}
