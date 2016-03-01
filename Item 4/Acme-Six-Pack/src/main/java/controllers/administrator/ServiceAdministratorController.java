package controllers.administrator;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ServiceService;

import controllers.AbstractController;
import domain.ServiceEntity;

@Controller
@RequestMapping(value = "/service/administrator")
public class ServiceAdministratorController extends AbstractController {
	
	// Services ----------------------------------------------------------

	@Autowired
	private ServiceService serviceService;

	// Constructors ----------------------------------------------------------

	public ServiceAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required=false) Integer gymId) {
		ModelAndView result;
		Collection<ServiceEntity> services;
		Collection<ArrayList<Integer>> customers;
				
		result = new ModelAndView("service/list");
		result.addObject("requestURI", "service/administrator/list.do?");

		services = serviceService.findAll();
		
		if (gymId != null) {
			services = serviceService.findAllByGym(gymId);
		}
		
		customers = serviceService.numbersOfCustomersByService(services);

		
		result.addObject("services", services);
		result.addObject("customers", customers);

		return result;
	}

	// Creation ----------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		ServiceEntity service;

		service = serviceService.create();
		result = createEditModelAndView(service);

		return result;
	}

	// Edition ----------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int serviceId) {
		ModelAndView result;
		ServiceEntity service;

		service = serviceService.findOne(serviceId);
		result = createEditModelAndView(service);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid ServiceEntity service, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(service);
		} else {
			try {
				serviceService.saveToEdit(service);
				result = new ModelAndView("redirect:list.do?");
			} catch (Throwable oops) {
				result = createEditModelAndView(service, "service.commit.error");
			}
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(ServiceEntity service, BindingResult binding) {
		ModelAndView result;

		try {
			serviceService.delete(service);
			result = new ModelAndView("redirect:list.do?");
		} catch (Throwable oops) {
			result = createEditModelAndView(service, "service.commit.error");
		}

		return result;
	}

	// Ancillary Methods
	// ----------------------------------------------------------

	protected ModelAndView createEditModelAndView(ServiceEntity service) {
		ModelAndView result;

		result = createEditModelAndView(service, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(ServiceEntity service, String message) {
		ModelAndView result;

		result = new ModelAndView("service/edit");
		result.addObject("serviceEntity", service);
		result.addObject("message", message);

		return result;
	}
}
