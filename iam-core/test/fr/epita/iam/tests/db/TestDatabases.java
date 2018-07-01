package fr.epita.iam.tests.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.epita.iam.datamodel.Identity;

/**
 * 
 * @author Shantanu Kamble
 *
 */
public class TestDatabases {

	public static void main(String[] args) throws Exception {
		// final Connection connection = testConnection();
		final Connection connection = getConnection();
		// insertQuery(connection);
		List<Identity> identities = getIdentities(connection);
		if (identities.size() != 0) {
			for (int i = 0; i < identities.size(); i++) {
				System.out.println("Identity" + i + 1 + " : " + identities.get(i));
			}
		}

	}

	private static void insertQuery(final Connection connection) throws SQLException {
		final PreparedStatement pstmt = connection.prepareStatement(
				"INSERT INTO IDENTITIES (IDENTITY_DISPLAYNAME, IDENTITY_EMAIL, IDENTITY_UID) VALUES (?, ?, ?)");
		pstmt.setString(1, "Clément Serr");
		pstmt.setString(2, "cserr@cserr.com");
		pstmt.setString(3, "9123");
		pstmt.execute();
		pstmt.close();
		connection.close();
	}

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

	private static Connection testConnection() throws Exception {

		String currentSchema = "";
		final Connection connection = getConnection();
		currentSchema = connection.getSchema();
		// Then I should get the "TEST" string in the currentSchema
		if (!currentSchema.equals("TEST")) {
			throw new Exception("problem: connection not operational");
		}
		System.out.println("Connection successful");
		return connection;
	}

	private static Connection getConnection() throws SQLException {
		// Given this context
		final String url = "jdbc:derby://localhost:1527/testInstance;create=true";
		Connection connection = null;

		// When I connect
		connection = DriverManager.getConnection(url, "test", "test");
		return connection;

	}

}