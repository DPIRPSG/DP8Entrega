package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Administrator;

import repositories.AdministratorRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class AdministratorService {


 	//Managed repository -----------------------------------------------------

	@Autowired
	private AdministratorRepository administratorRepository;
	
	//Supporting services ----------------------------------------------------

	//Constructors -----------------------------------------------------------

	public AdministratorService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------

	//Other business methods -------------------------------------------------
 
	/**
	 * Devuelve el administrator que est� realizando la operaci�n
	 */
	//req: x
	public Administrator findByPrincipal(){
		Administrator result;
		UserAccount userAccount;
		
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = administratorRepository.findByUserAccountId(userAccount.getId());
		Assert.notNull(result);
		
		return result;
	}
}
