package ua.omld.jpc.dto.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import ua.omld.jpc.dto.BuildingDto;
import ua.omld.jpc.entity.Building;

/**
 * @author Oleksii Kostetskyi
 */
@Component
public class BuildingConverter extends Converter<BuildingDto, Building> {

	public BuildingConverter() {
		super(BuildingConverter::convertToEntity, BuildingConverter::convertToDto);
	}

	private static BuildingDto convertToDto(Building building) {
		if (building == null) {
			return null;
		}
		BuildingDto buildingDto = new BuildingDto();
		BeanUtils.copyProperties(building, buildingDto,"activities");
		return buildingDto;
	}

	private static Building convertToEntity(BuildingDto buildingDto) {
		if (buildingDto == null) {
			return null;
		}
		Building building = new Building();
		BeanUtils.copyProperties(buildingDto, building);
		return building;
	}
}