package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.form.ActorFormService;

import controllers.AbstractController;
import domain.Administrator;
import domain.form.ActorForm;

@Controller
@RequestMapping(value = "/administrator/administrator")
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

	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(){
		ModelAndView result;
		Administrator administrator;
		
		administrator = administratorService.findByPrincipal();
		
		result = new ModelAndView("administrator/display");
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
		actorFormValidator.validate(actorForm, binding);
		
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(actorForm);
		} else {
			try {
				actorFormService.saveForm(actorForm);
				result = new ModelAndView("redirect:display.do");
			} catch (Throwable oops) {
				String errorCode;
				errorCode = "actorForm.commit.error";
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
		result.addObject("urlAction", "administrator/administrator/edit.do");

		return result;
	}
}
