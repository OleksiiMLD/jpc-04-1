package ua.omld.jpc.ws;

import jpc.omld.ua.ActivitiesByUserAndBuildingRequest;
import jpc.omld.ua.ActivitiesPriceByBuildingRequest;
import jpc.omld.ua.ActivitiesPriceByReportRequest;
import jpc.omld.ua.ActivitiesPriceByUserRequest;
import jpc.omld.ua.ActivitiesPriceResponse;
import jpc.omld.ua.ActivitiesResponse;
import jpc.omld.ua.ActivityList;
import jpc.omld.ua.ObjectFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.omld.jpc.entity.Activity;
import ua.omld.jpc.entity.Building;
import ua.omld.jpc.entity.Report;
import ua.omld.jpc.entity.User;
import ua.omld.jpc.ws.converter.ActivityConverter;

import javax.jws.WebService;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Supplier;

import static ua.omld.jpc.ws.WSConstants.SUCCESS;

/**
 * Implements {@link ActivityService} interface.
 *
 * @author Oleksii Kostetskyi
 */
@Component("ws.ActivityServiceImpl")
@WebService(name = "ActivityService", endpointInterface = "ua.omld.jpc.ws.ActivityService",
		serviceName = "JPCActivityService", portName = "JPCActivityServicePort")
public class ActivityServiceImpl implements ActivityService {

	private static final Logger LOGGER = LogManager.getLogger();

	private ObjectFactory objectFactory;
	private ActivityConverter activityConverter;

	private ua.omld.jpc.service.ActivityService activityService;

	public ActivityServiceImpl() {
	}

	@Autowired
	public void setObjectFactory(ObjectFactory objectFactory) {
		this.objectFactory = objectFactory;
	}

	@Autowired
	public void setActivityService(ua.omld.jpc.service.ActivityService activityService) {
		this.activityService = activityService;
	}

	@Autowired
	public void setActivityConverter(ActivityConverter activityConverter) {
		this.activityConverter = activityConverter;
	}

	/**
	 * Returns {@link ActivitiesResponse} with a list of found {@link Activity Activities}
	 * for the {@link User} and the {@link Building} with the given id
	 *
	 * @param request@return list of found activities
	 */
	@Override
	public ActivitiesResponse findAllByUserIdAndBuildingId(ActivitiesByUserAndBuildingRequest request) {
		ActivitiesResponse response = objectFactory.createActivitiesResponse();
		try {
			List<Activity> activities = activityService.findAllByUserIdAndBuildingId(request.getUserId(),
					request.getBuildingId());
			ActivityList activityList = objectFactory.createActivityList();
			activityList.getActivity().addAll(activityConverter.createFromEntities(activities));

			response.setActivityList(objectFactory.createActivitiesResponseActivityList(activityList));
			response.setStatus(SUCCESS);
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
			ResponseUtil.setFunctionalFailure(response, e, objectFactory);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			ResponseUtil.setTechnicalFailure(response, e, objectFactory);
		}
		return response;
	}

	/**
	 * Returns total price of all {@link Activity Activities} for the {@link Building} with the given id
	 *
	 * @param request request with the given building id
	 * @return total price of all activities
	 */
	@Override
	public ActivitiesPriceResponse getTotalPriceByBuildingId(ActivitiesPriceByBuildingRequest request) {
		return getTotalPriceResponse(() -> activityService.getTotalPriceByBuildingId(request.getBuildingId()));
	}

	/**
	 * Returns total price of all {@link Activity Activities} for the {@link Report} with the given id
	 *
	 * @param request request with the given report id
	 * @return total price of all activities
	 */
	@Override
	public ActivitiesPriceResponse getTotalPriceByReportId(ActivitiesPriceByReportRequest request) {
		return getTotalPriceResponse(() -> activityService.getTotalPriceByReportId(request.getReportId()));
	}

	/**
	 * Returns total price of all {@link Activity Activities} for the {@link User} with the given id
	 *
	 * @param request request with the given user id
	 * @return total price of all activities
	 */
	@Override
	public ActivitiesPriceResponse getTotalPriceByUserId(ActivitiesPriceByUserRequest request) {
		return getTotalPriceResponse(() -> activityService.getTotalPriceByUserId(request.getUserId()));
	}

	private ActivitiesPriceResponse getTotalPriceResponse(Supplier<BigDecimal> priceSupplier) {
		ActivitiesPriceResponse response = objectFactory.createActivitiesPriceResponse();
		try {
			BigDecimal price = priceSupplier.get();
			if (price != null) {
				response.setPrice(objectFactory.createActivitiesPriceResponsePrice(price));
			}
			response.setStatus(SUCCESS);
		} catch (IllegalArgumentException e) {
			LOGGER.info(e.getMessage());
			ResponseUtil.setFunctionalFailure(response, e, objectFactory);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ResponseUtil.setTechnicalFailure(response, e, objectFactory);
		}
		return response;
	}
}
