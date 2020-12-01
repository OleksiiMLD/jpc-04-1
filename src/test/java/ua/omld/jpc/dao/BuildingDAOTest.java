package ua.omld.jpc.dao;

import org.junit.jupiter.api.Test;
import ua.omld.jpc.dao.hibernate.BuildingDAOImpl;
import ua.omld.jpc.entity.Building;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ua.omld.jpc.testdata.BuildingTestData.BUILDING_DATA_FOR_PRICE;
import static ua.omld.jpc.testdata.BuildingTestData.TOTAL_ACTIVITIES_PRICE;

/**
 * @author Oleksii Kostetskyi
 */
class BuildingDAOTest extends DaoTest {

	BuildingDAO buildingDAO = new BuildingDAOImpl(sessionFactory);

	@Test
	void findAllByGreaterTotalActivitiesPrice_somePrice_rightBuildings() {
		List<Building> actualBuildings = buildingDAO.findAllByGreaterTotalActivitiesPrice(TOTAL_ACTIVITIES_PRICE);
		assertAll(() -> assertEquals(BUILDING_DATA_FOR_PRICE.getList().size(), actualBuildings.size(), "Wrong " +
						"buildings count."),
				() -> assertTrue(BUILDING_DATA_FOR_PRICE.getList().containsAll(actualBuildings), "Wrong actual " +
						"buildings list.")
		);
	}

	@Test
	void inactivateAllByGreaterTotalActivitiesPrice_somePrice_rightCount() {
		assertEquals(BUILDING_DATA_FOR_PRICE.getList().size(),
				buildingDAO.inactivateAllByGreaterTotalActivitiesPrice(TOTAL_ACTIVITIES_PRICE), "Wrong number of " +
						"inactivated buildings.");
	}

	@Test
	void findAllByGreaterTotalActivitiesPrice_afterInactivateBuildings_allFoundBuildingInactive() {
		buildingDAO.inactivateAllByGreaterTotalActivitiesPrice(TOTAL_ACTIVITIES_PRICE);
		assertEquals(0,
				buildingDAO.findAllByGreaterTotalActivitiesPrice(TOTAL_ACTIVITIES_PRICE).stream()
						.filter(Building::getActive).count(), "All found buildings must be inactive");
	}
}