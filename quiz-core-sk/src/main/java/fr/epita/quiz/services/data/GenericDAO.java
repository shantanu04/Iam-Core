package fr.epita.quiz.services.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public abstract class GenericDAO<T> {

	private static final Logger LOGGER = LogManager.getLogger(GenericDAO.class);

	@PersistenceContext
	protected EntityManager em;

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(T instance) {
		LOGGER.debug("Update entity");
		em.merge(instance);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(T instance) {
		LOGGER.debug("Delete entity");
		em.remove(em.contains(instance) ? instance : em.merge(instance));
		
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void create(T instance) {
		LOGGER.debug("Create entity");
		em.persist(instance);

	}

	public abstract List<T> search(T criteriaInstance);

	public T getById(Serializable id) {
		LOGGER.debug("Find entity by id");
		return em.find(getType(), id);
	}

	public abstract Class<T> getType();
}
