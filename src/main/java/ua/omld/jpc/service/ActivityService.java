package ua.omld.jpc.service;

import ua.omld.jpc.entity.Activity;
import ua.omld.jpc.entity.Building;
import ua.omld.jpc.entity.Report;
import ua.omld.jpc.entity.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * Provides Service interface for {@link Activity}
 *
 * @author Oleksii Kostetskyi
 */
public interface ActivityService {

	/**
	 * Returns list of found {@link Activity Activities} for the {@link User} with the given id
	 * and the {@link Building} with the given id
	 *
	 * @param userId     given user id
	 * @param buildingId given building id
	 * @return list of found activities
	 */
	List<Activity> findAllByUserIdAndBuildingId(Long userId, Long buildingId);

	/**
	 * Returns total price of all {@link Activity Activities} for the {@link Building} with the given id
	 *
	 * @param buildingId given building id
	 * @return total price of all activities
	 */
	BigDecimal getTotalPriceByBuildingId(Long buildingId);

	/**
	 * Returns total price of all {@link Activity Activities} for the {@link Report} with the given id
	 *
	 * @param reportId given report id
	 * @return total price of all activities
	 */
	BigDecimal getTotalPriceByReportId(Long reportId);

	/**
	 * Returns total price of all {@link Activity Activities} for the given {@link User}
	 *
	 * @param userId given user id
	 * @return total price of all activities
	 */
	BigDecimal getTotalPriceByUserId(Long userId);
}