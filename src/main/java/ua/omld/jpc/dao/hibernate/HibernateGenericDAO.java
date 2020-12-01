package ua.omld.jpc.dao.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.omld.jpc.dao.GenericDAO;
import ua.omld.jpc.entity.Identifiable;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.function.Function;

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
		getCurrentSession().save(entity);
	}

	/**
	 * Finds Entity in data storage by id.
	 *
	 * @param id id of Entity to find
	 * @return found Entity
	 */
	@Override
	public E findById(Long id) {
		return getCurrentSession().get(entityClass, id);
	}

	/**
	 * Updates Entity in data storage.
	 *
	 * @param entity Entity object to update
	 */
	@Override
	public void update(E entity) {
		getCurrentSession().update(entity);
	}

	/**
	 * Deletes given Entity from data storage.
	 *
	 * @param entity Entity object to be delete
	 */
	@Override
	public void delete(E entity) {
		getCurrentSession().delete(entity);
	}

	/**
	 * Finds all Entities in data storage.
	 *
	 * @return found Entities
	 */
	@Override
	public List<E> findAll() {
		return getCurrentSession().createQuery("FROM " + entityClass.getSimpleName(), entityClass).getResultList();
	}

	/**
	 * Runs given action in transactional context.
	 *
	 * @return action result
	 */
	public Object executeInsideTransaction(Function<Object[], Object> action, String logMessage, Object... args) {
		Transaction transaction = getCurrentSession().getTransaction();
		try {
			transaction.begin();
			Object result = action.apply(args);
			transaction.commit();
			return result;
		} catch (RuntimeException e) {
			logger.error(logMessage);
			transaction.rollback();
			throw e;
		}
	}
}
