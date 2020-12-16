package ua.omld.jpc.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.ws.Endpoint;

/**
 * Implements {@link ServicePublisher}
 *
 * @author Oleksii Kostetskyi
 */
@Component
public class ServicePublisherImpl implements ServicePublisher {

	private ReportService reportService;
	private ActivityService activityService;
	private BuildingService buildingService;
	private String wsDefaultUri;

	@Autowired
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	@Autowired
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}

	@Autowired
	public void setBuildingService(BuildingService buildingService) {
		this.buildingService = buildingService;
	}

	@Autowired
	public void setWsDefaultUri(String wsDefaultUri) {
		this.wsDefaultUri = wsDefaultUri;
	}

	@Override
	public void publishReportService() {
		Endpoint.publish(wsDefaultUri + "ReportService", reportService);
	}

	@Override
	public void publishActivityService() {
		Endpoint.publish(wsDefaultUri + "ActivityService", activityService);
	}

	@Override
	public void publishBuildingService() {
		Endpoint.publish(wsDefaultUri + "BuildingService", buildingService);
	}
}
