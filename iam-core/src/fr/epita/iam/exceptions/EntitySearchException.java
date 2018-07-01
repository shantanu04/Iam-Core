package fr.epita.iam.exceptions;

/** 
 * 
 */
public class EntitySearchException extends Exception {

	private Object entity;

	public EntitySearchException(Object entity, Throwable cause) {
		this.entity = entity;
		initCause(cause);
	}

	public String getUserMessage() {
		return "The following entity search has failed :" + entity.toString();
	}
}
