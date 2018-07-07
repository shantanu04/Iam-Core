package fr.epita.iam.exceptions;

/**
 * <h3>Description</h3>
 * <p>
 * This class is an exception class, used in case of error in reading an entity.
 * </p>
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
