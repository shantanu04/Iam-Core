package fr.epita.iam.exceptions;

/**
 * 
 */
public class EntityCreationException extends Exception {

	private Object entity;

	/**
	 *
	 */
	public EntityCreationException(Object entity, Throwable cause) {
		this.entity = entity;
		initCause(cause);
	}

	public String getUserMessage() {
		return "The following entity creation has failed :" + entity.toString();
	}

}
