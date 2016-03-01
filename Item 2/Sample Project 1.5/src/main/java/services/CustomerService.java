package services;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Customer;
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
	private ActorService actorService;
	
	@Autowired
	private UserAccountService userAccountService;
	
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
		
		boolean result = true;
		for(Authority a: customer.getUserAccount().getAuthorities()){
			if(!a.getAuthority().equals("CUSTOMER")){
				result = false;
				break;
			}
		}
		Assert.isTrue(result, "A customer can only be a authority.customer");
		
		if(customer.getId() == 0){
			UserAccount auth;
			
			//Encoding password
			auth = customer.getUserAccount();
			auth = userAccountService.modifyPassword(auth);
			customer.setUserAccount(auth);
			
		}
		customerRepository.save(customer);		
		
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
	

}
