package controllers.customer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;

import controllers.AbstractController;
import domain.CreditCard;
import domain.Customer;

@Controller
@RequestMapping(value = "/creditCard/customer")
public class CreditCardController extends AbstractController {

	// Services ----------------------------------------------------------

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private Validator creditCardValidator;

	// Constructors ----------------------------------------------------------

	public CreditCardController() {
		super();
	}

	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(){
		ModelAndView result;
		CreditCard creditCard;
		Customer customer;
		
		customer = customerService.findByPrincipal();
		creditCard = customer.showCreditCard();
		
		result = new ModelAndView("creditCard/display");
		result.addObject("creditCard", creditCard);
		result.addObject("customer", "true");
		
		return result;
	}

	// Creation ----------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		CreditCard creditCard;

		
		// Si no la tiene debería crearla
		creditCard = customerService.getOrCreateCreditCard();
		
		result = createEditModelAndView(creditCard);
		
		return result;
	}

	// Edition ----------------------------------------------------------

	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid CreditCard creditCard, BindingResult binding
			,@CookieValue(value = "createSocialIdentity", required = false, defaultValue = "false") String createSocialIdentity
			, HttpServletResponse response			
			) {
		ModelAndView result;

		creditCardValidator.validate(creditCard, binding);
		if (binding.hasErrors()) {
			result = createEditModelAndView(creditCard);
		} else {
			try {
				Cookie cook1;
				
				customerService.saveCreditCard(creditCard);
				
				cook1 = new Cookie("createCreditCard", "false");
				cook1.setPath("/");
			
				response.addCookie(cook1);
				
				if(createSocialIdentity.equals("true")){
					result = new ModelAndView("redirect:/socialIdentity/customer/edit.do");					
				}else{
					result = new ModelAndView("redirect:display.do");					
				}
			} catch (Throwable oops) {
				result = createEditModelAndView(creditCard, "creditCard.commit.error");				
			}
		}

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(CreditCard creditCard, BindingResult binding) {
		ModelAndView result;

		try {
			customerService.deleteCreditCard();
			result = new ModelAndView("redirect:/");
		} catch (Throwable oops) {
			result = createEditModelAndView(creditCard, "creditCard.commit.error");
		}

		return result;
	}
	

	// Ancillary Methods
	// ----------------------------------------------------------

	protected ModelAndView createEditModelAndView(CreditCard creditCard) {
		ModelAndView result;

		result = createEditModelAndView(creditCard, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(CreditCard creditCard, String message) {
		ModelAndView result;

		result = new ModelAndView("creditCard/edit");
		result.addObject("creditCard", creditCard);
		result.addObject("message", message);
		result.addObject("customer", "true");
		result.addObject("urlAction", "creditCard/customer/edit.do");
		result.addObject("urlReturn", "creditCard/customer/display.do");

		return result;
	}
}
