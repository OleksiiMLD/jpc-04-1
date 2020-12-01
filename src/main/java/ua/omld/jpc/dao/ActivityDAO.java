package ua.omld.jpc.dao;

import ua.omld.jpc.entity.Activity;
import ua.omld.jpc.entity.Building;
import ua.omld.jpc.entity.Report;
import ua.omld.jpc.entity.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * Provides DAO interface for {@link Activity}
 *
 * @author Oleksii Kostetskyi
 */
public interface ActivityDAO extends GenericDAO<Activity, Long> {

	/**
	 * Returns list of found {@link Activity Activities} by the given {@link User} and {@link Building}
	 *
	 * @param user     given user
	 * @param building given building
	 * @return list of found activities
	 */
	List<Activity> findAllByUserAndBuilding(User user, Building building);

	/**
	 * Returns total price of all {@link Activity Activities} for the given {@link Building}
	 *
	 * @param building given building
	 * @return total price of all activities
	 */
	BigDecimal getTotalPriceByBuilding(Building building);

	/**
	 * Returns total price of all {@link Activity Activities} for the given {@link Report}
	 *
	 * @param report given report
	 * @return total price of all activities
	 */
	BigDecimal getTotalPriceByReport(Report report);

	/**
	 * Returns total price of all {@link Activity Activities} for the given {@link User}
	 *
	 * @param user given user
	 * @return total price of all activities
	 */
	BigDecimal getTotalPriceByUser(User user);
}