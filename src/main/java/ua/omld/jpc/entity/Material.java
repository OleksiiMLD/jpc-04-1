package ua.omld.jpc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents a material.
 *
 * @author Oleksii Kostetskyi
 */
@Entity
@Table(name = "material")
public class Material implements Identifiable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "material_id_generator")
	@SequenceGenerator(name = "material_id_generator", sequenceName = "material_inst_id_seq", allocationSize = 1)
	@Column(name = "inst_id")
	private Long id;

	@Column(name = "material_name")
	private String name;

	@Column(name = "price")
	private BigDecimal price;

	@Size(max = 50)
	@Column(name = "supplier")
	private String supplier;

	@Column(name = "measurement")
	@Enumerated(EnumType.STRING)
	private MaterialMeasurement measurement;

	@Column(name = "balance")
	private Double balance;

	public Material() {
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

	public MaterialMeasurement getMeasurement() {
		return measurement;
	}

	public void setMeasurement(MaterialMeasurement measurement) {
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
		Material material = (Material) o;
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
