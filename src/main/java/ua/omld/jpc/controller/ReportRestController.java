package ua.omld.jpc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.omld.jpc.dto.BuildingDto;
import ua.omld.jpc.dto.ReportDto;
import ua.omld.jpc.dto.converter.ActivityConverter;
import ua.omld.jpc.dto.converter.BuildingConverter;
import ua.omld.jpc.dto.converter.ReportConverter;
import ua.omld.jpc.entity.Building;
import ua.omld.jpc.entity.Report;
import ua.omld.jpc.service.ReportService;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static ua.omld.jpc.configuration.WebConfig.REST_COMMON_PREFIX;

/**
 * @author Oleksii Kostetskyi
 */
@RestController
@RequestMapping(REST_COMMON_PREFIX)
public class ReportRestController {

	private ReportService reportService;
	private ActivityConverter activityConverter;
	private BuildingConverter buildingConverter;
	private ReportConverter reportConverter;

	@Autowired
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	@Autowired
	public void setActivityConverter(ActivityConverter activityConverter) {
		this.activityConverter = activityConverter;
	}

	@Autowired
	public void setBuildingConverter(BuildingConverter buildingConverter) {
		this.buildingConverter = buildingConverter;
	}

	@Autowired
	public void setReportConverter(ReportConverter reportConverter) {
		this.reportConverter = reportConverter;
	}

	@GetMapping(value = "users/{userId:\\d+}/reports",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReportDto>> getReportsByUser(@PathVariable("userId") long userId) {
		List<ReportDto> reportDtoList = convertReportsToDto(reportService.findAllByUserId(userId));
		HttpStatus status = reportDtoList.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
		return new ResponseEntity<>(reportDtoList, status);
	}

	private List<ReportDto> convertReportsToDto(List<Report> reports) {
		List<ReportDto> reportDtoList = reportConverter.createFromEntities(reports);
		Iterator<ReportDto> iterDto = reportDtoList.iterator();
		Iterator<Report> iterRep = reports.iterator();
		while (iterDto.hasNext() && iterRep.hasNext()) {
			Report report = iterRep.next();
			ReportDto reportDto = iterDto.next();
			if (report.getBuildings() != null && report.getBuildings().size() > 0) {
				reportDto.setBuildings(Set.copyOf(convertBuildingsToDto(report.getBuildings())));
			}
		}
		return reportDtoList;
	}

	private List<BuildingDto> convertBuildingsToDto(Set<Building> buildings) {
		List<BuildingDto> buildingDtoList = buildingConverter.createFromEntities(buildings);
		Iterator<BuildingDto> iterDto = buildingDtoList.iterator();
		Iterator<Building> iterRep = buildings.iterator();
		while (iterDto.hasNext() && iterRep.hasNext()) {
			Building building = iterRep.next();
			BuildingDto buildingDto = iterDto.next();
			if (building.getActivities() != null && building.getActivities().size() > 0) {
				buildingDto.setActivities(Set.copyOf(activityConverter.createFromEntities(building.getActivities())));
			}
		}
		return buildingDtoList;
	}
}
