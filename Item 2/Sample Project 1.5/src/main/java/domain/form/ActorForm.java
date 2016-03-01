package domain.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class ActorForm {
	private String name;
	private String surname;
	private String phone;
	private String username;
	private String password;
	private String repeatedPassword;
	private Boolean acceptTerm;
	
	@NotNull
	@NotBlank
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@NotNull
	@NotBlank
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	@NotNull
	@NotBlank
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@NotNull
	@NotBlank
	@Size(min = 5, max = 32)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRepeatedPassword() {
		return repeatedPassword;
	}
	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}
	
	public Boolean getAcceptTerm() {
		return acceptTerm;
	}
	public void setAcceptTerm(Boolean aceptTerm) {
		this.acceptTerm = aceptTerm;
	}
	
	// Validator ----------

}
