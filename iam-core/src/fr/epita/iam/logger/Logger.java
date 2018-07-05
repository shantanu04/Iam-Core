package fr.epita.iam.logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * 
 * @author Shantanu Kamble
 *
 */
public class Logger {
	private static PrintWriter printWriter;
	private static String LOGPATH;
	private final Class<?> classe;
	private static final String ERROR = "ERROR";
	private static final String INFO = "INFO";

	static {
		try {
			InputStream iStream = Logger.class.getClassLoader().getResourceAsStream("logger-config.properties");
			Properties properties = new Properties();
			properties.load(iStream);

			if (properties != null) {
				LOGPATH = properties.getProperty("logPath");
			}
			printWriter = new PrintWriter(new FileOutputStream(new File(LOGPATH), true));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Logger(Class<?> classe) {
		this.classe = classe;
	}

	public void error(String message) {
		printMessage(message, "ERROR");
	}

	public void info(String message) {
		printMessage(message, "INFO");
	}

	private void printMessage(String message, String level) {
		String completeMessage = getTimeStamp() + " - " + level + " - " + classe.getCanonicalName() + " " + message;
		printWriter.println(completeMessage);
		printWriter.flush();
	}

	private static String getTimeStamp() {
		Date date = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss.SSS");
		return sdf.format(date);
	}

	public void error(String message, Exception e) {
		printMessage(message, "ERROR");
		e.printStackTrace(printWriter);
		printWriter.flush();
	}
}