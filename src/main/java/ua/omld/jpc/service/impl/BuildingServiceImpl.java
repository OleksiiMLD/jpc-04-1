package ua.omld.jpc.service.impl;

import ua.omld.jpc.dao.BuildingDAO;
import ua.omld.jpc.entity.Building;
import ua.omld.jpc.service.BuildingService;

import java.math.BigDecimal;
import java.util.List;

/**
 * Implements {@link BuildingService} interface.
 *
 * @author Oleksii Kostetskyi
 */
public class BuildingServiceImpl implements BuildingService {

	private static final String PROVIDE_PRICE = "Please provide price.";

	private BuildingDAO buildingDAO;

	public BuildingServiceImpl(BuildingDAO buildingDAO) {
		this.buildingDAO = buildingDAO;
	}

	/**
	 * Returns list of all {@link Building Buildings} for which total price
	 * of all activities is more than specified value.
	 *
	 * @param price given total price of activities
	 * @return list of buildings
	 */
	@Override
	public List<Building> findAllByGreaterTotalActivitiesPrice(BigDecimal price) {
		if (price == null) {
			throw new IllegalArgumentException(PROVIDE_PRICE);
		}
		return buildingDAO.executeInsideTransaction(() -> buildingDAO.findAllByGreaterTotalActivitiesPrice(price),
				"Error find buildings.");
	}

	/**
	 * Sets all {@link Building Buildings} to non-active state for which total price
	 * of all activities is more than specified value. Returns count of updated
	 * {@link Building buildings}.
	 *
	 * @param price given total price of activities
	 * @return number of updated buildings
	 */
	@Override
	public Integer inactivateAllByGreaterTotalActivitiesPrice(BigDecimal price) {
		if (price == null) {
			throw new IllegalArgumentException(PROVIDE_PRICE);
		}
		if (BigDecimal.ZERO.equals(price)) {
			throw new IllegalArgumentException("Price must be greater then zero.");
		}
		return buildingDAO.executeInsideTransaction(
				() -> buildingDAO.inactivateAllByGreaterTotalActivitiesPrice(price),
				"Error inactivating buildings.");
	}
}