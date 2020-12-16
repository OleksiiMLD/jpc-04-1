package ua.omld.jpc.ws.converter;

import jpc.omld.ua.Building;
import jpc.omld.ua.ObjectFactory;
import org.springframework.stereotype.Component;

/**
 * @author Oleksii Kostetskyi
 */
@Component
public class BuildingConverter extends Converter<Building, ua.omld.jpc.entity.Building> {

	public BuildingConverter(ObjectFactory objectFactory) {
		super(BuildingConverter::convertToEntity, BuildingConverter::convertToJAXB);
		BuildingConverter.objectFactory = objectFactory;
	}

	private static Building convertToJAXB(ua.omld.jpc.entity.Building building) {
		if (building == null) {
			return null;
		}
		Building jaxbBuilding = objectFactory.createBuilding();
		jaxbBuilding.setId(objectFactory.createBuildingId(building.getId()));
		jaxbBuilding.setName(objectFactory.createBuildingName(building.getName()));
		jaxbBuilding.setActive(objectFactory.createBuildingActive(building.getActive()));
		return jaxbBuilding;
	}

	private static ua.omld.jpc.entity.Building convertToEntity(Building jaxbBuilding) {
		if (jaxbBuilding == null) {
			return null;
		}
		ua.omld.jpc.entity.Building building = new ua.omld.jpc.entity.Building();
		building.setId(jaxbBuilding.getId().getValue());
		building.setName(jaxbBuilding.getName().getValue());
		building.setActive(jaxbBuilding.getActive().getValue());
		return building;
	}
}