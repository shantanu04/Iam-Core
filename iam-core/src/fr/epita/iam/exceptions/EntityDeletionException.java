package fr.epita.iam.exceptions;

/**
 * <h3>Description</h3>
 * <p>
 * This class is an exception class, used in case of error in entity deletion.
 * </p>
 * 
 * @author Shantanu Kamble
 *
 */
public class EntityDeletionException extends Exception {

	/** The Object entity */
	private final Object entity;

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
