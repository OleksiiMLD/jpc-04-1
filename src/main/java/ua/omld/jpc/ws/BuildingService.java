package ua.omld.jpc.ws;

import jpc.omld.ua.BuildingsResponse;
import jpc.omld.ua.TotalActivitiesPriceRequest;
import jpc.omld.ua.UpdatedBuildingsResponse;
import ua.omld.jpc.entity.Building;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Provides Web-Service interface for {@link Building}
 *
 * @author Oleksii Kostetskyi
 */
@SOAPBinding
@WebService(name = "BuildingService", serviceName = "JPCBuildingService", portName = "JPCBuildingServicePort")
public interface BuildingService {

	/**
	 * Returns {@link BuildingsResponse} with a list of all {@link Building Buildings} for which
	 * total price of all activities is more than specified value.
	 *
	 * @param request request with the given total price of activities
	 * @return list of buildings
	 */
	@WebMethod(operationName = "GetBuildingsByTotalActivitiesPrice")
	@WebResult(name = "GetBuildingsByTotalActivitiesPriceResponseAttributes")
	BuildingsResponse findAllByGreaterTotalActivitiesPrice(
			@WebParam(name = "GetBuildingsByTotalActivitiesPriceRequestAttributes") TotalActivitiesPriceRequest request);

	/**
	 * Sets all {@link Building Buildings} to non-active state for which total price
	 * of all activities is more than specified value. Returns count of updated
	 * {@link Building buildings}.
	 *
	 * @param request request with the given total price of activities
	 * @return number of updated buildings
	 */
	@WebMethod(operationName = "InactivateBuildingsByTotalActivitiesPrice")
	@WebResult(name = "InactivateBuildingsByTotalActivitiesPriceResponseAttributes")
	UpdatedBuildingsResponse inactivateAllByGreaterTotalActivitiesPrice(
			@WebParam(name = "InactivateBuildingsByTotalActivitiesPriceRequestAttributes") TotalActivitiesPriceRequest request);
}
