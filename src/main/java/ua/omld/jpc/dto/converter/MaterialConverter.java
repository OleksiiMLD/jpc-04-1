package ua.omld.jpc.dto.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import ua.omld.jpc.dto.MaterialDto;
import ua.omld.jpc.entity.Material;
import ua.omld.jpc.entity.MaterialMeasurement;

/**
 * @author Oleksii Kostetskyi
 */
@Component
public class MaterialConverter  extends Converter<MaterialDto, Material> {

	public MaterialConverter() {
		super(MaterialConverter::convertToEntity, MaterialConverter::convertToDto);
	}

	private static MaterialDto convertToDto(Material material) {
		if (material == null) {
			return null;
		}
		MaterialDto materialDto = new MaterialDto();
		BeanUtils.copyProperties(material, materialDto);
		materialDto.setMeasurement(material.getMeasurement() == null ? null : material.getMeasurement().name());
		return materialDto;
	}

	private static Material convertToEntity(MaterialDto materialDto) {
		if (materialDto == null) {
			return null;
		}
		Material material = new Material();
		BeanUtils.copyProperties(materialDto, material);
		material.setMeasurement(materialDto.getMeasurement() == null ? null :
				MaterialMeasurement.valueOf(materialDto.getMeasurement().toUpperCase()));
		return material;
	}
}
