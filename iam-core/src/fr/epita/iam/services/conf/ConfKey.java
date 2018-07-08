package fr.epita.iam.services.conf;

/**
 * <h3>Description</h3>
 * <p>
 * This enum contains all the keys required for configuration.
 * </p>
 * 
 * @author Shantanu Kamble
 *
 */
public enum ConfKey {

	/**
	 * The key to choose the backend mode
	 */
	BACKEND_MODE("backend.mode"),

	/**
	 * The key to choose the fall back backend mode
	 */
	FALLBACK_BACKEND_MODE("backend.mode"),

	/**
	 * The key for database url
	 */
	DB_URL("db.url"),

	/**
	 * The key for database username
	 */
	DB_USER("db.user"),

	/**
	 * The key for database password
	 */
	DB_PASSWORD("db.pwd"),

	/**
	 * The key for backend
	 */
	DB_BACKEND("db"),

	/**
	 * The key to search identity
	 */
	IDENTITY_SEARCH_QUERY("identity.search"),

	/**
	 * The key to insert identity
	 */
	IDENTITY_INSERT_QUERY("identity.insert"),

	/**
	 * The key to update identity
	 */
	IDENTITY_UPDATE_QUERY("identity.update"),

	/**
	 * The key to update displayname
	 */
	IDENTITY_UPDATE_DISPLAYNAME_QUERY("identity.update.displayname"),

	/**
	 * The key to update email
	 */
	IDENTITY_UPDATE_EMAIL_QUERY("identity.update.email"),

	/**
	 * The key to delete identity
	 */
	IDENTITY_DELETE_QUERY("identity.delete"),

	/**
	 * The key to retrieve user
	 */
	USER_RETRIEVE_QUERY("user.retrieve");

	/** The key */
	private String key;

	/**
	 * The constructor
	 */
	private ConfKey(String key) {
		this.key = key;
	}

	/**
	 * Gets the key
	 * 
	 * @return the string value of the key
	 */
	public String getKey() {
		return key;
	}

}
