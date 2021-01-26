package ua.omld.jpc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ua.omld.jpc.entity.MaterialMeasurement;
import ua.omld.jpc.validation.EnumConstraint;

import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * Represents a material.
 *
 * @author Oleksii Kostetskyi
 */
@JsonInclude(NON_NULL)
public class MaterialDto {

	private Long id;
	private String name;
	private BigDecimal price;
	@Size(max = 50)
	private String supplier;
	@Size(max = 50)
	@EnumConstraint(enumClass = MaterialMeasurement.class)
	private String measurement;
	private Double balance;

	public MaterialDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getMeasurement() {
		return measurement;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MaterialDto material = (MaterialDto) o;
		return Objects.equals(id, material.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Material{" +
				"id=" + id +
				", name='" + name + '\'' +
				", price=" + price +
				", supplier='" + supplier + '\'' +
				", measurement=" + measurement +
				", balance=" + balance +
				'}';
	}
}
