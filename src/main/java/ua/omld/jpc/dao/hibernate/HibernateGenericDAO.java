package ua.omld.jpc.dao.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.omld.jpc.dao.GenericDAO;
import ua.omld.jpc.entity.Identifiable;
import ua.omld.jpc.exception.DAOException;

import javax.persistence.PersistenceException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.function.Supplier;

/**
 * Implements {@link GenericDAO} interface with Hibernate.
 *
 * @author Oleksii Kostetskyi
 */
public abstract class HibernateGenericDAO<E extends Identifiable> implements GenericDAO<E, Long> {

	protected final Logger logger = LogManager.getLogger(getClass());

	private final SessionFactory sessionFactory;
	private final Class<E> entityClass = (Class<E>) (((ParameterizedType) getClass().getGenericSuperclass())
			.getActualTypeArguments()[0]);

	protected HibernateGenericDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * Persists Entity to data storage.
	 *
	 * @param entity Entity to be persisted
	 * @return persisted Entity
	 */
	@Override
	public void create(E entity) {
		try {
			getCurrentSession().save(entity);
		} catch (PersistenceException e) {
			logger.error("Error persist entity: " + e.getMessage());
			throw new DAOException(e);
		}
	}

	/**
	 * Finds Entity in data storage by id.
	 *
	 * @param id id of Entity to find
	 * @return found Entity
	 */
	@Override
	public E findById(Long id) {
		try {
			return getCurrentSession().get(entityClass, id);
		} catch (PersistenceException e) {
			logger.error("Error find Entity with id " + id + ": " + e.getMessage());
			throw new DAOException(e);
		}
	}

	/**
	 * Updates Entity in data storage.
	 *
	 * @param entity Entity object to update
	 */
	@Override
	public void update(E entity) {
		try {
			getCurrentSession().update(entity);
		} catch (PersistenceException e) {
			logger.error("Error update entity: " + e.getMessage());
			throw new DAOException(e);
		}
	}

	/**
	 * Deletes given Entity from data storage.
	 *
	 * @param entity Entity object to be delete
	 */
	@Override
	public void delete(E entity) {
		try {
			getCurrentSession().delete(entity);
		} catch (PersistenceException e) {
			logger.error("Error delete entity: " + e.getMessage());
			throw new DAOException(e);
		}
	}

	/**
	 * Finds all Entities in data storage.
	 *
	 * @return found Entities
	 */
	@Override
	public List<E> findAll() {
		try {
			return getCurrentSession().createQuery("FROM " + entityClass.getSimpleName(), entityClass).getResultList();
		} catch (PersistenceException e) {
			logger.error("Error find all entities: " + e.getMessage());
			throw new DAOException(e);
		}
	}

	/**
	 * Runs given action in transactional context.
	 *
	 * @return action result
	 */
	public Object executeInsideTransaction(Supplier<Object> action, String logMessage) {
		Transaction transaction = getCurrentSession().getTransaction();
		try {
			transaction.begin();
			Object result = action.get();
			transaction.commit();
			return result;
		} catch (RuntimeException e) {
			logger.error(logMessage);
			transaction.rollback();
			throw e;
		}
	}
}