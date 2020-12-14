package ua.omld.jpc.service;

import ua.omld.jpc.entity.Building;
import ua.omld.jpc.entity.Material;

import java.util.List;

/**
 * Provides Service interface for {@link Building}
 *
 * @author Oleksii Kostetskyi
 */
public interface MaterialService {

	/**
	 * Creates given {@link Material}.
	 *
	 * @param material Material to be created
	 * @return persisted Entity
	 */
	Material create(Material material);

	/**
	 * Finds {@link Material} by id.
	 *
	 * @param id id of the Material to find
	 * @return found Entity
	 */
	Material findById(Long id);

	/**
	 * Updates given {@link Material}.
	 *
	 * @param material Material object to update
	 */
	void update(Material material);

	/**
	 * Deletes {@link Material} with the given id.
	 *
	 * @param id Material object to be delete
	 */
	void deleteById(Long id);

	/**
	 * Return list of all {@link Material Materials}.
	 *
	 * @return list of found Materials
	 */
	List<Material> findAll();
}
