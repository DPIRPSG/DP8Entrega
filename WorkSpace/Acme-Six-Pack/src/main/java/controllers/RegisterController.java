package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Customer;

import services.CustomerService;

@Controller
@RequestMapping(value = "/customer")
public class RegisterController extends AbstractController{

	//Services ----------------------------------------------------------
	
	@Autowired
	private CustomerService customerService;
	
	//Constructors ----------------------------------------------------------
	
	public RegisterController(){
		super();
	}

	//Listing ----------------------------------------------------------

	//Creation ----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		Customer consu;
		
		consu = customerService.create();
		result = createEditModelAndView(consu);
		
		return result;
	}
	
	//Edition ----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Customer consu, BindingResult binding){
		ModelAndView result;
		boolean bindingError;
		
		if(binding.hasFieldErrors("messageBoxs")){
			bindingError = binding.getErrorCount() > 1;
		}else{
			bindingError = binding.getErrorCount() > 0;
		}
		
		if(bindingError){
			System.out.println("Errores: " + binding.toString());
			result = createEditModelAndView(consu);
		} else {
			try {
				customerService.save(consu);
				result = new ModelAndView("redirect:../security/login.do");
				result.addObject("messageStatus", "customer.commit.ok");
								
			} catch (Throwable oops){
				result = createEditModelAndView(consu, "customer.commit.error");
			}
		}
		
		return result;
	}
	//Ancillary Methods ----------------------------------------------------------

	protected ModelAndView createEditModelAndView(Customer consumer){
		ModelAndView result;
		
		result = createEditModelAndView(consumer, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Customer consumer, String message){
		ModelAndView result;
		
		result = new ModelAndView("customer/create");
		result.addObject("customer", consumer);
		result.addObject("message", message);
		
		return result;
	}
}
