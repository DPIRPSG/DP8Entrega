package domain.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class ActorForm {
	private String name;
	private String surname;
	private String phone;
	private String username;
	private String password;
	private String repeatedPassword;
	private Boolean aceptTerm;
	private boolean creditCard;
	private boolean socialIdentity;
	
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
	
	public Boolean isAceptTerm() {
		return aceptTerm;
	}
	public void setAceptTerm(Boolean aceptTerm) {
		this.aceptTerm = aceptTerm;
	}
	public boolean isCreditCard() {
		return creditCard;
	}
	public void setCreditCard(boolean creditCard) {
		this.creditCard = creditCard;
	}
	public boolean isSocialIdentity() {
		return socialIdentity;
	}
	public void setSocialIdentity(boolean socialIdentity) {
		this.socialIdentity = socialIdentity;
	}
	
	// Validator ----------
	/*
	@AssertTrue(message="actorForm.error.passwordMismatch")
	public boolean paswordMismatch(){
		boolean result;
		
		System.out.println("Comprobando paswordMismatch");
		
		result = this.getPassword() == null && this.getRepeatedPassword() == null;
		result = result || this.getPassword().equals(this.getRepeatedPassword());
		
		return result;
	}
	
	@AssertTrue(message="actorForm.error.termsDenied")
	public boolean termsAccepted(){
		boolean result;
		
		System.out.println("Comprobando termsAccepted");
		
		result = this.isAceptTerm() == null;
		result = result || this.isAceptTerm();
		
		return result;
	}*/
}
