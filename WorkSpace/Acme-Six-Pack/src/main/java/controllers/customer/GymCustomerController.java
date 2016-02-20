package controllers.customer;

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
@RequestMapping(value = "/gym/customer")
public class GymCustomerController extends AbstractController {

	// Services ----------------------------------------------------------

	@Autowired
	private GymService gymService;

	// Constructors ----------------------------------------------------------

	public GymCustomerController() {
		super();
	}

	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/list-feepayments-active", method = RequestMethod.GET)
	public ModelAndView listFeePaymentsActive(@RequestParam(required=false, defaultValue="") String keyword) {
		ModelAndView result;
		Collection<Gym> gyms;
		Collection<String> customers;
		String keywordToFind;
		Boolean paid;
		
		paid = true;

		gyms = gymService.findAllWithFeePaymentActive();
		
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
		result.addObject("requestURI", "gym/customer/list-feepayments-active.do?");
		result.addObject("gyms", gyms);
		result.addObject("customers", customers);
		result.addObject("paid", paid);

		return result;
	}
	
	@RequestMapping(value = "/list-feepayments-not-active", method = RequestMethod.GET)
	public ModelAndView listFeePaymentsNotActive(@RequestParam(required=false, defaultValue="") String keyword) {
		ModelAndView result;
		Collection<Gym> gyms;
		Collection<String> customers;
		String keywordToFind;
		Boolean paid;
		
		paid = false;

		gyms = gymService.findAllWithoutFeePaymentActive();
		
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
		result.addObject("requestURI", "gym/customer/list-feepayments-not-active.do?");
		result.addObject("gyms", gyms);
		result.addObject("customers", customers);
		result.addObject("paid", paid);

		return result;
	}
}
