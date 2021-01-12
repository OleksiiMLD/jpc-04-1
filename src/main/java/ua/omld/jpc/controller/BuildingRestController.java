package ua.omld.jpc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.omld.jpc.dto.BuildingDto;
import ua.omld.jpc.dto.converter.BuildingConverter;
import ua.omld.jpc.service.BuildingService;

import javax.annotation.security.RolesAllowed;
import java.math.BigDecimal;
import java.util.List;

import static ua.omld.jpc.configuration.WebConfig.REST_COMMON_PREFIX;

/**
 * @author Oleksii Kostetskyi
 */
@RestController
@RequestMapping(REST_COMMON_PREFIX)
public class BuildingRestController {

	private BuildingConverter buildingConverter;
	private BuildingService buildingService;

	@Autowired
	public void setBuildingConverter(BuildingConverter buildingConverter) {
		this.buildingConverter = buildingConverter;
	}

	@Autowired
	public void setBuildingService(BuildingService buildingService) {
		this.buildingService = buildingService;
	}

	@GetMapping(value = "buildings",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BuildingDto>> findAllByGreaterTotalActivitiesPrice(@RequestParam(
			"totalActivitiesPriceGte") BigDecimal totalPrice) {
		List<BuildingDto> buildingDtoList =
				buildingConverter.createFromEntities(buildingService.findAllByGreaterTotalActivitiesPrice(totalPrice));
		HttpStatus status = buildingDtoList.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
		return new ResponseEntity<>(buildingDtoList, status);
	}

	@RolesAllowed("MANAGER")
	@PatchMapping(value = "buildings",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> inactivateAllByGreaterTotalActivitiesPrice(@RequestParam("totalActivitiesPriceGte") BigDecimal totalPrice,
																			  @RequestParam("isActive") boolean isActive) {
		if (isActive) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(buildingService.inactivateAllByGreaterTotalActivitiesPrice(totalPrice), HttpStatus.OK);
	}
}
