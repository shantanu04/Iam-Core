package fr.epita.iam.services.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.epita.iam.datamodel.User;
import fr.epita.iam.exceptions.UserAuthenticationException;
import fr.epita.iam.logger.Logger;
import fr.epita.iam.services.conf.ConfKey;
import fr.epita.iam.services.conf.ConfigurationService;

/**
 * <h3>Description</h3>
 * <p>
 * This class is used to authenticate the user.
 * </p>
 * 
 * @author Shantanu Kamble
 *
 */
public class JDBCUserDAO implements UserDAO {

	/** The logger */
	private final static Logger logger = new Logger(JDBCUserDAO.class);

	/**
	 * Gets the database connection
	 * 
	 * @return
	 * @throws SQLException
	 */
	private static Connection getConnection() throws SQLException {
		// Given this context
		final String url = ConfigurationService.getProperty(ConfKey.DB_URL);
		Connection connection = null;

		// Get connection
		connection = DriverManager.getConnection(url, ConfigurationService.getProperty(ConfKey.DB_USER),
				ConfigurationService.getProperty(ConfKey.DB_PASSWORD));

		return connection;

	}

	/**
	 * This method will validate the login credentials of user
	 * 
	 * @param userLogin
	 * @return
	 */
	public boolean checkLogin(User userLogin) throws UserAuthenticationException {
		Connection conn = null;
		try {
			conn = getConnection();
			final PreparedStatement pstmt = conn
					.prepareStatement("SELECT * FROM USERS WHERE USER_USERNAME =? AND USER_PWD=?");

			pstmt.setString(1, userLogin.getUsername());
			pstmt.setString(2, userLogin.getPasskey());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				logger.info("Login success");
				return true;
			} else {
				logger.error("Login failed");
			}

		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.close();
				} catch (final SQLException sqlEx) {
					logger.error("SQL Exception occured while user authentication", sqlEx);
				}
			}
			final UserAuthenticationException userEx = new UserAuthenticationException(userLogin, e);
			logger.error("Exception occured while user authentication", e);
			throw userEx;
		}

		return false;
	}

	/**
	 * This method will perform the health check
	 * 
	 * @return true if health check is successful or else false
	 */
	@Override
	public boolean healthCheck() {
		try {
			final Connection connection = getConnection();
			connection.close();
			return true;
		} catch (final SQLException sqle) {
			logger.error("SQL Exception occured while performing health check", sqle);

		}
		return false;

	}

}
