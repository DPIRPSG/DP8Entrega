package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Booking;
import domain.Comment;
import domain.CreditCard;
import domain.Customer;
import domain.FeePayment;
import domain.Folder;
import domain.Message;


import repositories.CustomerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;

@Service
@Transactional
public class CustomerService {
	//Managed repository -----------------------------------------------------
	
	@Autowired
	private CustomerRepository customerRepository;
	
	//Supporting services ----------------------------------------------------

	@Autowired
	private FolderService folderService;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private UserAccountService userAccountService;
	
	/*@Autowired
	private ShoppingCartService shoppingCartService;*/
	
	//Constructors -----------------------------------------------------------

	public CustomerService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------
	
	/** Devuelve customer preparado para ser modificado. Necesita usar save para que persista en la base de datos
	 * 
	 */
	// req: 10.1
	public Customer create(){
		Customer result;
		UserAccount userAccount;

		result = new Customer();
		
		userAccount = userAccountService.create("CUSTOMER");
		result.setUserAccount(userAccount);
		
		return result;
	}
	
	/**
	 * Almacena en la base de datos el cambio
	 */
	// req: 10.1
	public void save(Customer customer){
		Assert.notNull(customer);
		
		Customer modify;
		
		boolean result = true;
		for(Authority a: customer.getUserAccount().getAuthorities()){
			if(!a.getAuthority().equals("CUSTOMER")){
				result = false;
				break;
			}
		}
		Assert.isTrue(result, "A customer can only be a authority.customer");
		
		if(customer.getId() == 0){
			Collection<Folder> folders;
			Collection<Message> sent;
			Collection<Message> received;
			Collection<Comment> comments;
			Collection<Booking> bookings;
			Collection<FeePayment>feePayments;
			UserAccount auth;
			
			//Encoding password
			auth = customer.getUserAccount();
			auth = userAccountService.modifyPassword(auth);
			customer.setUserAccount(auth);
			
			// Initialize folders
			folders = folderService.initializeSystemFolder(customer);
			customer.setMessageBoxes(folders);
			
			sent = new ArrayList<Message>();
			received = new ArrayList<Message>();
			customer.setSent(sent);
			customer.setReceived(received);
			
			// Initialize anothers
			
			comments = new ArrayList<Comment>();
			bookings = new ArrayList<Booking>();
			feePayments = new ArrayList<FeePayment>();
			customer.setComments(comments);
			customer.setBookings(bookings);
			customer.setFeePayments(feePayments);

			
		}
		//modify = customerRepository.saveAndFlush(customer);
		modify = customerRepository.save(customer);		
		
		if(customer.getId() == 0){
			Collection<Folder> folders;

			folders = folderService.initializeSystemFolder(modify);
			folderService.save(folders);
		}
		
	}
	
	/**
	 * Lista los customers registrados
	 */
	// req: 12.5
	public Collection<Customer> findAll(){
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can list customers");
		
		Collection<Customer> result;
		
		result = customerRepository.findAll();
		
		return result;
	}

	//Other business methods -------------------------------------------------

	/**
	 * Devuelve el customers que está realizando la operación
	 */
	//req: x
	public Customer findByPrincipal(){
		Customer result;
		UserAccount userAccount;
		
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = customerRepository.findByUserAccountId(userAccount.getId());
		Assert.notNull(result);
		
		return result;
	}
	
	public CreditCard getOrCreateCreditCard(){
		CreditCard result;
		Customer custo;
		
		custo = this.findByPrincipal();
		
		result = custo.showCreditCard();
		if(result == null)
			result = new CreditCard();
		return result;		
	}
	
	public void saveCreditCard(CreditCard creditCard){
		Customer custo;
		
		custo = this.findByPrincipal();
		custo.modifyCreditCard(creditCard);
		this.save(custo);
	}
	
	public void deleteCreditCard(){
		Customer custo;
		
		custo = this.findByPrincipal();
		custo.modifyCreditCard(null);
		this.save(custo);
	}
	
	
	public Integer numbersOfCustomersByGym(int gymId) {
		Integer result;
		
		result = customerRepository.findByGymBooked(gymId).size();
		
		return result;
	}

	public Integer numbersOfCustomersByService(int serviceId) {
		Integer result;

		result = customerRepository.findByServiceBooked(serviceId).size();

		return result;
	}
	
	/* Query 5 */
	public Collection<Customer> findCustomerWhoHasPaidMoreFees(){
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can open the dashboard");
		
		Collection<Customer> result;
		
		result = customerRepository.findCustomerWhoHasPaidMoreFees();
		
		return result;
	}
	
	/* Query 6 */
	public Collection<Customer> findCustomerWhoHasPaidLessFees(){
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can open the dashboard");
		
		Collection<Customer> result;
		
		result = customerRepository.findCustomerWhoHasPaidLessFees();
		
		return result;
	}
	
	/* Query 14 */
	public Collection<Customer> findCustomerWhoHaveBeenRemovedMoreComments(){
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can open the dashboard");
		
		Collection<Customer> result;
		
		result = customerRepository.findCustomerWhoHaveBeenRemovedMoreComments();
		
		return result;
	}
}
