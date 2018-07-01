package fr.epita.iam.services.conf;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * 
 * @author Shantanu Kamble
 *
 */
public class ConfigurationService {

	public static final String BACKEND_MODE = "backend.mode";
	public static final String FALLBACK_BACKEND_MODE = "fallback.backend.mode";
	public static final String DB_BACKEND = "db";
	public static final String FILE_BACKEND = "file";

	private static Properties properties;

	static {
		init();
	}

	private static void init() {
		try {
			properties = new Properties();
			properties.load(new FileInputStream(new File(System.getProperty("conf.file.path"))));
		} catch (final Exception e) {
			// TODO treat exception
		}

	}

	public static Integer getIntProperty(ConfKey key) {
		final String valueAsString = getProperty(key);
		return Integer.valueOf(valueAsString);
	}

	public static String getProperty(ConfKey key) {
		return properties.getProperty(key.getKey());
	}

}
