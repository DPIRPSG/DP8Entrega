package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.TaxService;
import controllers.AbstractController;
import domain.Tax;

@Controller
@RequestMapping(value = "/tax/administrator")
public class TaxAdministratorController extends AbstractController {
	//Services ----------------------------------------------------------
	
	@Autowired
	private TaxService taxService;
	
	//Constructors ----------------------------------------------------------

	public TaxAdministratorController() {
		super();
	}
	
	//Listing ----------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Tax> taxes;
		
		taxes = taxService.findAll();
		
		result = new ModelAndView("tax/list");
		result.addObject("requestURI", "tax/administrator/list.do");
		result.addObject("taxes", taxes);
		
		return result;
	}
	
	//Creation ----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Tax tax;
		
		tax = taxService.create();		
		result = createEditModelAndView(tax);
		
		return result;
	}
	
	//Edition ----------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int taxId) {
		ModelAndView result;
		Tax tax;
		
		tax = taxService.findOne(taxId);
		Assert.notNull(tax);
		result = createEditModelAndView(tax);
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Tax tax, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(tax);
		} else {
			try {
				taxService.save(tax);		
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(tax, "tax.commit.error");				
			}
		}

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Tax tax, BindingResult binding) {
		ModelAndView result;

		try {
			taxService.delete(tax);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(tax,
					"tax.commit.error");
		}

		return result;
	}
	
	//Ancillary Methods ----------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(Tax tax) {
		ModelAndView result;
		
		result = createEditModelAndView(tax, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Tax tax, String message) {
		ModelAndView result;
	
		result = new ModelAndView("tax/edit");
		result.addObject("tax", tax);
		result.addObject("message", message);
		
		return result;
	}	
}
