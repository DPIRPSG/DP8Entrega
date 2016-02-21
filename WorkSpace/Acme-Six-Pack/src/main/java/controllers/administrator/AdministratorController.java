package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorFormService;
import services.AdministratorService;

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
		
		result = new ModelAndView("admin/display");
		result.addObject("administrator", administrator);
		
		return result;
	}

	// Creation ----------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(){
		ModelAndView result;
		ActorForm administrator;
		System.out.println("Quiero editar !!!!");
		
		// Si no la tiene debería crearla
		administrator = actorFormService.createForm();
		
		result = createEditModelAndView(administrator);
		
		return result;
	}

	// Edition ----------------------------------------------------------

	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid ActorForm actorForm, BindingResult binding) {
		ModelAndView result;
		boolean bindingError;
		Administrator oldCustomer;
		
		if(binding.hasFieldErrors("userAccount")		
				&& binding.hasFieldErrors("messageBoxes")
				&& binding.hasFieldErrors("comments")){
			bindingError = binding.getErrorCount() > 3;
		}else{
			bindingError = binding.getErrorCount() > 0;
		}

		if (bindingError) {
			System.out.println("Errores: " + binding.toString());
			result = createEditModelAndView(actorForm);
		} else {
			try {
				actorFormService.saveForm(actorForm);
				result = new ModelAndView("redirect:display.do");
			} catch (Throwable oops) {
				System.out.println("Oops: " + oops.toString());
				System.out.println(oops.getStackTrace());
				result = createEditModelAndView(actorForm, "actorForm.commit.error");
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
