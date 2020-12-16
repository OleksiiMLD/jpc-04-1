package ua.omld.jpc.ws;

import jpc.omld.ua.ActivitiesByUserAndBuildingRequest;
import jpc.omld.ua.ActivitiesPriceByBuildingRequest;
import jpc.omld.ua.ActivitiesPriceByReportRequest;
import jpc.omld.ua.ActivitiesPriceByUserRequest;
import jpc.omld.ua.ActivitiesPriceResponse;
import jpc.omld.ua.ActivitiesResponse;
import ua.omld.jpc.entity.Activity;
import ua.omld.jpc.entity.Building;
import ua.omld.jpc.entity.Report;
import ua.omld.jpc.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Provides Web-Service interface for {@link Activity}
 *
 * @author Oleksii Kostetskyi
 */
@SOAPBinding
@WebService(name = "ActivityService", serviceName = "JPCActivityService", portName = "JPCActivityServicePort")
public interface ActivityService {

	/**
	 * Returns {@link ActivitiesResponse} with a list of found {@link Activity Activities}
	 * for the {@link User} and the {@link Building} with the given id
	 *
	 * @param request request with the given user id and building id
	 * @return list of found activities
	 */
	@WebMethod(operationName = "GetActivitiesByUserAndBuilding")
	@WebResult(name = "GetActivitiesByUserAndBuildingResponseAttributes")
	ActivitiesResponse findAllByUserIdAndBuildingId(
			@WebParam(name = "GetActivitiesByUserAndBuildingRequestAttributes") ActivitiesByUserAndBuildingRequest request);

	/**
	 * Returns total price of all {@link Activity Activities} for the {@link Building} with the given id
	 *
	 * @param request request with the given building id
	 * @return total price of all activities
	 */
	@WebMethod(operationName = "GetTotalPriceByBuilding")
	@WebResult(name = "GetTotalPriceResponseAttributes")
	ActivitiesPriceResponse getTotalPriceByBuildingId(
			@WebParam(name = "GetTotalPriceByBuildingRequestAttributes") ActivitiesPriceByBuildingRequest request);

	/**
	 * Returns total price of all {@link Activity Activities} for the {@link Report} with the given id
	 *
	 * @param request request with the given report id
	 * @return total price of all activities
	 */
	@WebMethod(operationName = "GetTotalPriceByReport")
	@WebResult(name = "GetTotalPriceResponseAttributes")
	ActivitiesPriceResponse getTotalPriceByReportId(
			@WebParam(name = "GetTotalPriceByReportRequestAttributes") ActivitiesPriceByReportRequest request);

	/**
	 * Returns total price of all {@link Activity Activities} for the {@link User} with the given id
	 *
	 * @param request request with the given user id
	 * @return total price of all activities
	 */
	@WebMethod(operationName = "GetTotalPriceByUser")
	@WebResult(name = "GetTotalPriceResponseAttributes")
	ActivitiesPriceResponse getTotalPriceByUserId(
			@WebParam(name = "GetTotalPriceByUserRequestAttributes") ActivitiesPriceByUserRequest request);
}
