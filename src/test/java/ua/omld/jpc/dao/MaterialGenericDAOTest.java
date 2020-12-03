package ua.omld.jpc.dao;

import org.junit.jupiter.api.Test;
import ua.omld.jpc.dao.hibernate.MaterialDAOImpl;
import ua.omld.jpc.entity.Material;
import ua.omld.jpc.exception.DAOException;

import javax.persistence.PersistenceException;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ua.omld.jpc.testdata.MaterialTestData.GENERAL_MATERIAL_DATA;
import static ua.omld.jpc.testdata.MaterialTestData.ID_TO_DELETE;
import static ua.omld.jpc.testdata.MaterialTestData.ID_TO_FIND;
import static ua.omld.jpc.testdata.MaterialTestData.NONEXISTENT_ID;
import static ua.omld.jpc.testdata.MaterialTestData.STRING_55_CHARS;

/**
 * Tests GenericDAO methods using MaterialDAO
 *
 * @author Oleksii Kostetskyi
 */
class MaterialGenericDAOTest extends DaoTest {

	private final MaterialDAO materialDAO = new MaterialDAOImpl(sessionFactory);

	@Test
	void findById_rightId_rightEntity() {
		Material expected = GENERAL_MATERIAL_DATA.get("MATERIAL_1");
		Material actual = materialDAO.findById(expected.getId());
		assertEquals(expected.getName(), actual.getName());
	}

	@Test
	void findById_nonexistentId_null() {
		assertNull(materialDAO.findById(NONEXISTENT_ID), "There is no entity with such Id");
	}

	@Test
	void findById_afterCreateEntity_returnsCreatedEntity() {
		Material material = new Material();
		material.setName("Paper");

		materialDAO.create(material);
		sessionFactory.getCurrentSession().flush();
		assertNotNull(materialDAO.findById(material.getId()), "Material must be saved.");
	}

	@Test
	void create_entityWithConstraintViolation_throwsException() {
		Material material = new Material();
		material.setName(STRING_55_CHARS);

		assertThrows(PersistenceException.class, () -> {
			materialDAO.create(material);
			sessionFactory.getCurrentSession().flush();
		}, "Material name too long.");
	}

	@Test
	void findById_afterUpdateEntity_returnsUpdatedEntity() {
		BigDecimal newPrice = BigDecimal.valueOf(20.00);
		Material material = materialDAO.findById(ID_TO_FIND);

		material.setPrice(newPrice);
		materialDAO.update(material);
		Material updatedMaterial = materialDAO.findById(ID_TO_FIND);

		assertEquals(newPrice, updatedMaterial.getPrice(), "Price must be updated.");
	}

	@Test
	void update_transientEntity_throwsException() {
		Material material = new Material();
		assertThrows(DAOException.class, () -> {
			materialDAO.update(material);
		}, "Supplier too long.");
	}

	@Test
	void update_entityWithConstraintViolation_throwsException() {
		Material material = materialDAO.findById(ID_TO_FIND);
		material.setSupplier(STRING_55_CHARS);
		assertThrows(PersistenceException.class, () -> {
			materialDAO.update(material);
			sessionFactory.getCurrentSession().flush();
		}, "Supplier too long.");
	}

	@Test
	void findById_afterDeleteSucceed_returnsNoEntity() {
		Material material = new Material();
		material.setId(ID_TO_DELETE);

		materialDAO.delete(material);
		sessionFactory.getCurrentSession().flush();
		assertNull(materialDAO.findById(ID_TO_DELETE), "Material must be deleted");
	}

	@Test
	void delete_nonexistentEntity_throwsException() {
		Material material = new Material();
		material.setId(NONEXISTENT_ID);

		assertThrows(PersistenceException.class, () -> {
					materialDAO.delete(material);
					sessionFactory.getCurrentSession().flush();
				},
				"There is no Material with such id.");
	}

	@Test
	void findAll_returnsFullList() {
		List<Material> actualMaterials = materialDAO.findAll();
		assertAll(
				() -> assertEquals(GENERAL_MATERIAL_DATA.getList().size(), actualMaterials.size(), "Wrong " +
						"actual Materials count."),
				() -> assertTrue(GENERAL_MATERIAL_DATA.getList().containsAll(actualMaterials), "Materials do not" +
						" match")
		);
	}
}