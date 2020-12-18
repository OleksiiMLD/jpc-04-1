package ua.omld.jpc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * Represents an activity made in {@link BuildingDto}.
 *
 * @author Oleksii Kostetskyi
 */
@JsonInclude(NON_NULL)
public class ActivityDto {

	private Long id;
	private BuildingDto building;
	private String workName;
	private String measurement;
	private BigDecimal price;
	private Double amount;

	public ActivityDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BuildingDto getBuilding() {
		return building;
	}

	public void setBuilding(BuildingDto building) {
		this.building = building;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getMeasurement() {
		return measurement;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ActivityDto activity = (ActivityDto) o;
		return Objects.equals(id, activity.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Activity{" +
				"id=" + id +
				", building.id=" + (building == null ? null : building.getId()) +
				", workName='" + workName + '\'' +
				", measurement=" + measurement +
				", price=" + price +
				", amount=" + amount +
				'}';
	}
}
