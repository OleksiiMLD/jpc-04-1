package ua.omld.jpc.service;

import ua.omld.jpc.entity.Building;

import java.math.BigDecimal;
import java.util.List;

/**
 * Provides Service interface for {@link Building}
 *
 * @author Oleksii Kostetskyi
 */
public interface BuildingService {

	/**
	 * Returns list of all {@link Building Buildings} for which total price
	 * of all activities is more than specified value.
	 *
	 * @param price given total price of activities
	 * @return list of buildings
	 */
	List<Building> findAllByGreaterTotalActivitiesPrice(BigDecimal price);

	/**
	 * Sets all {@link Building Buildings} to non-active state for which total price
	 * of all activities is more than specified value. Returns count of updated
	 * {@link Building buildings}.
	 *
	 * @param price given total price of activities
	 * @return number of updated buildings
	 */
	Integer inactivateAllByGreaterTotalActivitiesPrice(BigDecimal price);
}
