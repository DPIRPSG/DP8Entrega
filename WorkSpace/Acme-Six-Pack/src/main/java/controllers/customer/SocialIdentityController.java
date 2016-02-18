package controllers.customer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.SocialIdentityService;

import controllers.AbstractController;
import domain.SocialIdentity;

@Controller
@RequestMapping(value = "/socialIdentity/customer")
public class SocialIdentityController extends AbstractController {

	// Services ----------------------------------------------------------

	@Autowired
	private SocialIdentityService socialIdentityService;

	// Constructors ----------------------------------------------------------

	public SocialIdentityController() {
		super();
	}

	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(){
		ModelAndView result;
		SocialIdentity socialIdent;
		
		socialIdent = socialIdentityService.findByPrincipalOrCreate();
		
		result = new ModelAndView("socialIdentity/display");
		result.addObject("socialIdentity", socialIdent);
		
		return result;
	}

	// Creation ----------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		SocialIdentity socialIdentity;
		
		// Si no la tiene debería crearla
		socialIdentity = socialIdentityService.findByPrincipalOrCreate();
		
		result = createEditModelAndView(socialIdentity);
		
		return result;
	}

	// Edition ----------------------------------------------------------

	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid SocialIdentity socialIdentity, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(socialIdentity);
		} else {
			try {
				socialIdentityService.save(socialIdentity);
				result = new ModelAndView("redirect:display.do");
			} catch (Throwable oops) {
				System.out.println("Error!!!!");
				System.out.println(oops);
				result = createEditModelAndView(socialIdentity, "socialIdentity.commit.error");				
			}
		}

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(SocialIdentity socialIdentity, BindingResult binding) {
		ModelAndView result;

		try {
			socialIdentityService.delete(socialIdentity);
			result = new ModelAndView("redirect:/");
		} catch (Throwable oops) {
			result = createEditModelAndView(socialIdentity, "socialIdentity.commit.error");
		}

		return result;
	}
	

	// Ancillary Methods
	// ----------------------------------------------------------

	protected ModelAndView createEditModelAndView(SocialIdentity socialIdentity) {
		ModelAndView result;

		result = createEditModelAndView(socialIdentity, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(SocialIdentity socialIdentity, String message) {
		ModelAndView result;

		result = new ModelAndView("socialIdentity/edit");
		result.addObject("socialIdentity", socialIdentity);
		result.addObject("message", message);

		return result;
	}
}
