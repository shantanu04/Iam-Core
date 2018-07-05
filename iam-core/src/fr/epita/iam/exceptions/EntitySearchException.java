package fr.epita.iam.exceptions;

/**
 * 
 * @author Shantanu Kamble
 *
 */
public class EntitySearchException extends Exception {

	/** The Object entity */
	private Object entity;

	/**
	 * The constructor
	 * 
	 * @param entity
	 * @param cause
	 */
	public EntitySearchException(Object entity, Throwable cause) {
		this.entity = entity;
		initCause(cause);
	}

	/**
	 * This method gets the user message
	 * 
	 * @return
	 */
	public String getUserMessage() {
		return "The following entity search has failed :" + entity.toString();
	}
}
