package fr.epita.iam.services.dao;

/**
 * <h3>Description</h3>
 * <p>
 * The interface UserDAO.
 * </p>
 * 
 * @author Shantanu Kamble
 *
 */
public interface UserDAO {

	/**
	 * This method will perform the health check
	 * 
	 * @return true if health check is successful or else false
	 */
	boolean healthCheck();

}
