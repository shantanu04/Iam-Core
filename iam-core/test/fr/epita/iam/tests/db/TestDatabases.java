package fr.epita.iam.tests.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.datamodel.User;
import fr.epita.iam.services.dao.IdentityDAO;
import fr.epita.iam.services.dao.IdentityDAOFactory;
import fr.epita.iam.services.dao.JDBCUserDAO;

/**
 * <h3>Description</h3>
 * <p>
 * This class tests the implementation of all the CRUD operations related to the
 * identity and user data model.
 * </p>
 * 
 * @author Shantanu Kamble
 *
 */
public class TestDatabases {

	/** The logger */
	private static final Logger logger = LogManager.getLogger(TestDatabases.class);

	public static void main(String[] args) throws Exception {

		// test database connection
		final Connection connection = testConnection();

		// test user login
		User user = new User();
		user.setUsername("admin");
		user.setPasskey("admin123");
		JDBCUserDAO userDAO = new JDBCUserDAO();
		boolean isValid = userDAO.checkLogin(user);
		if (!isValid) {
			logger.error("User authentication failed");
			return;
		}

		// display identities
		List<Identity> identities = getIdentities(connection);
		if (identities.size() != 0) {
			for (int i = 0; i < identities.size(); i++) {
				logger.info("Identity" + i + 1 + " : " + identities.get(i));
			}
		}

		// create identity
		Identity newIdentity = new Identity("Sample Test", "9123", "sample@test.com");
		IdentityDAO dao = IdentityDAOFactory.getDAO();

		dao.create(newIdentity);

		// search identity
		Identity criteria = new Identity();
		criteria.setDisplayName("Test");
		criteria.setEmail("sample@test");
		dao.search(criteria);

		// update identity
		Identity updateIdentity = new Identity();
		updateIdentity.setDisplayName("Tony Stark");
		updateIdentity.setEmail("tony@gmail.com");
		updateIdentity.setUid("9123");
		dao.update(updateIdentity);

		// delete identity
		Identity deleteIdentity = new Identity();
		deleteIdentity.setUid("9123");
		dao.delete(deleteIdentity);

	}

	/**
	 * This method is used to get identities from database
	 * 
	 * @param connection
	 * @return list of identities
	 * @throws SQLException
	 */
	private static List<Identity> getIdentities(final Connection connection) throws SQLException {
		final PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM IDENTITIES");

		pstmt.execute();
		ResultSet rset = pstmt.getResultSet();
		final ArrayList<Identity> identityList = new ArrayList<>();
		if (rset.next()) {
			Identity identity = new Identity();
			identity.setDisplayName(rset.getString(2));
			identity.setEmail(rset.getString(3));
			identity.setUid(rset.getString(4));
			identityList.add(identity);
		}

		pstmt.close();
		connection.close();

		return identityList;
	}

	/**
	 * This method is used to test database connection
	 * 
	 * @return the connection
	 * @throws Exception
	 */
	private static Connection testConnection() throws Exception {

		String currentSchema = "";
		final Connection connection = getConnection();
		currentSchema = connection.getSchema();
		// Then I should get the "TEST" string in the currentSchema
		if (!currentSchema.equals("TEST")) {
			throw new Exception("problem: connection not operational");
		}
		logger.info("Connection successful");
		return connection;
	}

	/**
	 * This method is used to get database connection object
	 * 
	 * @return the connection
	 * @throws SQLException
	 */
	private static Connection getConnection() throws SQLException {
		// Given this context
		final String url = "jdbc:derby://localhost:1527/testInstance;create=true";
		Connection connection = null;

		// When I connect
		connection = DriverManager.getConnection(url, "test", "test");
		return connection;

	}

}