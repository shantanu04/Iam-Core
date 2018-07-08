package fr.epita.iam.exceptions;

/**
 * <h3>Description</h3>
 * <p>
 * This class is an exception class, used in case of error in intializing jdbc.
 * </p>
 * 
 * @author Shantanu Kamble
 *
 */
public class JDBCInitException extends Exception {

	/** The Object entity */
	private final Object entity;

	/**
	 * The constructor
	 * 
	 * @param entity
	 * @param cause
	 */
	public JDBCInitException(Object entity) {
		this.entity = entity;
	}

	/**
	 * This method gets the user message
	 * 
	 * @return the user message
	 */
	public String getUserMessage() {
		return "The jdbc init has failed :" + entity.toString();
	}

}
