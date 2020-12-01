package ua.omld.jpc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a building associated with {@link Report}.
 *
 * @author Oleksii Kostetskyi
 */
@Entity
@Table(name = "building")
public class Building implements Identifiable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "building_id_generator")
	@SequenceGenerator(name = "building_id_generator", sequenceName = "building_inst_id_seq", allocationSize = 1)
	@Column(name = "inst_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "report_id")
	private Report report;

	@Size(max = 50)
	@Column(name = "building_name")
	private String name;

	@Column(name = "is_active")
	private Boolean isActive;

	@OneToMany(mappedBy = "building")
	private Set<Activity> activities;

	public Building() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
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

	public Set<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Building building = (Building) o;
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
