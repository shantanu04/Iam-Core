package fr.epita.iam.services.conf;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <h3>Description</h3>
 * <p>
 * This class is used to initialize and get the properties.
 * </p>
 * 
 * @author Shantanu Kamble
 *
 */
public class ConfigurationService {

	/**
	 * Private constructor
	 */
	private ConfigurationService() {
		// Private constructor
	}

	/** The logger */
	private static final Logger logger = LogManager.getLogger(ConfigurationService.class);

	/** The constant backend mode */
	public static final String BACKEND_MODE = "backend.mode";

	/** The constant fallback backend mode */
	public static final String FALLBACK_BACKEND_MODE = "fallback.backend.mode";

	/** The constant db backend */
	public static final String DB_BACKEND = "db";

	/** The constant file backend */
	public static final String FILE_BACKEND = "file";

	/** The properties */
	private static Properties properties;

	static {
		init();
	}

	/**
	 * Method to Initialize properties
	 */
	private static void init() {
		try {
			properties = new Properties();
			properties.load(new FileInputStream(new File(System.getProperty("conf.file.path"))));
		} catch (final Exception e) {
			logger.error("Exception occured while initialising properties");
		}

	}

	/**
	 * Gets the property of type Integer
	 * 
	 * @param key
	 * @return integer value of property key
	 */
	public static Integer getIntProperty(ConfKey key) {
		final String valueAsString = getProperty(key);
		return Integer.valueOf(valueAsString);
	}

	/**
	 * Gets the property
	 * 
	 * @param key
	 * @return string value of property key
	 */
	public static String getProperty(ConfKey key) {
		return properties.getProperty(key.getKey());
	}

}
