package controllers;

import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.form.ActorForm;

import services.ActorFormService;

@Controller
@RequestMapping(value = "/customer")
public class RegisterController extends AbstractController{

	//Services ----------------------------------------------------------
	
	@Autowired
	private ActorFormService actorFormService;
	
	@Autowired
	private Validator actorFormValidator;
	
	//Constructors ----------------------------------------------------------
	
	public RegisterController(){
		super();
	}

	//Listing ----------------------------------------------------------

	//Creation ----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		ActorForm consu;
		
		consu = actorFormService.createForm();
		result = createEditModelAndView(consu);
		
		
		return result;
	}
	
	//Edition ----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid ActorForm consu, BindingResult binding
			,HttpServletResponse respose
			,HttpServletRequest request
			){
		actorFormValidator.validate(consu, binding);
		
		ModelAndView result;
		
		if(binding.hasErrors()){
			System.out.println("Errors: " + binding.toString());
			result = createEditModelAndView(consu);
		} else {
			try {
				actorFormService.saveForm(consu);
				result = new ModelAndView("redirect:../security/login.do");
				result.addObject("messageStatus", "customer.commit.ok");
				
				Cookie cook;
				
				cook = new Cookie("cook5", "value5");
				// cook.setDomain("localhost");
				cook.setPath("/");
				//request.getLocale();
				respose.addCookie(cook);
				respose.addCookie(new Cookie("cook2", "value2"));
								
			} catch (Throwable oops){
				result = createEditModelAndView(consu, "customer.commit.error");
			}
		}
		
		return result;
	}
	//Ancillary Methods ----------------------------------------------------------

	protected ModelAndView createEditModelAndView(ActorForm customer){
		ModelAndView result;
		
		result = createEditModelAndView(customer, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(ActorForm customer, String message){
		ModelAndView result;
		
		result = new ModelAndView("actorForm/create");
		result.addObject("actorForm", customer);
		result.addObject("message", message);
		result.addObject("urlAction", "customer/create.do");
		
		return result;
	}
}
