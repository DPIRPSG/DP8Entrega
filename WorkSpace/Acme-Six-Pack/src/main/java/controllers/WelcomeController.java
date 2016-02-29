/* WelcomeController.java
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ServiceService;

import domain.ServiceEntity;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	// Services ----------------------------------------------------------

	@Autowired
	private ServiceService serviceService;
	
	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------

	public WelcomeController() {
		super();
	}

	// Index ------------------------------------------------------------------

	@RequestMapping(value = "/index")
	public ModelAndView index(
			@RequestParam(required = false, defaultValue = "") String messageStatus
			,@CookieValue(value = "createCreditCard", required = false, defaultValue = "false") String createCreditCard
			,@CookieValue(value = "createSocialIdentity", required = false, defaultValue = "false") String createSocialIdentity
			) {
		ModelAndView result;
		SimpleDateFormat formatter;
		String moment;
		Collection<ServiceEntity> services;
		ServiceEntity service;
		
		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		moment = formatter.format(new Date());
		if(actorService.checkAuthority("CUSTOMER")){
			try{
				services = serviceService.findAllPaidAndNotBookedByCustomerId(actorService.findByPrincipal().getId());
				if(!services.isEmpty()){
					Random rnd = new Random();
					int i = rnd.nextInt(services.size());
					service = (ServiceEntity) services.toArray()[i];
				}else{
					services = null;
					service = null;
				}
			}catch(org.springframework.dao.DataIntegrityViolationException oops){
				services = null;
				service = null;
			}
		}else{
			services = null;
			service = null;
		}
		

		result = new ModelAndView("welcome/index");
		result.addObject("moment", moment);
		result.addObject("service", service);
		
		if(messageStatus != ""){
			result.addObject("messageStatus", messageStatus);
		}
		
		if(createCreditCard.equals("true") && actorService.checkAuthority("CUSTOMER")){
			result = new ModelAndView("redirect:/creditCard/customer/edit.do");
		}else if(createSocialIdentity.equals("true") && actorService.checkAuthority("CUSTOMER")){
			result = new ModelAndView("redirect:/socialIdentity/customer/edit.do");			
		}

		return result;
	}
}