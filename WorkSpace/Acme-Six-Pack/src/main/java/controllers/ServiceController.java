package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping(value = "/service")
public class ServiceController extends AbstractController {

	// Services ----------------------------------------------------------

	@Autowired
	private ServiceService serviceService;
	
	@Autowired
	private GymService gymService;

	// Constructors ----------------------------------------------------------

	public ServiceController() {
		super();
	}

	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required=false) Integer gymId) {
		ModelAndView result;
		Collection<ServiceEntity> services;
		Collection<String> customers;
				
		result = new ModelAndView("service/list");
		result.addObject("requestURI", "service/list.do?");

		services = serviceService.findAll();
		
		if (gymId != null) {
			services = serviceService.findAllByGym(gymId);
		}
		
		customers = serviceService.numbersOfCustomersByService(services);

		
		result.addObject("services", services);
		result.addObject("customers", customers);

		return result;
	}
}
