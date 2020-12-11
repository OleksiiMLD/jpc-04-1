package ua.omld.jpc.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.omld.jpc.dao.ActivityDAO;
import ua.omld.jpc.dao.BuildingDAO;
import ua.omld.jpc.dao.ReportDAO;
import ua.omld.jpc.dao.UserDAO;
import ua.omld.jpc.entity.Activity;
import ua.omld.jpc.entity.Building;
import ua.omld.jpc.entity.Report;
import ua.omld.jpc.entity.User;
import ua.omld.jpc.service.ActivityService;

import java.math.BigDecimal;
import java.util.List;

/**
 * Implements {@link ActivityService} interface.
 *
 * @author Oleksii Kostetskyi
 */
public class ActivityServiceImpl implements ActivityService {

	private static final Logger LOGGER = LogManager.getLogger();

	private static final String WRONG_BUILDING_ID = "Wrong building id: ";
	private static final String WRONG_REPORT_ID = "Wrong report id: ";
	private static final String WRONG_USER_ID = "Wrong user id: ";
	private static final String PROVIDE_USER_ID = "Please provide user id.";
	private static final String PROVIDE_BUILDING_ID = "Please provide user id.";
	private static final String PROVIDE_REPORT_ID = "Please provide report id.";

	private ActivityDAO activityDAO;
	private BuildingDAO buildingDAO;
	private ReportDAO reportDAO;
	private UserDAO userDAO;

	public ActivityServiceImpl(ActivityDAO activityDAO, BuildingDAO buildingDAO, ReportDAO reportDAO, UserDAO userDAO) {
		this.activityDAO = activityDAO;
		this.buildingDAO = buildingDAO;
		this.reportDAO = reportDAO;
		this.userDAO = userDAO;
	}

	/**
	 * Returns list of found {@link Activity Activities} for the {@link User} with the given id
	 * and the {@link Building} with the given id
	 *
	 * @param userId     given user id
	 * @param buildingId given building id
	 * @return list of found activities
	 */
	@Override
	public List<Activity> findAllByUserIdAndBuildingId(Long userId, Long buildingId) {
		if (userId == null) {
			throw new IllegalArgumentException(PROVIDE_USER_ID);
		}
		if (buildingId == null) {
			throw new IllegalArgumentException(PROVIDE_BUILDING_ID);
		}
		return activityDAO.executeInsideTransaction(
				() -> {
					User user = userDAO.findById(userId);
					if (user == null) {
						LOGGER.debug(WRONG_USER_ID + userId);
						throw new IllegalArgumentException(WRONG_USER_ID + userId);
					}
					Building building = buildingDAO.findById(buildingId);
					if (building == null) {
						LOGGER.debug(WRONG_BUILDING_ID + buildingId);
						throw new IllegalArgumentException(WRONG_BUILDING_ID + buildingId);
					}
					return activityDAO.findAllByUserAndBuilding(user, building);
				},
				"Error find activities by user and building."
		);
	}

	/**
	 * Returns total price of all {@link Activity Activities} for the {@link Building} with the given id
	 *
	 * @param buildingId given building id
	 * @return total price of all activities
	 */
	@Override
	public BigDecimal getTotalPriceByBuildingId(Long buildingId) {
		if (buildingId == null) {
			throw new IllegalArgumentException(PROVIDE_BUILDING_ID);
		}
		return activityDAO.executeInsideTransaction(
				() -> {
					Building building = buildingDAO.findById(buildingId);
					if (building == null) {
						LOGGER.debug(WRONG_BUILDING_ID + buildingId);
						throw new IllegalArgumentException(WRONG_BUILDING_ID + buildingId);
					}
					return activityDAO.getTotalPriceByBuilding(building);
				},
				"Error find total price by building."
		);
	}

	/**
	 * Returns total price of all {@link Activity Activities} for the {@link Report} with the given id
	 *
	 * @param reportId given report id
	 * @return total price of all activities
	 */
	@Override
	public BigDecimal getTotalPriceByReportId(Long reportId) {
		if (reportId == null) {
			throw new IllegalArgumentException(PROVIDE_REPORT_ID);
		}
		return activityDAO.executeInsideTransaction(
				() -> {
					Report report = reportDAO.findById(reportId);
					if (report == null) {
						LOGGER.debug(WRONG_REPORT_ID + reportId);
						throw new IllegalArgumentException(WRONG_REPORT_ID + reportId);
					}
					return activityDAO.getTotalPriceByReport(report);
				},
				"Error find total price by report."
		);
	}

	/**
	 * Returns total price of all {@link Activity Activities} for the given {@link User}
	 *
	 * @param userId given user id
	 * @return total price of all activities
	 */
	@Override
	public BigDecimal getTotalPriceByUserId(Long userId) {
		if (userId == null) {
			throw new IllegalArgumentException(PROVIDE_USER_ID);
		}
		return activityDAO.executeInsideTransaction(
				() -> {
					User user = userDAO.findById(userId);
					if (user == null) {
						LOGGER.debug(WRONG_USER_ID + userId);
						throw new IllegalArgumentException(WRONG_USER_ID + userId);
					}
					return activityDAO.getTotalPriceByUser(user);
				},
				"Error find total price by user."
		);
	}
}