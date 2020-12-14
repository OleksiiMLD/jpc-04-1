package ua.omld.jpc.service.impl;

import ua.omld.jpc.dao.MaterialDAO;
import ua.omld.jpc.entity.Material;
import ua.omld.jpc.service.MaterialService;

import java.util.List;

/**
 * Implements {@link MaterialService} interface.
 *
 * @author Oleksii Kostetskyi
 */
public class MaterialServiceImpl implements MaterialService {

	private MaterialDAO materialDAO;

	private static final String WRONG_MATERIAL_ID = "Wrong material id: ";
	private static final String MATERIAL_IS_NULL = "Material is null.";
	private static final String PROVIDE_ID = "Please provide material id.";

	public MaterialServiceImpl(MaterialDAO materialDAO) {
		this.materialDAO = materialDAO;
	}

	/**
	 * Creates given {@link Material}.
	 *
	 * @param material Material to be created
	 * @return persisted Entity
	 */
	@Override
	public Material create(Material material) {
		if (material == null) {
			throw new IllegalArgumentException(MATERIAL_IS_NULL);
		}
		materialDAO.executeInsideTransaction(() -> {
			materialDAO.create(material);
			return material;
		}, "Can`t create material.");
		return material;
	}

	/**
	 * Finds {@link Material} by id.
	 *
	 * @param id id of the Material to find
	 * @return found Entity
	 */
	@Override
	public Material findById(Long id) {
		if (id == null) {
			throw new IllegalArgumentException(PROVIDE_ID);
		}
		return materialDAO.executeInsideTransaction(() -> materialDAO.findById(id), "Error find Material");
	}

	/**
	 * Updates given {@link Material}.
	 *
	 * @param material Material object to update
	 */
	@Override
	public void update(Material material) {
		if (material == null) {
			throw new IllegalArgumentException(MATERIAL_IS_NULL);
		}
		materialDAO.executeInsideTransaction(() -> {
			materialDAO.update(material);
			return material;
		}, "Error update Material");
	}

	/**
	 * Deletes {@link Material} with the given id.
	 *
	 * @param id Material object to be delete
	 */
	@Override
	public void deleteById(Long id) {
		if (id == null) {
			throw new IllegalArgumentException(PROVIDE_ID);
		}
		materialDAO.executeInsideTransaction(() -> {
			Material material = materialDAO.findById(id);
			if (material == null) {
				throw new IllegalArgumentException(WRONG_MATERIAL_ID + id);
			}
			materialDAO.delete(material);
			return true;
		}, "Error delete material.");
	}

	/**
	 * Return list of all {@link Material Materials}.
	 *
	 * @return list of found Materials
	 */
	@Override
	public List<Material> findAll() {
		return materialDAO.executeInsideTransaction(() -> materialDAO.findAll(), "Error find Materials");
	}
}