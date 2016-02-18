package controllers.customer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;

import controllers.AbstractController;
import domain.Customer;

@Controller
@RequestMapping(value = "/customer/customer")
public class CustomerController extends AbstractController {

	// Services ----------------------------------------------------------

	@Autowired
	private CustomerService customerService;

	// Constructors ----------------------------------------------------------

	public CustomerController() {
		super();
	}

	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(){
		ModelAndView result;
		Customer customer;
		
		customer = customerService.findByPrincipal();
		
		result = new ModelAndView("customer/display");
		result.addObject("customer", customer);
		
		return result;
	}

	// Creation ----------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(){
		ModelAndView result;
		Customer customer;
		
		// Si no la tiene debería crearla
		customer = customerService.findByPrincipal();
		
		result = createEditModelAndView(customer);
		
		return result;
	}

	// Edition ----------------------------------------------------------

	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Customer customer, BindingResult binding) {
		ModelAndView result;
		boolean bindingError;
		Customer oldCustomer;
		
		if(binding.hasFieldErrors("userAccount") 
				&& binding.hasFieldErrors("creditCard")
				&& binding.hasFieldErrors("socialIdentity")){
			bindingError = binding.getErrorCount() > 3;
		}else{
			bindingError = binding.getErrorCount() > 0;
		}

		if (bindingError) {
			System.out.println("Errores: " + binding.toString());
			result = createEditModelAndView(customer);
		} else {
			try {
				oldCustomer = customerService.findByPrincipal();
				customer.setUserAccount(oldCustomer.getUserAccount());
				customer.setCreditCard(oldCustomer.getCreditCard());
				customer.setSocialIdentity(oldCustomer.getSocialIdentity());
				customerService.save(customer);
				result = new ModelAndView("redirect:display.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(customer, "customer.commit.error");				
			}
		}

		return result;
	}
	
	// Ancillary Methods
	// ----------------------------------------------------------

	protected ModelAndView createEditModelAndView(Customer customer) {
		ModelAndView result;

		result = createEditModelAndView(customer, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Customer customer, String message) {
		ModelAndView result;

		result = new ModelAndView("customer/edit");
		result.addObject("customer", customer);
		result.addObject("message", message);
		result.addObject("urlAction", "customer/customer/edit.do");

		return result;
	}
}
