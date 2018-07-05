package fr.epita.iam.services.dao;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.DAO;

/**
 * 
 * @author Shantanu Kamble
 *
 */
public interface IdentityDAO extends DAO<Identity> {

	/**
	 * 
	 * @return
	 */
	boolean healthCheck();

}
