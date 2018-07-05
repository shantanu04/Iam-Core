package fr.epita.iam.services.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.epita.iam.datamodel.User;
import fr.epita.iam.logger.Logger;
import fr.epita.iam.services.conf.ConfKey;
import fr.epita.iam.services.conf.ConfigurationService;

/**
 * 
 * @author Shantanu Kamble
 *
 */
public class JDBCUserDAO {

	private final static Logger logger = new Logger(JDBCUserDAO.class);

	private static Connection getConnection() throws SQLException {
		// Given this context
		final String url = ConfigurationService.getProperty(ConfKey.DB_URL);
		Connection connection = null;

		// Get connection
		connection = DriverManager.getConnection(url, ConfigurationService.getProperty(ConfKey.DB_USER),
				ConfigurationService.getProperty(ConfKey.DB_PASSWORD));

		return connection;

	}

	public boolean checkLogin(User userLogin) {
		try {
			final Connection conn = getConnection();
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
			logger.error("SQL exception occured", e);
		}

		return false;
	}

}
