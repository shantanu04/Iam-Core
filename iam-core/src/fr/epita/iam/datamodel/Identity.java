package fr.epita.iam.datamodel;

import java.util.Map;

/**
 * <h3>Description</h3>
 * <p>
 * This class is a data model class for identity.
 * </p>
 * 
 * @author Shantanu Kamble
 *
 */
public class Identity {
	private String displayName;
	private String uid;
	private String email;
	private Map<String, String> attributes;

	/**
	 * The contructor
	 * 
	 * @param displayName
	 * @param uid
	 * @param email
	 */
	public Identity(String displayName, String uid, String email) {
		this.displayName = displayName;
		this.uid = uid;
		this.email = email;
	}

	/**
	 * Default constructor
	 */
	public Identity() {

	}

	/**
	 * Gets the display name
	 * 
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Sets the display name
	 * 
	 * @param displayName
	 *            the displayName
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Gets the UID
	 * 
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * Sets the UID
	 * 
	 * @param uid
	 *            the uid
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * Gets the email
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email
	 * 
	 * @param email
	 *            the email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the attributes
	 * 
	 * @return the attributes
	 */
	public Map<String, String> getAttributes() {
		return attributes;
	}

	/**
	 * Sets the attributes
	 * 
	 * @param attributes
	 *            the attributes
	 */
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	/**
	 * This method prints the identity details
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Identity [displayName=" + displayName + ", uid=" + uid + ", email=" + email + ", attributes="
				+ attributes + "]";
	}

}
