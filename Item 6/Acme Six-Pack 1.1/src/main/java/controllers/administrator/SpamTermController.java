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

import services.SpamTermService;

import controllers.AbstractController;
import domain.SpamTerm;

@Controller
@RequestMapping(value = "/spamTerm/administrator")
public class SpamTermController extends AbstractController{

	//Services ----------------------------------------------------------

	@Autowired
	private SpamTermService spamTermService;
	
	//Constructors ----------------------------------------------------------
	
	public SpamTermController(){
		super();
	}

	//Listing ----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
        ModelAndView result;
        Collection<SpamTerm> spamTerms;
        
        spamTerms = spamTermService.findAll();
        
        result = new ModelAndView("spamTerm/list");
        result.addObject("spamTerms", spamTerms);
        result.addObject("requestURI", "spamTerm/administrator/list.do");
        
        return result;
	}
	
	// Creation ----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		SpamTerm spamTerm;
		
		spamTerm = spamTermService.create();
		
		result = createEditModelAndView(spamTerm);
		
		return result;
	}

	// Edition ----------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int spamTermId) {
	
		ModelAndView result;
		SpamTerm spamTerm;
		
		spamTerm = spamTermService.findOne(spamTermId);
		Assert.notNull(spamTerm);
		
		result = createEditModelAndView(spamTerm);
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid SpamTerm spamTerm, BindingResult binding) {
		ModelAndView result;
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(spamTerm);
		} else {
			try {
				spamTermService.save(spamTerm);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(spamTerm, "spamTerm.commit.error");				
			}
		}

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid SpamTerm spamTerm, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(spamTerm);
		} else {
			try {
				spamTermService.delete(spamTerm);		
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(spamTerm, "spamTerm.commit.error");								
			}
		}

		return result;
	}

	// Ancillary Methods ----------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(SpamTerm spamTerm) {
		ModelAndView result;
		
		result = createEditModelAndView(spamTerm, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(SpamTerm spamTerm, String message){
		ModelAndView result;
		
		result = new ModelAndView("spamTerm/edit");
		result.addObject("spamTerm", spamTerm);
		result.addObject("message", message);
		
		return result;
	}

}
