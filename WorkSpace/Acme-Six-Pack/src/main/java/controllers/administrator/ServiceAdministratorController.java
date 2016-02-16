package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public ModelAndView list() {
		ModelAndView result;
		Collection<ServiceEntity> services;

		services = serviceService.findAll();

		result = new ModelAndView("service/list");
		result.addObject("requestURI", "service/list.do?");
		result.addObject("services", services);

		return result;
	}
}
