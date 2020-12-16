package ua.omld.jpc.ws;

import jpc.omld.ua.ActivityList;
import jpc.omld.ua.Building;
import jpc.omld.ua.BuildingList;
import jpc.omld.ua.ObjectFactory;
import jpc.omld.ua.Report;
import jpc.omld.ua.ReportList;
import jpc.omld.ua.UserReportsRequest;
import jpc.omld.ua.UserReportsResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.omld.jpc.entity.User;
import ua.omld.jpc.ws.converter.ActivityConverter;
import ua.omld.jpc.ws.converter.BuildingConverter;
import ua.omld.jpc.ws.converter.ReportConverter;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

import static ua.omld.jpc.ws.WSConstants.SUCCESS;

/**
 * Implements {@link ReportService} interface.
 *
 * @author Oleksii Kostetskyi
 */
@Component("ws.ReportServiceImpl")
@WebService(name = "ReportService", endpointInterface = "ua.omld.jpc.ws.ReportService",
		serviceName = "JPCReportService", portName = "JPCReportServicePort")
public class ReportServiceImpl implements ReportService {

	private static final Logger LOGGER = LogManager.getLogger();

	private ObjectFactory objectFactory = new ObjectFactory();
	private ReportConverter reportConverter;
	private BuildingConverter buildingConverter;
	private ActivityConverter activityConverter;
	private ua.omld.jpc.service.ReportService reportService;

	public ReportServiceImpl() {
	}

	@Autowired
	public void setObjectFactory(ObjectFactory objectFactory) {
		this.objectFactory = objectFactory;
	}

	@Autowired
	public void setReportService(ua.omld.jpc.service.ReportService reportService) {
		this.reportService = reportService;
	}

	@Autowired
	public void setReportConverter(ReportConverter reportConverter) {
		this.reportConverter = reportConverter;
	}

	@Autowired
	public void setBuildingConverter(BuildingConverter buildingConverter) {
		this.buildingConverter = buildingConverter;
	}

	@Autowired
	public void setActivityConverter(ActivityConverter activityConverter) {
		this.activityConverter = activityConverter;
	}

	/**
	 * Returns {@link UserReportsResponse} with a list of all {@link jpc.omld.ua.Report Reports}
	 * for the {@link User} with the given id
	 *
	 * @param request id of user for witch search reports
	 * @return list of user's reports
	 */
	@Override
	public UserReportsResponse findAllByUserId(UserReportsRequest request) {
		UserReportsResponse response = objectFactory.createUserReportsResponse();
		try {
			List<ua.omld.jpc.entity.Report> reports = reportService.findAllByUserId(request.getUserId());
			ReportList reportList = objectFactory.createReportList();
			reportList.getReport().addAll(convertReportsToJAXB(reports));

			response.setReportList(objectFactory.createUserReportsResponseReportList(reportList));
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

	private List<Report> convertReportsToJAXB(List<ua.omld.jpc.entity.Report> reports) {
		List<Report> jaxbReports = reportConverter.createFromEntities(reports);

		for (int i = 0; i < reports.size(); i++) {
			ua.omld.jpc.entity.Report report = reports.get(i);
			Report jaxbReport = jaxbReports.get(i);
			if (report.getBuildings() != null && report.getBuildings().size() > 0) {
				BuildingList buildingList = objectFactory.createBuildingList();
				buildingList.getBuilding().addAll(convertBuildingsToJAXB(new ArrayList<>(report.getBuildings())));
				jaxbReport.setBuildings(objectFactory.createReportBuildings(buildingList));
			}
		}
		return jaxbReports;
	}

	private List<Building> convertBuildingsToJAXB(List<ua.omld.jpc.entity.Building> buildings) {
		List<Building> jaxbBuildings = buildingConverter.createFromEntities(buildings);

		for (int i = 0; i < buildings.size(); i++) {
			ua.omld.jpc.entity.Building building = buildings.get(i);
			Building jaxbBuilding = jaxbBuildings.get(i);
			if (building.getActivities() != null && building.getActivities().size() > 0) {
				ActivityList activityList = objectFactory.createActivityList();
				activityList.getActivity().addAll(activityConverter.createFromEntities(building.getActivities()));
				jaxbBuilding.setActivities(objectFactory.createBuildingActivities(activityList));
			}
		}
		return jaxbBuildings;
	}
}
