package ua.omld.jpc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * Represents a user.
 *
 * @author Oleksii Kostetskyi
 */
@JsonInclude(NON_NULL)
public class UserDto {

	private Long id;

	@Size(max = 50)
	private String userName;

	@Size(max = 50)
	private String lastName;

	@NotNull
	@Size(max = 50)
	@Email
	private String email;

	@Size(max = 50)
	@Email
	private String emailBackup;

	@NotNull
	@Size(max = 20)
	private String telephoneNumber;

	@Size(max = 20)
	private String telephoneNumberBackUp;
	private Set<ReportDto> reports;

	public UserDto() {
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

	public Set<ReportDto> getReports() {
		return reports;
	}

	public void setReports(Set<ReportDto> reports) {
		this.reports = reports;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserDto user = (UserDto) o;
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
