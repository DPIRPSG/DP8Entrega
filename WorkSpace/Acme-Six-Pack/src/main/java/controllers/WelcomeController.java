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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CustomizationInfoService;
import services.ExchangeRateService;
import services.ItemService;
import domain.CustomizationInfo;
import domain.ExchangeRate;
import domain.Item;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	// Services ----------------------------------------------------------

	@Autowired
	private ItemService itemService;

	@Autowired
	private ExchangeRateService exchangeRateService;
	
	@Autowired
	private CustomizationInfoService customizationInfoService;

	// Constructors -----------------------------------------------------------

	public WelcomeController() {
		super();
	}

	// Index ------------------------------------------------------------------

	@RequestMapping(value = "/index")
	public ModelAndView index(
			@RequestParam(required = false, defaultValue = "John Doe") String name,
			@RequestParam(required = false) Integer exchangeRateId,
			@CookieValue(value = "customizationInfo", required = false) CustomizationInfo customizationInfo,
			@RequestParam(required = false) String customizationInfoId,
			@RequestParam(required = false, defaultValue = "") String messageStatus,
			HttpServletResponse response) {
		ModelAndView result;
		SimpleDateFormat formatter;
		String moment;
		ExchangeRate exchangeRate;
		Collection<ExchangeRate> moneyList;
		Collection<CustomizationInfo> customizations;
		int customId;
		boolean hayItem, noHayItem;
		
		hayItem = true;
		noHayItem = false;

		exchangeRate = null;
		moneyList = exchangeRateService.findAll();
		customizations = customizationInfoService.findAll();

		if (exchangeRateId != null) {
			exchangeRate = exchangeRateService.findOne(exchangeRateId);
		} else {
			exchangeRate = exchangeRateService.findOneByName("Euros");
		}

		Collection<Item> items;
		Item item;

		items = itemService.findItemBestSelling();
		
		if(items.isEmpty()) {
			hayItem = false;
			noHayItem = true;
			item = null;
		} else {
		item = items.iterator().next();
		}
		

		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		moment = formatter.format(new Date());

		result = new ModelAndView("welcome/index");
		result.addObject("name", name);
		result.addObject("item", item);
		result.addObject("moment", moment);
		result.addObject("moneyList", moneyList);
		result.addObject("exchangeRate", exchangeRate);
		result.addObject("customizations", customizations);
		result.addObject("hayItem", hayItem);
		result.addObject("noHayItem", noHayItem);
		
		if(messageStatus != ""){
			result.addObject("messageStatus", messageStatus);
		}

		if(customizationInfo == null){
			customizationInfoId = "1";
		}
		
		if(customizationInfoId != null){
			customId = Integer.valueOf(customizationInfoId);
			customizationInfo = customizationInfoService.findOne(customId);
			response.addCookie(new Cookie("customLogo", customizationInfo.getLogo()));
			response.addCookie(new Cookie("customName", customizationInfo.getName()));
			response.addCookie(new Cookie("customDescrip", customizationInfo.getDescription()));
			response.addCookie(new Cookie("customWelcome", customizationInfo.getWelcomeMessage()));
			response.addCookie(new Cookie("customizationInfo", String.valueOf(customizationInfo.getId())));
			result = new ModelAndView("redirect:");
		}		

		return result;
	}
}