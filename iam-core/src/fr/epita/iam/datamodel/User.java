package fr.epita.iam.datamodel;

/**
 * <h3>Description</h3>
 * <p>
 * This class is a data model class for user.
 * </p>
 * 
 * @author Shantanu Kamble
 *
 */
public class User {

	/** The username */
	private String username;

	/** The passkey */
	private String passkey;

	/**
	 * Gets the username
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username
	 * 
	 * @param username
	 *            the username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the passkey
	 * 
	 * @return the passkey
	 */
	public String getPasskey() {
		return passkey;
	}

	/**
	 * Sets the passkey
	 * 
	 * @param passkey
	 *            the passkey
	 */
	public void setPasskey(String passkey) {
		this.passkey = passkey;
	}

	/**
	 * This method prints user details
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [username=" + username + ", passkey=" + passkey + "]";
	}
}
