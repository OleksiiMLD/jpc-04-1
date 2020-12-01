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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a {@link User}`s report.
 *
 * @author Oleksii Kostetskyi
 */
@Entity
@Table(name = "report")
public class Report implements Identifiable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "report_id_generator")
	@SequenceGenerator(name = "report_id_generator", sequenceName = "report_inst_id_seq", allocationSize = 1)
	@Column(name = "inst_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Size(max = 50)
	@Column(name = "report_name")
	private String name;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "order_date")
	private LocalDate orderDate;

	@OneToMany(mappedBy = "report")
	private Set<Building> buildings;

	public Report() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public Set<Building> getBuildings() {
		return buildings;
	}

	public void setBuildings(Set<Building> buildings) {
		this.buildings = buildings;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Report report = (Report) o;
		return Objects.equals(id, report.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Report{" +
				"id=" + id +
				", user.id=" + (user == null ? null : user.getId()) +
				", name='" + name + '\'' +
				", price=" + price +
				", orderDate=" + orderDate +
				'}';
	}
}
