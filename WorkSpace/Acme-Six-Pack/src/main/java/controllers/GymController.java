package controllers;

import java.util.ArrayList;
import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.GymService;

import controllers.AbstractController;
import domain.Gym;

@Controller
@RequestMapping(value = "/gym")
public class GymController extends AbstractController {

	// Services ----------------------------------------------------------

	@Autowired
	private GymService gymService;

	// Constructors ----------------------------------------------------------

	public GymController() {
		super();
	}

	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required=false, defaultValue="") String keyword, @RequestParam(required=false) Integer serviceId) {
		ModelAndView result;
		Collection<Gym> gyms;
		Collection<ArrayList<Integer>> customers;
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
		result.addObject("requestURI", "gym/list.do?");
		result.addObject("gyms", gyms);
		result.addObject("customers", customers);
		result.addObject("requestUri2", "service/list.do?");

		return result;
	}
}
