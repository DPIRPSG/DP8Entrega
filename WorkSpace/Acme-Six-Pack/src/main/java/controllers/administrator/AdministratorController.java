package controllers.administrator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorFormService;
import services.AdministratorService;
import validator.ActorFormValidator;

import controllers.AbstractController;
import domain.Administrator;
import domain.form.ActorForm;

@Controller
@RequestMapping(value = "/admin/administrator")
public class AdministratorController extends AbstractController {

	// Services ----------------------------------------------------------

	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private ActorFormService actorFormService;
	
	@Autowired
    private Validator actorFormValidator;

	// Constructors ----------------------------------------------------------

	public AdministratorController() {
		super();
	}

     
/*    @InitBinder
    private void initBinder(WebDataBinder binder) {
    	Validator[] validators;
    	
        //binder.setValidator(shopValidator);
        //validators = binder.getValidators();
        validators = actorFormValidator;
        binder.addValidators(validators);
        
    }*/
	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(){
		ModelAndView result;
		Administrator administrator;
		
		administrator = administratorService.findByPrincipal();
		
		result = new ModelAndView("admin/display");
		result.addObject("administrator", administrator);
		
		return result;
	}

	// Creation ----------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(){
		ModelAndView result;
		ActorForm administrator;
		
		administrator = actorFormService.createForm();
		
		result = createEditModelAndView(administrator);
		
		return result;
	}

	// Edition ----------------------------------------------------------

	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid ActorForm actorForm, BindingResult binding) {
		ModelAndView result;
		FieldError errorCampoMio;
		actorFormValidator.validate(actorForm, binding);
		
		/*binding.addAllErrors(binding);
		
		errorCampoMio = new FieldError("actorForm", "password", actorForm.getPassword(), false, null,
				null, "Error Mio");
		//new Fiel
		binding.addError(errorCampoMio);
		binding.rejectValue("name", "jssjerror", "Este es un error indeseado");
		 */

		if (binding.hasErrors()) {
			System.out.println("Errores: " + binding.toString());
			result = createEditModelAndView(actorForm);
		} else {
			try {
				actorFormService.saveForm(actorForm);
				result = new ModelAndView("redirect:display.do");
			} catch (Throwable oops) {
				String errorCode;
				
				System.out.println("Oops: " + oops.toString());
				System.out.println(oops.getStackTrace().length);
				
				if(oops.getMessage().equals("actorForm.error.passwordMismatch")){
					errorCode = "actorForm.error.passwordMismatch";
				}else if (oops.getMessage().equals("actorForm.error.termsDenied")) {
					errorCode = "actorForm.error.termsDenied";
				}else{
					errorCode = "actorForm.commit.error";
				}
				result = createEditModelAndView(actorForm, errorCode);
			}
		}

		return result;
	}
	
	// Ancillary Methods
	// ----------------------------------------------------------

	protected ModelAndView createEditModelAndView(ActorForm administrator) {
		ModelAndView result;

		result = createEditModelAndView(administrator, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(ActorForm administrator, String message) {
		ModelAndView result;

		result = new ModelAndView("actorForm/edit");
		result.addObject("actorForm", administrator);
		result.addObject("message", message);
		result.addObject("urlAction", "admin/administrator/edit.do");

		return result;
	}
}
