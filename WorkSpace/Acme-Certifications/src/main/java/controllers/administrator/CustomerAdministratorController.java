/* CustomerAdministratorController.java
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

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

import security.Authority;
import services.CustomerService;
import controllers.AbstractController;
import domain.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerAdministratorController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private CustomerService customerService;
	
	// Constructors -----------------------------------------------------------
	
	public CustomerAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list")
	public ModelAndView list() {
		ModelAndView mav;
		Collection<Customer> customers;

		customers = customerService.findAll();

		mav = new ModelAndView("customer/list");
		mav.addObject("customers", customers);

		return mav;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Customer customer;

		customer = customerService.create();
		result = createEditView(customer);

		return result;
	}
	
	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int customerId) {
		ModelAndView result;
		Customer customer;

		customer = customerService.findOne(customerId);
		result = createEditView(customer);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid  Customer customer,
			BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = createEditView(customer);
		else {
			customerService.save(customer);
			result = new ModelAndView("redirect:list.do");
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(
	// We do not require @Valid objects so that they can be deleted.
			 Customer customer) {
		ModelAndView result;

		customerService.delete(customer);
		result = new ModelAndView("redirect:list.do");

		return result;
	}
	
	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditView(Customer customer) {
		ModelAndView result;
		Collection<Authority> authorities;

		authorities = Authority.listAuthorities();

		result = new ModelAndView("customer/edit");
		result.addObject("customer", customer);
		result.addObject("authorities", authorities);

		return result;
	}

}