package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	// Creation ----------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Gym gym;

		gym = gymService.create();
		result = createEditModelAndView(gym);

		return result;
	}

	// Edition ----------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int gymId) {
		ModelAndView result;
		Gym gym;

		gym = gymService.findOne(gymId);
		result = createEditModelAndView(gym);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Gym gym, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(gym);
		} else {
			try {
				gymService.save(gym);
				result = new ModelAndView("redirect:list.do?");
			} catch (Throwable oops) {
				result = createEditModelAndView(gym, "gym.commit.error");
			}
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Gym gym, BindingResult binding) {
		ModelAndView result;

		try {
			gymService.delete(gym);
			result = new ModelAndView("redirect:list.do?");
		} catch (Throwable oops) {
			result = createEditModelAndView(gym, "gym.commit.error");
		}

		return result;
	}

	// Ancillary Methods
	// ----------------------------------------------------------

	protected ModelAndView createEditModelAndView(Gym gym) {
		ModelAndView result;

		result = createEditModelAndView(gym, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Gym gym, String message) {
		ModelAndView result;

		result = new ModelAndView("gym/edit");
		result.addObject("gym", gym);
		result.addObject("message", message);

		return result;
	}
}
