package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.CustomizationInfo;

import services.CustomizationInfoService;

@Controller
@RequestMapping("/about-us")
public class AboutUsController extends AbstractController {

	// Services ----------------------------------------------------------

	@Autowired
	private CustomizationInfoService customizationInfoService;

	// Constructors -----------------------------------------------------------

	public AboutUsController() {
		super();
	}
	
	//About Us
	
	@RequestMapping(value = "/about-us")
	public ModelAndView aboutUs(@RequestParam(required=false, defaultValue="94") int customizationInfoId){
		ModelAndView result;
		CustomizationInfo customizationInfo;
		
		customizationInfo = customizationInfoService.findOne(customizationInfoId);
		
		result = new ModelAndView("about-us/about-us");
		result.addObject("customizationInfo", customizationInfo);
		
		return result;
	}

}
