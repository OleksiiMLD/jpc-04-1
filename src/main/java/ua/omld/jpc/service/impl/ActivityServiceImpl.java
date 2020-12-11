package ua.omld.jpc.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.omld.jpc.dao.ActivityDAO;
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
	private final ActivityDAO activityDAO;

	public ActivityServiceImpl(ActivityDAO activityDAO) {
		this.activityDAO = activityDAO;
	}

	/**
	 * Returns list of found {@link Activity Activities} by the given {@link User} and {@link Building}
	 *
	 * @param user     given user
	 * @param building given building
	 * @return list of found activities
	 */
	@Override
	public List<Activity> findAllByUserAndBuilding(User user, Building building) {
		return activityDAO.findAllByUserAndBuilding(user, building);
	}

	/**
	 * Returns total price of all {@link Activity Activities} for the given {@link Building}
	 *
	 * @param building given building
	 * @return total price of all activities
	 */
	@Override
	public BigDecimal getTotalPriceByBuilding(Building building) {
		return activityDAO.getTotalPriceByBuilding(building);
	}

	/**
	 * Returns total price of all {@link Activity Activities} for the given {@link Report}
	 *
	 * @param report given report
	 * @return total price of all activities
	 */
	@Override
	public BigDecimal getTotalPriceByReport(Report report) {
		return activityDAO.getTotalPriceByReport(report);
	}

	/**
	 * Returns total price of all {@link Activity Activities} for the given {@link User}
	 *
	 * @param user given user
	 * @return total price of all activities
	 */
	@Override
	public BigDecimal getTotalPriceByUser(User user) {
		return activityDAO.getTotalPriceByUser(user);
	}
}
