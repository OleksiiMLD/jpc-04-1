package ua.omld.jpc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * Represents a {@link UserDto}`s report.
 *
 * @author Oleksii Kostetskyi
 */
@JsonInclude(NON_NULL)
public class ReportDto {

	private Long id;
	private UserDto user;
	@Size(max = 50)
	private String name;
	private BigDecimal price;
	@PastOrPresent
	private LocalDate orderDate;
	private Set<BuildingDto> buildings;

	public ReportDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
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

	public Set<BuildingDto> getBuildings() {
		return buildings;
	}

	public void setBuildings(Set<BuildingDto> buildings) {
		this.buildings = buildings;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ReportDto report = (ReportDto) o;
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
