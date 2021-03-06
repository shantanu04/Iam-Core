package fr.epita.iam.services.dao;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.DAO;

/**
 * <h3>Description</h3>
 * <p>
 * The IdentityDAO interface.
 * </p>
 * 
 * @author Shantanu Kamble
 *
 */
public interface IdentityDAO extends DAO<Identity> {

	/**
	 * This method will perform the health check
	 * 
	 * @return true if health check is successful or else false
	 */
	boolean healthCheck();

}
