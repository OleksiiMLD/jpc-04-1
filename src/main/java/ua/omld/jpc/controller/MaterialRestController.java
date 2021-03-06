package ua.omld.jpc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.omld.jpc.dto.MaterialDto;
import ua.omld.jpc.dto.converter.MaterialConverter;
import ua.omld.jpc.entity.Material;
import ua.omld.jpc.service.MaterialService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ua.omld.jpc.configuration.WebConfig.REST_COMMON_PREFIX;

/**
 * @author Oleksii Kostetskyi
 */
@RestController
@RequestMapping(REST_COMMON_PREFIX)
public class MaterialRestController {

	private MaterialConverter materialConverter;
	private MaterialService materialService;

	@Autowired
	public void setMaterialConverter(MaterialConverter materialConverter) {
		this.materialConverter = materialConverter;
	}

	@Autowired
	public void setMaterialService(MaterialService materialService) {
		this.materialService = materialService;
	}

	@GetMapping(value = "materials/{materialId:[\\d]+}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MaterialDto> findMaterialById(@PathVariable("materialId") Long materialId) {
		MaterialDto materialDto = materialConverter.convertFromEntity(materialService.findById(materialId));
		HttpStatus status = materialDto == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
		return new ResponseEntity<>(materialDto, status);
	}

	@GetMapping(value = "materials", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MaterialDto>> findMaterials() {
		List<MaterialDto> materialDtoList = materialConverter.createFromEntities(materialService.findAll());
		HttpStatus status = materialDtoList.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
		return new ResponseEntity<>(materialDtoList, status);
	}

	@RolesAllowed("MANAGER")
	@PostMapping(value = "materials", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MaterialDto> createMaterial(@Valid @RequestBody MaterialDto materialDto) {
		Material material = materialConverter.convertFromDto(materialDto);
		Material createdMaterial = materialService.create(material);

		HttpHeaders headers = new HttpHeaders();
		HttpStatus status;
		if (createdMaterial != null) {
			headers.setLocation(URI.create(REST_COMMON_PREFIX + "materials/" + createdMaterial.getId()));
			status = HttpStatus.CREATED;
		} else {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(materialConverter.convertFromEntity(createdMaterial), headers, status);
	}

	@RolesAllowed("MANAGER")
	@PutMapping(value = "materials/{materialId:[\\d]+}", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MaterialDto> updateMaterial(@PathVariable("materialId") Long materialId,
													  @Valid @RequestBody MaterialDto materialDto) {
		materialDto.setId(materialId);
		Material material = materialConverter.convertFromDto(materialDto);
		materialService.update(material);

		return new ResponseEntity<>(materialConverter.convertFromEntity(material), HttpStatus.OK);
	}

	@DeleteMapping(value = "materials/{materialId:[\\d]+}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteMaterial(@PathVariable("materialId") Long materialId) {
		materialService.deleteById(materialId);

		return ResponseEntity.ok("Material with ID = " + materialId + " was deleted.");
	}
}
