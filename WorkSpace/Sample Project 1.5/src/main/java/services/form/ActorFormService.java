package services.form;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Administrator;
import domain.Customer;
import domain.form.ActorForm;

import security.UserAccount;
import security.UserAccountService;
import services.ActorService;
import services.AdministratorService;
import services.CustomerService;

@Service
@Transactional
public class ActorFormService {
		// Managed repository -----------------------------------------------------

		
		// Supporting services ----------------------------------------------------

		@Autowired
		private ActorService actorService;
		
		@Autowired
		private CustomerService customerService;
		
		@Autowired
		private AdministratorService administratorService;
		
		@Autowired
		private UserAccountService userAccountService;
		
		
		
		// Constructors -----------------------------------------------------------
		
		public ActorFormService(){
			super();
		}
		
		// Other business methods -------------------------------------------------
		
		public ActorForm createForm(){
			ActorForm result;
			
			if(actorService.checkAuthority("CUSTOMER")
					|| actorService.checkAuthority("ADMIN")){ 
				result = this.createFormActor(actorService.findByPrincipal());
			}else{ //Usuario registrandose
				result = new ActorForm();
			}
			
			return result;
		}
		
		private ActorForm createFormActor(Actor actor){
			ActorForm result;
			
			result = new ActorForm();
			
			result.setName(actor.getName());
			result.setSurname(actor.getSurname());
			result.setPhone(actor.getPhone());
			result.setUsername(actor.getUserAccount().getUsername());
			
			return result;
		}
		
		public void saveForm(ActorForm input){
			boolean isCustoOrAdmin;
			
			if(input.getPassword() != null){
				Assert.isTrue(input.getPassword().equals(input.getRepeatedPassword()), "actorForm.error.passwordMismatch");
			}
			try{
				isCustoOrAdmin = actorService.checkAuthority("CUSTOMER")
						|| actorService.checkAuthority("ADMIN");
			}catch (Exception e) {
				isCustoOrAdmin = false;
			}
			if(isCustoOrAdmin){
				this.saveActor(input, actorService.checkAuthority("CUSTOMER"));
			}else{ //Usuario registrandose
				this.saveRegistration(input);
			}
		}
		
		private void saveActor(ActorForm input, boolean isConsumer){
			UserAccount acount;
			String pass;
			
			acount = actorService.findByPrincipal().getUserAccount();
			pass = input.getPassword();
			
			acount.setUsername(input.getUsername());
			if(pass != null){
				if(!(pass.isEmpty() || pass.equals(""))){
				acount.setPassword(pass);
				acount = userAccountService.modifyPassword(acount);
				}
			}
			
			if(isConsumer){
				Customer result;
				
				result = customerService.findByPrincipal();
				
				result.setName(input.getName());
				result.setSurname(input.getSurname());
				result.setPhone(input.getPhone());
				result.setUserAccount(acount);
				
				customerService.save(result);
				
			}else{ //isAdmin
				Administrator result;
				
				result = administratorService.findByPrincipal();
				
				result.setName(input.getName());
				result.setSurname(input.getSurname());
				result.setPhone(input.getPhone());
				result.setUserAccount(acount);
				
				administratorService.save(result);
			}
			
			
		}
		
		private void saveRegistration(ActorForm input){
			Assert.isTrue(input.getAcceptTerm(), "actorForm.error.termsDenied");

			UserAccount acount;
			Customer result;
			
			acount = userAccountService.createComplete(input.getUsername(), input.getPassword(), "CUSTOMER");
			result = customerService.create();
			
			result.setName(input.getName());
			result.setSurname(input.getSurname());
			result.setPhone(input.getUsername());
			result.setUserAccount(acount);
			
			customerService.save(result);
		}		

}
