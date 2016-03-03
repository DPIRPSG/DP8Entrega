package controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.form.ActorForm;

import services.form.ActorFormService;

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
			BindingResult binding
			, HttpServletResponse response
			){
		actorFormValidator.validate(consu, binding);
		
		ModelAndView result;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(consu);
		} else {
			try {
				Cookie cook1;
				Cookie cook2;
				actorFormService.saveForm(consu);

				result = new ModelAndView("redirect:../security/login.do");
				result.addObject("messageStatus", "customer.commit.ok");
				
				cook1 = new Cookie("createCreditCard", consu.getCreateCreditCard().toString());
				cook2 = new Cookie("createSocialIdentity", consu.getCreateSocialIdentity().toString());
				
				cook1.setPath("/");
				cook2.setPath("/");
				
				response.addCookie(cook1);
				response.addCookie(cook2);
			
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
