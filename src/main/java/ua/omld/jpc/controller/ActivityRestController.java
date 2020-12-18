package ua.omld.jpc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.omld.jpc.dto.ActivityDto;
import ua.omld.jpc.dto.converter.ActivityConverter;
import ua.omld.jpc.service.ActivityService;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Supplier;

import static ua.omld.jpc.configuration.WebConfig.REST_COMMON_PREFIX;

/**
 * @author Oleksii Kostetskyi
 */
@RestController
@RequestMapping(REST_COMMON_PREFIX)
public class ActivityRestController {

	private ActivityConverter activityConverter;
	private ActivityService activityService;

	@Autowired
	public void setActivityConverter(ActivityConverter activityConverter) {
		this.activityConverter = activityConverter;
	}

	@Autowired
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}

	@GetMapping(value = "users/{userId:[\\d]+}/buildings/{buildingId:[\\d]+}/activities",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ActivityDto>> findAllByUserAndBuilding(@PathVariable("userId") Long userId,
																	  @PathVariable("buildingId") Long buildingId) {
		List<ActivityDto> activityDtoList =
				activityConverter.createFromEntities(activityService.findAllByUserIdAndBuildingId(userId, buildingId));
		HttpStatus status = activityDtoList.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
		return new ResponseEntity<>(activityDtoList, status);
	}

	@GetMapping(value = "buildings/{buildingId:[\\d]+}/activities/totalprice",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BigDecimal> getTotalPriceByBuilding(@PathVariable("buildingId") Long buildingId) {
		return getBigDecimalResponse(() -> activityService.getTotalPriceByBuildingId(buildingId));
	}

	@GetMapping(value = "users/{userId:[\\d]+}/activities/totalprice",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BigDecimal> getTotalPriceByUser(@PathVariable("userId") Long userId) {
		return getBigDecimalResponse(() -> activityService.getTotalPriceByUserId(userId));
	}

	@GetMapping(value = "reports/{reportId:[\\d]+}/activities/totalprice",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BigDecimal> getTotalPriceByReport(@PathVariable("reportId") Long reportId) {
		return getBigDecimalResponse(() -> activityService.getTotalPriceByReportId(reportId));
	}

	public ResponseEntity<BigDecimal> getBigDecimalResponse(Supplier<BigDecimal> decimalSupplier) {
		BigDecimal bigDecimal = decimalSupplier.get();
		HttpStatus status = bigDecimal == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
		return new ResponseEntity<>(bigDecimal, status);
	}
}
