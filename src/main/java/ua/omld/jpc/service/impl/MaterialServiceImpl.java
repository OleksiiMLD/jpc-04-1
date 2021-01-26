package ua.omld.jpc.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.omld.jpc.dao.MaterialDAO;
import ua.omld.jpc.entity.Material;
import ua.omld.jpc.service.MaterialService;

import java.util.List;

/**
 * Implements {@link MaterialService} interface.
 *
 * @author Oleksii Kostetskyi
 */
@Service
public class MaterialServiceImpl implements MaterialService {

	private static final String ERROR_CREATE_MATERIAL = "Can`t create material.";
	private static final String ERROR_DELETE_MATERIAL = "Error delete material.";
	private static final String ERROR_FIND_MATERIALS = "Error find Materials";
	private static final String ERROR_UPDATE_MATERIAL = "Error update Material.";
	private static final String MATERIAL_IS_NULL = "Material is null.";
	private static final String MATERIAL_NOT_FOUND = "Material with the given ID not found.";
	private static final String PROVIDE_ID = "Please provide material id.";
	private static final String WRONG_MATERIAL_ID = "Wrong material id: ";

	private MaterialDAO materialDAO;

	@Autowired
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
		}, ERROR_CREATE_MATERIAL);
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
		return materialDAO.executeInsideTransaction(() -> materialDAO.findById(id), MATERIAL_NOT_FOUND);
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
			Material savedMaterial = materialDAO.findById(material.getId());
			if (savedMaterial == null) {
				throw new IllegalArgumentException(MATERIAL_NOT_FOUND);
			}
			BeanUtils.copyProperties(material, savedMaterial);
			materialDAO.update(savedMaterial);
			return savedMaterial;
		}, ERROR_UPDATE_MATERIAL);
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
		}, ERROR_DELETE_MATERIAL);
	}

	/**
	 * Return list of all {@link Material Materials}.
	 *
	 * @return list of found Materials
	 */
	@Override
	public List<Material> findAll() {
		return materialDAO.executeInsideTransaction(() -> materialDAO.findAll(), ERROR_FIND_MATERIALS);
	}
}