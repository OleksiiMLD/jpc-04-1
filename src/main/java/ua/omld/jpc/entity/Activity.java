package ua.omld.jpc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents an activity made in {@link Building}.
 *
 * @author Oleksii Kostetskyi
 */
@Entity
@Table(name = "activity")
public class Activity implements Identifiable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_id_generator")
	@SequenceGenerator(name = "activity_id_generator", sequenceName = "activity_inst_id_seq", allocationSize = 1)
	@Column(name = "inst_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "building_id")
	private Building building;

	@Size(max = 100)
	@Column(name = "work_name")
	private String workName;

	@Size(max = 20)
	@Column(name = "measurement")
	@Enumerated(EnumType.STRING)
	private ActivityMeasurement measurement;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "amount")
	private Double amount;

	public Activity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public ActivityMeasurement getMeasurement() {
		return measurement;
	}

	public void setMeasurement(ActivityMeasurement measurement) {
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
		Activity activity = (Activity) o;
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
