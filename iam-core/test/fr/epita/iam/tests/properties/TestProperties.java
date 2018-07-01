package fr.epita.iam.tests.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

/**
 * 
 * @author Shantanu Kamble
 *
 */
public class TestProperties {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		final Properties properties = new Properties();
		properties.load(new FileInputStream(new File("test/test.properties")));
		final Set<Object> keySet = properties.keySet();
		System.out.println(properties.getProperty("db.url"));
		System.out.println(keySet);
	}

}
