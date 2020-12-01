package ua.omld.jpc.dao;

import org.junit.jupiter.api.Test;
import ua.omld.jpc.dao.hibernate.ActivityDAOImpl;
import ua.omld.jpc.entity.Activity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ua.omld.jpc.testdata.ActivityTestData.BUILDING_2_ACTIVITY_DATA;
import static ua.omld.jpc.testdata.ActivityTestData.TOTAL_ACTIVITIES_PRICE_FOR_BUILDING_2;
import static ua.omld.jpc.testdata.ActivityTestData.TOTAL_ACTIVITIES_PRICE_FOR_REPORT_3;
import static ua.omld.jpc.testdata.ActivityTestData.TOTAL_ACTIVITIES_PRICE_FOR_USER_1;
import static ua.omld.jpc.testdata.BuildingTestData.GENERAL_BUILDING_DATA;
import static ua.omld.jpc.testdata.ReportTestData.GENERAL_REPORT_DATA;
import static ua.omld.jpc.testdata.UserTestData.GENERAL_USER_DATA;

/**
 * @author Oleksii Kostetskyi
 */
class ActivityDAOTest extends DaoTest {

	private final ActivityDAO activityDAO = new ActivityDAOImpl(sessionFactory);

	@Test
	void findAllByUserAndBuilding_relatedUserAndBuilding_rightActivitiesList() {
		List<Activity> actualActivities = activityDAO.findAllByUserAndBuilding(GENERAL_USER_DATA.get("USER_1"),
				GENERAL_BUILDING_DATA.get("BUILDING_2"));
		assertAll(
				() -> assertEquals(BUILDING_2_ACTIVITY_DATA.getList().size(), actualActivities.size(), "Wrong " +
						"actual Activities count."),
				() -> assertTrue(BUILDING_2_ACTIVITY_DATA.getList().containsAll(actualActivities), "Activities do not" +
						" match")
		);
	}

	@Test
	void findAllByUserAndBuilding_unrelatedUserAndBuilding_noActivities() {
		List<Activity> activities = activityDAO.findAllByUserAndBuilding(GENERAL_USER_DATA.get("USER_1"),
				GENERAL_BUILDING_DATA.get("BUILDING_5"));

		assertEquals(0, activities.size(), "Must be no activities.");
	}

	@Test
	void getTotalPriceByBuilding_existentBuilding_rightPrice() {
		assertEquals(TOTAL_ACTIVITIES_PRICE_FOR_BUILDING_2, activityDAO.getTotalPriceByBuilding(GENERAL_BUILDING_DATA.get(
				"BUILDING_2")), "Wrong total activities price.");
	}

	@Test
	void getTotalPriceByReport_existentReport_rightPrice() {
		assertEquals(TOTAL_ACTIVITIES_PRICE_FOR_REPORT_3,
				activityDAO.getTotalPriceByReport(GENERAL_REPORT_DATA.get("REPORT_3")), "Wrong total activities price.");
	}

	@Test
	void getTotalPriceByUser_userWithReports_rightPrice() {
		assertEquals(TOTAL_ACTIVITIES_PRICE_FOR_USER_1, activityDAO.getTotalPriceByUser(GENERAL_USER_DATA.get("USER_2")),
				"Wrong total activities price.");
	}

	@Test
	void getTotalPriceByUser_userWithoutReports_returnsNull() {
		assertNull(activityDAO.getTotalPriceByUser(GENERAL_USER_DATA.get("USER_3")), "There are no activities for the user.");
	}
}