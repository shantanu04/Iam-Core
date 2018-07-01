package fr.epita.iam.services;

import java.io.Serializable;
import java.util.List;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.EntityCreationException;
import fr.epita.iam.exceptions.EntityDeletionException;
import fr.epita.iam.exceptions.EntityReadException;
import fr.epita.iam.exceptions.EntitySearchException;
import fr.epita.iam.exceptions.EntityUpdateException;

/**
 * 
 */
public interface DAO<T> {

	public void create(T entity) throws EntityCreationException;

	public void delete(T entity) throws EntityDeletionException;

	public void update(T entity) throws EntityUpdateException;

	public Identity getById(Serializable id) throws EntityReadException;

	public List<Identity> search(T criteria) throws EntitySearchException;

}
