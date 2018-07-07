package fr.epita.iam.services.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.EntityCreationException;
import fr.epita.iam.exceptions.EntityDeletionException;
import fr.epita.iam.exceptions.EntitySearchException;
import fr.epita.iam.exceptions.EntityUpdateException;
import fr.epita.iam.logger.Logger;
import fr.epita.iam.services.conf.ConfKey;
import fr.epita.iam.services.conf.ConfigurationService;

/**
 * <h3>Description</h3>
 * <p>
 * This class provides implementation of all the CRUD operations related to the
 * identity data model.
 * </p>
 * 
 * @author Shantanu Kamble
 *
 */
public class JDBCIdentityDAO implements IdentityDAO {

	/** The logger */
	private final static Logger logger = new Logger(JDBCIdentityDAO.class);

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

		// When I connect
		connection = DriverManager.getConnection(url, ConfigurationService.getProperty(ConfKey.DB_USER),

				ConfigurationService.getProperty(ConfKey.DB_PASSWORD));
		return connection;

	}

	/**
	 * This method will create the identity in the database
	 */
	@Override
	public void create(Identity identity) throws EntityCreationException {
		Connection connection = null;
		try {
			connection = getConnection();
			final PreparedStatement pstmt = connection
					.prepareStatement(ConfigurationService.getProperty(ConfKey.IDENTITY_INSERT_QUERY));

			pstmt.setString(1, identity.getDisplayName());
			pstmt.setString(2, identity.getEmail());
			pstmt.setString(3, identity.getUid());
			pstmt.execute();
			pstmt.close();
			connection.close();
		} catch (final SQLException e) {
			if (connection != null) {
				try {
					connection.close();
				} catch (final SQLException e1) {
					logger.error("SQL Exception occured while identity creation", e1);
				}
			}
			final EntityCreationException exception = new EntityCreationException(identity, e);
			logger.error("Exception occured while identity creation", exception);
			throw exception;
		}
	}

	/**
	 * This method will delete the identity from the database
	 */
	@Override
	public void delete(Identity identity) throws EntityDeletionException {
		Connection connection = null;

		try {
			connection = getConnection();
			final PreparedStatement pstmt = connection
					.prepareStatement(ConfigurationService.getProperty(ConfKey.IDENTITY_DELETE_QUERY));
			pstmt.setString(1, identity.getUid());

			int rowCount = pstmt.executeUpdate();
			pstmt.close();
			connection.close();
			if (rowCount > 0) {
				logger.info("Deleted identity successfully");
			} else {
				logger.error("Delete unsuccessful");
			}

		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.close();
				} catch (final SQLException e1) {
					logger.error("SQL Exception occured while identity deletion", e1);
				}
			}
			final EntityDeletionException exception = new EntityDeletionException(identity, e);
			logger.error("Exception occured while deleting identity", e);
		}
	}

	/**
	 * This method will update the identity in the database
	 */
	@Override
	public void update(Identity identity) throws EntityUpdateException {
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(assembleUpdateQuery(identity));

			pstmt = assemblePreparedStatement(pstmt, identity);

			pstmt.executeUpdate();
			pstmt.close();
			connection.close();
		} catch (final SQLException e) {
			if (connection != null) {
				try {
					connection.close();
				} catch (final SQLException e1) {
					logger.error("SQL Exception occured while identity updation", e1);
				}
			}
			final EntityUpdateException exception = new EntityUpdateException(identity, e);
			logger.error("Exception occured while identity updation", e);
			throw exception;
		}
	}

	/**
	 * This method will assemble the update query
	 */
	private String assembleUpdateQuery(Identity identity) {
		String sqlString = "";
		if (identity.getDisplayName() != null && !identity.getDisplayName().isEmpty() && identity.getEmail() != null
				&& !identity.getEmail().isEmpty()) {
			sqlString = ConfigurationService.getProperty(ConfKey.IDENTITY_UPDATE_QUERY);

		} else if (identity.getDisplayName() != null && !identity.getDisplayName().isEmpty()
				&& (identity.getEmail() == null || identity.getEmail().isEmpty())) {
			sqlString = ConfigurationService.getProperty(ConfKey.IDENTITY_UPDATE_DISPLAYNAME_QUERY);

		} else if (identity.getEmail() != null && !identity.getEmail().isEmpty()
				&& (identity.getDisplayName() == null || identity.getDisplayName().isEmpty())) {
			sqlString = ConfigurationService.getProperty(ConfKey.IDENTITY_UPDATE_EMAIL_QUERY);

		}

		return sqlString;
	}

	/**
	 * This method will assemble the prepared statement
	 * 
	 * @param statement
	 * @param identity
	 * @return
	 * @throws SQLException
	 */
	private PreparedStatement assemblePreparedStatement(PreparedStatement statement, Identity identity)
			throws SQLException {
		if (identity.getDisplayName() != null && !identity.getDisplayName().isEmpty() && identity.getEmail() != null
				&& !identity.getEmail().isEmpty()) {
			statement.setString(1, identity.getDisplayName());
			statement.setString(2, identity.getEmail());
			statement.setString(3, identity.getUid());
		} else if (identity.getDisplayName() != null && !identity.getDisplayName().isEmpty()
				&& (identity.getEmail() == null || identity.getEmail().isEmpty())) {
			statement.setString(1, identity.getDisplayName());
			statement.setString(2, identity.getUid());
		} else if (identity.getEmail() != null && !identity.getEmail().isEmpty()
				&& (identity.getDisplayName() == null || identity.getDisplayName().isEmpty())) {
			statement.setString(1, identity.getEmail());
			statement.setString(2, identity.getUid());
		}

		return statement;
	}

	/**
	 * This method gets identity by Id
	 */
	@Override
	public Identity getById(Serializable id) {
		final Identity identity = new Identity();

		return identity;
	}

	/**
	 * This method will search the identity in the database
	 */
	@Override
	public List<Identity> search(Identity criteria) throws EntitySearchException {
		final List<Identity> list = new ArrayList<>();

		Connection connection = null;
		try {
			connection = getConnection();
			final PreparedStatement pstmt = connection
					.prepareStatement("select IDENTITY_DISPLAYNAME, IDENTITY_EMAIL, IDENTITY_UID from IDENTITIES"
							+ " where IDENTITY_DISPLAYNAME like ? or IDENTITY_EMAIL like ?");
			pstmt.setString(1, "%" + criteria.getDisplayName() + "%");
			pstmt.setString(2, "%" + criteria.getEmail() + "%");
			final ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				final String displayName = rs.getString("IDENTITY_DISPLAYNAME");
				final String email = rs.getString("IDENTITY_EMAIL");
				final String uid = rs.getString("IDENTITY_UID");
				final Identity identity = new Identity(displayName, uid, email);
				list.add(identity);

			}
			pstmt.close();
			connection.close();
		} catch (final SQLException e) {
			if (connection != null) {
				try {
					connection.close();
				} catch (final SQLException e2) {
					EntitySearchException searchException = new EntitySearchException(criteria, e2);
					logger.error("SQL Exception occured while searching identity", e2);
					throw searchException;
				}
			}
			final EntitySearchException exception = new EntitySearchException(criteria, e);
			logger.error("Exception occured while searching identity", e);
			throw exception;
		}
		return list;
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
