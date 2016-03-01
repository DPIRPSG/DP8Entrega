package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Customer;
import domain.SocialIdentity;

import repositories.SocialIdentityRepository;

@Service
@Transactional
public class SocialIdentityService {
	//Managed repository -----------------------------------------------------
	
	@Autowired
	private SocialIdentityRepository socialIdentityRepository;
	
	//Supporting services ----------------------------------------------------
	
	@Autowired
	private CustomerService customerService;
	
	//Constructors -----------------------------------------------------------

	public SocialIdentityService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------
	
	/**
	 * Almacena en la base de datos el cambio
	 */
	// req: 10.1
	public void save(SocialIdentity socialIdentity){
		Assert.notNull(socialIdentity);
		
		Customer customer;
		
		customer = customerService.findByPrincipal();
		socialIdentity = socialIdentityRepository.save(socialIdentity);
		
		customer.setSocialIdentity(socialIdentity);
		customerService.save(customer);
	}
	
	public SocialIdentity findByPrincipal(){
		SocialIdentity result;
		Customer custo;
		
		custo = customerService.findByPrincipal();
		result = custo.getSocialIdentity();
		
		return result;
	}
	
	public void delete(){
		Customer customer;
		SocialIdentity socialIdentity;
		
		customer = customerService.findByPrincipal();
		socialIdentity = customer.getSocialIdentity();
		customer.setSocialIdentity(null);

		socialIdentityRepository.delete(socialIdentity);
		customerService.save(customer);
	}
	
	//Other business methods -------------------------------------------------
	
	public SocialIdentity findByPrincipalOrCreate(){
		SocialIdentity result;
		
		result = this.findByPrincipal();
		if(result == null)
			result = new SocialIdentity();
		
		return result;
	}


}
