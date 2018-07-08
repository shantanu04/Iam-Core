package fr.epita.iam.exceptions;

/**
 * <h3>Description</h3>
 * <p>
 * This class is an exception class, used in case of error in searching entity.
 * </p>
 * 
 * @author Shantanu Kamble
 *
 */
public class EntitySearchException extends Exception {

	/** The Object entity */
	private final Object entity;

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
