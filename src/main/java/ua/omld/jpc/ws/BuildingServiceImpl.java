package ua.omld.jpc.ws;

import jpc.omld.ua.BuildingList;
import jpc.omld.ua.BuildingsResponse;
import jpc.omld.ua.ObjectFactory;
import jpc.omld.ua.TotalActivitiesPriceRequest;
import jpc.omld.ua.UpdatedBuildingsResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.omld.jpc.entity.Building;
import ua.omld.jpc.ws.converter.BuildingConverter;

import javax.jws.WebService;
import java.util.List;

import static ua.omld.jpc.ws.WSConstants.SUCCESS;

/**
 * Implements {@link BuildingService} interface.
 *
 * @author Oleksii Kostetskyi
 */
@Component("ws.BuildingServiceImpl")
@WebService(name = "BuildingService", endpointInterface = "ua.omld.jpc.ws.BuildingService",
		serviceName = "JPCBuildingService", portName = "JPCBuildingServicePort")
public class BuildingServiceImpl implements BuildingService {

	private static final Logger LOGGER = LogManager.getLogger();

	private ObjectFactory objectFactory = new ObjectFactory();
	private BuildingConverter buildingConverter;
	private ua.omld.jpc.service.BuildingService buildingService;

	public BuildingServiceImpl() {
	}

	@Autowired
	public void setObjectFactory(ObjectFactory objectFactory) {
		this.objectFactory = objectFactory;
	}

	@Autowired
	public void setBuildingService(ua.omld.jpc.service.BuildingService buildingService) {
		this.buildingService = buildingService;
	}

	@Autowired
	public void setBuildingConverter(BuildingConverter buildingConverter) {
		this.buildingConverter = buildingConverter;
	}

	/**
	 * Returns {@link BuildingsResponse} with a list of all {@link ua.omld.jpc.entity.Building Buildings}
	 * for which total price of all activities is more than specified value.
	 *
	 * @param request request with the given total price of activities
	 * @return list of buildings
	 */
	@Override
	public BuildingsResponse findAllByGreaterTotalActivitiesPrice(TotalActivitiesPriceRequest request) {
		BuildingsResponse response = objectFactory.createBuildingsResponse();
		try {
			List<Building> buildings = buildingService.findAllByGreaterTotalActivitiesPrice(request.getPrice());
			BuildingList buildingList = objectFactory.createBuildingList();
			buildingList.getBuilding().addAll(buildingConverter.createFromEntities(buildings));

			response.setBuildingList(objectFactory.createBuildingsResponseBuildingList(buildingList));
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

	/**
	 * Sets all {@link ua.omld.jpc.entity.Building Buildings} to non-active state for which total price
	 * of all activities is more than specified value. Returns count of updated
	 * {@link ua.omld.jpc.entity.Building buildings}.
	 *
	 * @param request request with the given total price of activities
	 * @return number of updated buildings
	 */
	@Override
	public UpdatedBuildingsResponse inactivateAllByGreaterTotalActivitiesPrice(TotalActivitiesPriceRequest request) {
		UpdatedBuildingsResponse response = objectFactory.createUpdatedBuildingsResponse();
		try {
			Integer buildingsCount = buildingService.inactivateAllByGreaterTotalActivitiesPrice(request.getPrice());
			response.setBuildingsCount(objectFactory.createUpdatedBuildingsResponseBuildingsCount(buildingsCount));
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
