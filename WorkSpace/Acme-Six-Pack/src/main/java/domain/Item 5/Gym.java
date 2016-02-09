import java.util.Collection;

public class Gym {

	public String name;

	public String description;

	public String postalAddress;

	public String phone;

	public Double fee;

	public String[] picture;

	private Collection<Service> service;

	private FeePayment feePayment;

	private Customer customer;

	private Collection<FeePayment> feePayment;

	private Collection<Comment> comment;

}
