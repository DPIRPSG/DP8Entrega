package controllers.customer;

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
@RequestMapping(value = "/feePayment/customer")
public class FeePaymentCustomerController extends AbstractController {

	// Services ----------------------------------------------------------

	@Autowired
	private FeePaymentService feePaymentService;

	// Constructors ----------------------------------------------------------

	public FeePaymentCustomerController() {
		super();
	}

	// Listing ----------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam (required=false) Integer gymId) {
		ModelAndView result;
		Collection<FeePayment> feePayments;
		
		feePayments = feePaymentService.findAllByCustomer();
		
		if(gymId != null) {
			feePayments = feePaymentService.findAllByCustomerAndGym(gymId);
		}
		
		result = new ModelAndView("feePayment/list");
		result.addObject("feePayments", feePayments);
		result.addObject("requestURI", "feePayment/customer/list.do");
		
		return result;
	}
	
	// Creation ----------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int gymId) {
		ModelAndView result;
		FeePayment fee;

		fee = feePaymentService.create(gymId);
		result = createEditModelAndView(fee);

		return result;
	}

	// Edition ----------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid FeePayment fee, BindingResult binding) {
		ModelAndView result;
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(fee);
		} else {
			try {
				feePaymentService.save(fee);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(fee, "feePayment.commit.error");
			}
		}

		return result;
	}

	// Ancillary Methods
	// ----------------------------------------------------------

	protected ModelAndView createEditModelAndView(FeePayment fee) {
		ModelAndView result;

		result = createEditModelAndView(fee, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(FeePayment fee, String message) {
		ModelAndView result;
		
		result = new ModelAndView("feePayment/create");
		result.addObject("feePayment", fee);
		result.addObject("message", message);

		return result;
	}
}
