package controllers.customer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.BookingService;

import controllers.AbstractController;
import domain.Booking;

@Controller
@RequestMapping(value = "/booking/customer")
public class BookingCustomerController extends AbstractController {

	// Services ----------------------------------------------------------

	@Autowired
	private BookingService bookingService;

	// Constructors ----------------------------------------------------------

	public BookingCustomerController() {
		super();
	}

	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Booking> bookings;

		bookings = bookingService.findAllByCustomer();

		result = new ModelAndView("booking/list");
		result.addObject("requestURI", "booking/list.do?");
		result.addObject("bookings", bookings);

		return result;
	}

	// Creation ----------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Booking booking;

		booking = bookingService.create();
		result = createEditModelAndView(booking);

		return result;
	}

	// Edition ----------------------------------------------------------

	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public ModelAndView cancel(Booking booking, BindingResult binding) {
		ModelAndView result;

		try {
			bookingService.cancel(booking);
			result = new ModelAndView("redirect:list.do");
			result.addObject("messageStatus", "booking.cancel.ok");
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:list.do");
			result = createEditModelAndView(booking, "booking.cancel.error");
		}

		return result;
	}
	

	// Ancillary Methods
	// ----------------------------------------------------------

	protected ModelAndView createEditModelAndView(Booking booking) {
		ModelAndView result;

		result = createEditModelAndView(booking, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Booking booking, String message) {
		ModelAndView result;

		result = new ModelAndView("booking/edit");
		result.addObject("booking", booking);
		result.addObject("message", message);

		return result;
	}
}
