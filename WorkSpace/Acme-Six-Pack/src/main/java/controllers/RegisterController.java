package controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.ws.ResponseWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HttpServletBean;
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
	public ModelAndView save(@Valid ActorForm consu,
			BindingResult binding,
			@CookieValue(value = "createCreditCard", required = false) String createCreditCard,
			@CookieValue(value = "createSocialIdentity", required = false) String createSocialIdentity,
			@RequestParam(required = false, defaultValue = "false") String recharging,
			HttpServletResponse response){
		actorFormValidator.validate(consu, binding);
		
		ModelAndView result;
		
		if(binding.hasErrors()){
			System.out.println("Errors: " + binding.toString());
			result = createEditModelAndView(consu);
		} else if(recharging.equals("true")){
			result = new ModelAndView("redirect:../security/login.do");
			result.addObject("messageStatus", "customer.commit.ok");			
		}else {
			try {
				//response.addCookie(new Cookie("createCreditCard", consu.getCreateCreditCard().toString()));
				response.addCookie(new Cookie("createSocialIdentity", consu.getCreateSocialIdentity().toString()));
				response.addCookie(new Cookie("createCreditCard", "cookTest"));
				
				actorFormService.saveForm(consu);
				result = createEditModelAndView(consu);
				result.addObject("recharging", "true");

				//response.flushBuffer();
				//result.addObject(new Cookie("testCookie!!", "cookTest"));
				//response.;
				
			
			} catch (Throwable oops){
				result = createEditModelAndView(consu, "customer.commit.error");
			}
		}
		
		return result;
	}
	//Ancillary Methods ----------------------------------------------------------
	@RequestMapping(value = "/loadCookies", method = RequestMethod.GET)
	public ModelAndView chargeCookies(
			@RequestParam(required = false, defaultValue = "false") String recharging
			){
		ModelAndView result;
		
		result = new ModelAndView("redirect:../security/login.do");
		result.addObject("messageStatus", "customer.commit.ok");	
		
		return result;
	}

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
