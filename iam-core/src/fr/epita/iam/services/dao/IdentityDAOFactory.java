package fr.epita.iam.services.dao;

import fr.epita.iam.exceptions.JDBCInitException;
import fr.epita.iam.services.conf.ConfKey;
import fr.epita.iam.services.conf.ConfigurationService;

/**
 * <h3>Description</h3>
 * <p>
 * This class is a factory class used to register the DAOs.
 * </p>
 * 
 * @author Shantanu Kamble
 *
 */
public class IdentityDAOFactory {

	/** The current instance */
	private static IdentityDAO currentInstance;

	private IdentityDAOFactory() {

	}

	/**
	 * This method is used to get instance of identityDAO
	 * 
	 * @return the identityDAO instance
	 * @throws Exception
	 */
	public static IdentityDAO getDAO() throws JDBCInitException {
		final String backendMode = ConfigurationService.getProperty(ConfKey.BACKEND_MODE);

		if (currentInstance == null) {
			currentInstance = getInstance(backendMode);
		}

		return currentInstance;
	}

	/**
	 * This method is used to get the instance of the identityDAO implementation
	 * 
	 * @param backendMode
	 * @return
	 * @throws Exception
	 */
	private static IdentityDAO getInstance(final String backendMode) throws JDBCInitException {
		IdentityDAO instance = null;
		if (backendMode.equals("db")) {
			instance = new JDBCIdentityDAO();
		} else {
			throw new JDBCInitException("not implemented yet");
		}

		return instance;

	}

}
