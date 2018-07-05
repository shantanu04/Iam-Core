package fr.epita.iam.exceptions;

/**
 * 
 * @author Shantanu Kamble
 *
 */
public class EntityDeletionException extends Exception {

	/** The Object entity */
	private Object entity;

	/**
	 * The constructor
	 * 
	 * @param entity
	 * @param cause
	 */
	public EntityDeletionException(Object entity, Throwable cause) {
		this.entity = entity;
		initCause(cause);
	}

	/**
	 * This method gets the user message
	 * 
	 * @return
	 */
	public String getUserMessage() {
		return "The following entity deletion has failed :" + entity.toString();
	}

}
