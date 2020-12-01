package ua.omld.jpc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a user.
 *
 * @author Oleksii Kostetskyi
 */
@Entity
@Table(name = "app_user")
public class User implements Identifiable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
	@SequenceGenerator(name = "user_id_generator", sequenceName = "app_user_inst_id_seq", allocationSize = 1)
	@Column(name = "inst_id")
	private Long id;

	@Size(max = 50)
	@Column(name = "user_name")
	private String userName;

	@Size(max = 50)
	@Column(name = "last_name")
	private String lastName;

	@NotNull
	@Size(max = 50)
	@Column(name = "email")
	private String email;

	@Size(max = 50)
	@Column(name = "email_backup")
	private String emailBackup;

	@NotNull
	@Size(max = 20)
	@Column(name = "tn")
	private String telephoneNumber;

	@Size(max = 20)
	@Column(name = "tn_backup")
	private String telephoneNumberBackUp;

	@OneToMany(mappedBy = "user")
	private Set<Report> reports;

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailBackup() {
		return emailBackup;
	}

	public void setEmailBackup(String emailBackup) {
		this.emailBackup = emailBackup;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getTelephoneNumberBackUp() {
		return telephoneNumberBackUp;
	}

	public void setTelephoneNumberBackUp(String telephoneNumberBackUp) {
		this.telephoneNumberBackUp = telephoneNumberBackUp;
	}

	public Set<Report> getReports() {
		return reports;
	}

	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(id, user.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", userName='" + userName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", emailBackup='" + emailBackup + '\'' +
				", telephoneNumber='" + telephoneNumber + '\'' +
				", telephoneNumberBackUp='" + telephoneNumberBackUp + '\'' +
				'}';
	}
}
