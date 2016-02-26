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

import services.FeePaymentService;

import controllers.AbstractController;
import domain.FeePayment;


@Controller
@RequestMapping("/feePayment/administrator")
public class FeePaymentAdministratorController extends AbstractController {
	
	// Services ----------------------------------------------------------
	
	@Autowired
	private FeePaymentService feePaymentService;
	
	
	// Constructors --------------------------------------------------------
	
	public FeePaymentAdministratorController() {
		super();
	}
	
	// Deleting --------------------------------------------------------------
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<FeePayment> feePayments;
		
		feePayments = feePaymentService.findAll();
		
		result = new ModelAndView("feePayment/list");
		result.addObject("feePayments", feePayments);
		result.addObject("requestURI", "feePayment/administrator/list.do");
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int feePaymentId) {
		ModelAndView result;
		FeePayment feePayment;
		
		feePayment = feePaymentService.findOne(feePaymentId);
		
		result = createEditModelAndView(feePayment);
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid FeePayment feePayment, BindingResult binding) {
		ModelAndView result;
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(feePayment);
		} else {
		try {
			feePaymentService.save(feePayment);
			result = new ModelAndView("redirect:/feePayment/administrator/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(feePayment, "feePayment.commit.error");
		}
		}
		return result;
	}
	
	// Ancillary methods ---------------------------------------------------
	
	protected ModelAndView createEditModelAndView(FeePayment feePayment) {
		ModelAndView result;
		
		result = createEditModelAndView(feePayment, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(FeePayment feePayment, String message) {
		ModelAndView result;
		
		result = new ModelAndView("feePayment/edit");
		result.addObject("feePayment", feePayment);
		result.addObject("message", message);
		
		return result;
	}
	
}
