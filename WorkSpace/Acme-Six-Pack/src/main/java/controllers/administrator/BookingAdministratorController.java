package controllers.administrator;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BookingService;

import controllers.AbstractController;
import domain.Booking;

@Controller
@RequestMapping(value = "/booking/administrator")
public class BookingAdministratorController extends AbstractController {

	// Services ----------------------------------------------------------

	@Autowired
	private BookingService bookingService;

	// Constructors ----------------------------------------------------------

	public BookingAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Booking> bookings;
		Date moment;
		
		moment = new Date();

		bookings = bookingService.findAll();

		result = new ModelAndView("booking/list");
		result.addObject("requestURI", "booking/administrator/list.do?");
		result.addObject("bookings", bookings);
		result.addObject("moment", moment);

		return result;
	}

	// Creation ----------------------------------------------------------

	

	// Edition ----------------------------------------------------------

	
	
	// Approve
	@RequestMapping(value="/approve", method = RequestMethod.GET)
	public ModelAndView approve(@RequestParam int bookingId){
		
		ModelAndView result;
		Booking booking;
		
		try{
			booking = bookingService.findOne(bookingId);
			Assert.notNull(booking);
			
			bookingService.approveBooking(booking);
			result = new ModelAndView("redirect: list.do");
			result.addObject("messageStatus", "booking.approve.ok");
		}catch(Throwable ops){
			result = new ModelAndView("redirect: list.do");
			result.addObject("messageStatus", "booking.approve.error");
		}
		
		return result;
	}
	
	// Deny
	@RequestMapping(value="/deny", method = RequestMethod.GET)
	public ModelAndView deny(@RequestParam int bookingId){
		
		ModelAndView result;
		Booking booking;
		
		try{
			booking = bookingService.findOne(bookingId);
			Assert.notNull(booking);
			
			bookingService.denyBooking(booking);
			result = new ModelAndView("redirect: list.do");
			result.addObject("messageStatus", "booking.deny.ok");
		}catch(Throwable ops){
			result = new ModelAndView("redirect: list.do");
			result.addObject("messageStatus", "booking.deny.error");
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

		result = new ModelAndView("booking/list");
		result.addObject("booking", booking);
		result.addObject("message", message);

		return result;
	}
}
