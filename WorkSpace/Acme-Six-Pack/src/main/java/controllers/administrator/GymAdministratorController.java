package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.GymService;

import controllers.AbstractController;
import domain.Gym;

@Controller
@RequestMapping(value = "/gym/administrator")
public class GymAdministratorController extends AbstractController {

	// Services ----------------------------------------------------------

	@Autowired
	private GymService gymService;

	// Constructors ----------------------------------------------------------

	public GymAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Gym> gyms;

		gyms = gymService.findAll();

		result = new ModelAndView("gym/list");
		result.addObject("requestURI", "gym/list.do?");
		result.addObject("gyms", gyms);

		return result;
	}
}
