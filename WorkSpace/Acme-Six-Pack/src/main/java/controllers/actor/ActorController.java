package controllers.actor;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CustomerService;

import controllers.AbstractController;
import domain.Actor;
import domain.Customer;

@Controller
@RequestMapping(value = "/actor/actor")
public class ActorController extends AbstractController {

	// Services ----------------------------------------------------------

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ActorService actorService;

	// Constructors ----------------------------------------------------------

	public ActorController() {
		super();
	}

	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(){
		ModelAndView result;
		Actor customer;
		
		customer = actorService.findByPrincipal();
		
		result = new ModelAndView("actor/display");
		result.addObject("actor", customer);
		
		return result;
	}

	// Creation ----------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(){
		ModelAndView result;
		Actor customer;
		
		customer = actorService.findByPrincipal();
		
		result = createEditModelAndView(customer);
		
		return result;
	}

	// Edition ----------------------------------------------------------

	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(
			//@Valid
			Actor actor, BindingResult binding) {
		ModelAndView result;
		boolean bindingError;
		Actor oldCustomer;
		
		System.out.println("Binding errors: " + binding.toString());
		if(binding.hasFieldErrors("userAccount") 
				&& binding.hasFieldErrors("creditCard")
				&& binding.hasFieldErrors("socialIdentity")){
			bindingError = binding.getErrorCount() > 3;
		}else{
			bindingError = binding.getErrorCount() > 0;
		}

		if (bindingError) {
			System.out.println("Errores: " + binding.toString());
			result = createEditModelAndView(actor);
		} else {
			try {
				oldCustomer = actorService.findByPrincipal();
				actor.setUserAccount(oldCustomer.getUserAccount());
				//customer.modifyCreditCard(oldCustomer.showCreditCard());
				//customer.setSocialIdentity(oldCustomer.getSocialIdentity());
				actorService.save(actor);
				result = new ModelAndView("redirect:display.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(actor, "actor.commit.error");				
			}
		}

		return result;
	}
	
	// Ancillary Methods
	// ----------------------------------------------------------

	protected ModelAndView createEditModelAndView(Actor customer) {
		ModelAndView result;

		result = createEditModelAndView(customer, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Actor customer, String message) {
		ModelAndView result;

		result = new ModelAndView("actor/edit");
		result.addObject("actor", customer);
		result.addObject("message", message);
		result.addObject("urlAction", "actor/actor/edit.do");

		return result;
	}
}
