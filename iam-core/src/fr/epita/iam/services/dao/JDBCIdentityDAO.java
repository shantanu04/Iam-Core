package fr.epita.iam.services.dao;

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
import fr.epita.iam.exceptions.EntityCreationException;
import fr.epita.iam.exceptions.EntityDeletionException;
import fr.epita.iam.exceptions.EntitySearchException;
import fr.epita.iam.exceptions.EntityUpdateException;
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
	private static final Logger logger = LogManager.getLogger(JDBCIdentityDAO.class);

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
	 * 
	 * @param identity
	 * @throws EntityCreationException
	 * @see fr.epita.iam.services.DAO#create(java.lang.Object)
	 */
	@Override
	public void create(Identity identity) throws EntityCreationException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(ConfigurationService.getProperty(ConfKey.IDENTITY_INSERT_QUERY));

			pstmt.setString(1, identity.getDisplayName());
			pstmt.setString(2, identity.getEmail());
			pstmt.setString(3, identity.getUid());
			pstmt.execute();
			pstmt.close();
			connection.close();
		} catch (final SQLException e) {
			final EntityCreationException exception = new EntityCreationException(identity, e);
			logger.error("Exception occured while identity creation", exception);
			throw exception;
		} finally {
			try {
				if (null != connection) {
					connection.close();
				}
				if (null != pstmt) {
					pstmt.close();
				}
			} catch (SQLException e) {
				logger.error("Exception occured while closing connection", e);
			}
		}
	}

	/**
	 * This method will delete the identity from the database
	 * 
	 * @param identity
	 * @throws EntityDeletionException
	 * @see fr.epita.iam.services.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(Identity identity) throws EntityDeletionException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(ConfigurationService.getProperty(ConfKey.IDENTITY_DELETE_QUERY));
			pstmt.setString(1, identity.getUid());

			int rowCount = pstmt.executeUpdate();

			if (rowCount > 0) {
				logger.info("Deleted identity successfully");
			} else {
				logger.error("Delete unsuccessful");
			}

		} catch (SQLException e) {
			final EntityDeletionException exception = new EntityDeletionException(identity, e);
			logger.error("Exception occured while deleting identity", exception);
		} finally {
			try {
				if (null != connection) {
					connection.close();
				}
				if (null != pstmt) {
					pstmt.close();
				}
			} catch (SQLException e) {
				logger.error("Exception occured while closing connection", e);
			}

		}
	}

	/**
	 * This method will update the identity in the database
	 * 
	 * @param identity
	 * @throws EntityUpdateException
	 * @see fr.epita.iam.services.DAO#update(java.lang.Object)
	 */
	@Override
	public void update(Identity identity) throws EntityUpdateException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(assembleUpdateQuery(identity));

			pstmt = assemblePreparedStatement(pstmt, identity);

			pstmt.executeUpdate();
			pstmt.close();
			connection.close();
		} catch (final SQLException e) {
			final EntityUpdateException exception = new EntityUpdateException(identity, e);
			logger.error("Exception occured while identity updation", e);
			throw exception;
		} finally {
			try {
				if (null != connection) {
					connection.close();
				}
				if (null != pstmt) {
					pstmt.close();
				}
			} catch (SQLException e) {
				logger.error("Exception occured while closing connection", e);
			}

		}
	}

	/**
	 * This method will assemble the update query
	 * 
	 * @param identity
	 * @return the update query string
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
	 * @return the prepared statement
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
	 * This method will search the identity in the database
	 * 
	 * @param criteria
	 * @return list of identities
	 * @see fr.epita.iam.services.DAO#search(java.lang.Object)
	 */
	@Override
	public List<Identity> search(Identity criteria) throws EntitySearchException {
		final List<Identity> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(ConfigurationService.getProperty(ConfKey.IDENTITY_SEARCH_QUERY));
			pstmt.setString(1, "%" + criteria.getDisplayName() + "%");
			pstmt.setString(2, "%" + criteria.getEmail() + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final String displayName = rs.getString("IDENTITY_DISPLAYNAME");
				final String email = rs.getString("IDENTITY_EMAIL");
				final String uid = rs.getString("IDENTITY_UID");
				final Identity identity = new Identity(displayName, uid, email);
				list.add(identity);

			}
		} catch (final SQLException e) {
			final EntitySearchException exception = new EntitySearchException(criteria, e);
			logger.error("Exception occured while searching identity", e);
			throw exception;
		} finally {
			try {
				if (null != connection) {
					connection.close();
				}
				if (null != pstmt) {
					pstmt.close();
				}
				if (null != rs) {
					rs.close();
				}
			} catch (SQLException e) {
				logger.error("Exception occured while closing connection", e);
			}

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
