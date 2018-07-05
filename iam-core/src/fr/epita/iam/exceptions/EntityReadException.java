package fr.epita.iam.exceptions;

/**
 * 
 * @author Shantanu Kamble
 *
 */
public class EntityReadException extends Exception {

	/** The Object entity */
	private Object entity;

	/**
	 * The constructor
	 * 
	 * @param entity
	 * @param cause
	 */
	public EntityReadException(Object entity, Throwable cause) {
		this.entity = entity;
		initCause(cause);
	}

	/**
	 * This method gets the user message
	 * 
	 * @return
	 */
	public String getUserMessage() {
		return "The following entity read has failed :" + entity.toString();
	}

}
