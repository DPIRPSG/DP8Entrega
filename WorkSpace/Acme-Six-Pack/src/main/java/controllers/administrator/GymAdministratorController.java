package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.GymService;
import services.ServiceService;

import controllers.AbstractController;
import domain.Gym;
import domain.ServiceEntity;

@Controller
@RequestMapping(value = "/gym/administrator")
public class GymAdministratorController extends AbstractController {

	// Services ----------------------------------------------------------

	@Autowired
	private GymService gymService;
	
	@Autowired
	private ServiceService serviceService;

	// Constructors ----------------------------------------------------------

	public GymAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required=false, defaultValue="") String keyword, @RequestParam(required=false) Integer serviceId) {
		ModelAndView result;
		Collection<Gym> gyms;
		Collection<String> customers;
		String keywordToFind;

		gyms = gymService.findAll();
		
		if(serviceId != null) {
			gyms = gymService.findAllByService(serviceId);
		}
		
		if (!keyword.equals("")) {
			String[] keywordComoArray = keyword.split(" ");
			for (int i = 0; i < keywordComoArray.length; i++) {
				if (!keywordComoArray[i].equals("")) {
					keywordToFind = keywordComoArray[i];
					gyms = gymService.findBySingleKeyword(keywordToFind);
					break;
				}
			}
		}
		
		customers = gymService.numbersOfCustomersByGym(gyms);

		result = new ModelAndView("gym/list");
		result.addObject("requestURI", "gym/administrator/list.do?");
		result.addObject("gyms", gyms);
		result.addObject("customers", customers);

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
		boolean bindingError;
		
		if(binding.hasFieldErrors("service")){
			bindingError = binding.getErrorCount() > 1;
		}else{
			bindingError = binding.getErrorCount() > 0;
		}
		if (bindingError) {
			result = createEditModelAndView(gym);
		} else {
			try {
				gymService.saveToEdit(gym);
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
		Collection<ServiceEntity> services;
		
		services = serviceService.findAll2();

		result = new ModelAndView("gym/edit");
		result.addObject("gym", gym);
		result.addObject("message", message);
		result.addObject("services", services);

		return result;
	}
}
