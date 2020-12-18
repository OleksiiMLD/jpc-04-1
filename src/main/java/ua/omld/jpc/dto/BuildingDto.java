package ua.omld.jpc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * Represents a building associated with {@link ReportDto}.
 *
 * @author Oleksii Kostetskyi
 */
@JsonInclude(NON_NULL)
public class BuildingDto {

	private Long id;
	private ReportDto report;
	private String name;
	private Boolean isActive;
	private Set<ActivityDto> activities;

	public BuildingDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ReportDto getReport() {
		return report;
	}

	public void setReport(ReportDto report) {
		this.report = report;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean active) {
		isActive = active;
	}

	public Set<ActivityDto> getActivities() {
		return activities;
	}

	public void setActivities(Set<ActivityDto> activities) {
		this.activities = activities;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BuildingDto building = (BuildingDto) o;
		return Objects.equals(id, building.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Building{" +
				"id=" + id +
				", report.id=" + (report == null ? null : report.getId()) +
				", name='" + name + '\'' +
				", isActive=" + isActive +
				'}';
	}
}
