package ua.omld.jpc.dao;

import ua.omld.jpc.entity.Identifiable;

import java.io.Serializable;
import java.util.List;
import java.util.function.Supplier;

/**
 * Provides generic DAO interface with basic CRUD operations.
 *
 * @author Oleksii Kostetskyi
 */
public interface GenericDAO<E extends Identifiable, ID extends Serializable> {

	/**
	 * Persists given Entity to data storage.
	 *
	 * @param entity Entity to be persisted
	 * @return persisted Entity
	 */
	void create(E entity);

	/**
	 * Finds Entity in data storage by id.
	 *
	 * @param id id of Entity to find
	 * @return found Entity
	 */
	E findById(ID id);

	/**
	 * Updates given Entity in data storage.
	 *
	 * @param entity Entity object to update
	 */
	void update(E entity);

	/**
	 * Deletes given Entity from data storage.
	 *
	 * @param entity Entity object to be delete
	 */
	void delete(E entity);

	/**
	 * Finds all Entities in data storage.
	 *
	 * @return found Entities
	 */
	List<E> findAll();

	/**
	 * Runs given action in transactional context.
	 *
	 * @return action result
	 */
	Object executeInsideTransaction(Supplier<Object> action, String logMessage);
}
