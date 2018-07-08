package fr.epita.iam.services;

import java.util.List;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.EntityCreationException;
import fr.epita.iam.exceptions.EntityDeletionException;
import fr.epita.iam.exceptions.EntitySearchException;
import fr.epita.iam.exceptions.EntityUpdateException;

/**
 * <h3>Description</h3>
 * <p>
 * This is a DAO interface.
 * </p>
 * 
 * @author Shantanu Kamble
 *
 */
public interface DAO<T> {

	/**
	 * Method to create entity
	 * 
	 * @param entity
	 * @throws EntityCreationException
	 */
	public void create(T entity) throws EntityCreationException;

	/**
	 * Method to delete entity
	 * 
	 * @param entity
	 * @throws EntityDeletionException
	 */
	public void delete(T entity) throws EntityDeletionException;

	/**
	 * Method to update entity
	 * 
	 * @param entity
	 * @throws EntityUpdateException
	 */
	public void update(T entity) throws EntityUpdateException;

	/**
	 * Method to search entity
	 * 
	 * @param criteria
	 * @return the list of identities
	 * @throws EntitySearchException
	 */
	public List<Identity> search(T criteria) throws EntitySearchException;

}
