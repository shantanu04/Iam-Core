package fr.epita.iam.services.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epita.iam.datamodel.User;
import fr.epita.iam.exceptions.UserAuthenticationException;
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
	private static final Logger logger = LogManager.getLogger(JDBCUserDAO.class);

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
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(ConfigurationService.getProperty(ConfKey.USER_RETRIEVE_QUERY));

			pstmt.setString(1, userLogin.getUsername());
			pstmt.setString(2, userLogin.getPasskey());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				logger.info("Login success");
				return true;
			} else {
				logger.error("Login failed");
			}

		} catch (SQLException e) {
			final UserAuthenticationException userEx = new UserAuthenticationException(userLogin, e);
			logger.error("Exception occured while user authentication", e);
			throw userEx;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (final SQLException sqlEx) {
				logger.error("SQL Exception occured while user authentication", sqlEx);
			}
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
