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
			@RequestParam(required = false, defaultValue = "") String messageStatus) {
		ModelAndView result;
		SimpleDateFormat formatter;
		String moment;
		Collection<ServiceEntity> services;
		ServiceEntity service;
		
		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		moment = formatter.format(new Date());
		if(actorService.checkAuthority("CUSTOMER")){
			services = serviceService.findAllNotBookedByCustomerId(actorService.findByPrincipal().getId());
			Random rnd = new Random();
			int i = rnd.nextInt(services.size());
			service = (ServiceEntity) services.toArray()[i];
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

		return result;
	}
}