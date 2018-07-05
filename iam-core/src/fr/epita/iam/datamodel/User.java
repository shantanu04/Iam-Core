package fr.epita.iam.datamodel;

/**
 * 
 * @author Shantanu Kamble
 *
 */
public class User {

	/** The username */
	private String username;

	/** The password */
	private String passkey;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the passkey
	 */
	public String getPasskey() {
		return passkey;
	}

	/**
	 * @param passkey
	 *            the passkey to set
	 */
	public void setPasskey(String passkey) {
		this.passkey = passkey;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [username=" + username + ", passkey=" + passkey + "]";
	}
}
