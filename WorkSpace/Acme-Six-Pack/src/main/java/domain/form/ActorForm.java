package domain.form;


public class ActorForm {
	private String name;
	private String surname;
	private String phone;
	private String username;
	private String password;
	private String repeatedPassword;
	private boolean aceptTerm;
	private boolean creditCard;
	private boolean socialIdentity;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
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
	public boolean isAceptTerm() {
		return aceptTerm;
	}
	public void setAceptTerm(boolean aceptTerm) {
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
	
}
