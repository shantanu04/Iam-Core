package fr.epita.iam.tests.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <h3>Description</h3>
 * <p>
 * This class is used to test the extraction of values of keys from properties
 * file
 * </p>
 * 
 * @author Shantanu Kamble
 *
 */
public class TestProperties {

	/** The logger */
	private static final Logger logger = LogManager.getLogger(TestProperties.class);

	public static void main(String[] args) throws FileNotFoundException, IOException {
		final Properties properties = new Properties();
		properties.load(new FileInputStream(new File("test/test.properties")));
		final Set<Object> keySet = properties.keySet();
		logger.info(properties.getProperty("db.url"));
		logger.info(keySet);
	}

}
