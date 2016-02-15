package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Booking;

import repositories.BookingRepository;

@Service
@Transactional
public class BookingService {
	
	// Managed repository -----------------------------------------------------
	
	@Autowired
	private BookingRepository bookingRepository;

	// Supporting services ----------------------------------------------------

	private ActorService actorService;
	
	// Constructors -----------------------------------------------------------

	public BookingService() {
		super();
	}
	
	// Simple CRUD methods ----------------------------------------------------

	/**
	 * 
	 * @return Devuelve booking preparado para ser modificado. Necesita usar save para que persista en la base de datos.
	 */
	// Requisito 10.3
	public Booking create(){
		
		Booking result;
		
		result = new Booking();
		
		// Faltan por poner más valores por defecto a los atributos
		
		return result;
	}
	
	/**
	 * 
	 * @param booking
	 * 
	 * Guarda un booking creado o modificado
	 */
	// Requisito 10.3 - Book a service as long as he or she has paid the corresponding fee.
	public void save(Booking booking){
		
		Assert.notNull(booking);
		Assert.isTrue(actorService.checkAuthority("CUSTOMER"), "Only a customer can book services");
		
		bookingRepository.save(booking);
		
	}
	
	/**
	 * 
	 * @param booking
	 * 
	 * Marca como cancelado un booking
	 */
	// Requisito 10.4 - Cancel a booking as long as no administrator has approved or denied it.
	public void cancel(Booking booking){
		
		Assert.notNull(booking);
		Assert.isTrue(booking.getId() != 0);
		Assert.isTrue(actorService.checkAuthority("CUSTOMER"), "Only a customer can cancel a booking");
		Assert.isTrue(!booking.getApproved(), "The selected booking is already approved");
		Assert.isTrue(!booking.getDenied(), "The selected booking is already denied");
		
		booking.setCanceled(true);
		this.save(booking);
		
		// En el código de borrar items de Acme Supermarket se hace alusión a un borrado completo. Falta por hacerlo.
		
	}
	
	/**
	 * 
	 * @return Devuelve todos los booking de la base de datos
	 */
	public Collection<Booking> findAll(){
		
		Collection<Booking> result;
		
		result = bookingRepository.findAll();
		
		return result;
	}
	
	/**
	 * 
	 * @param bookingId Id del Booking en cuestión
	 * @return Devuelve el booking en cuestión
	 */
	public Booking findOne(int bookingId){
		
		Booking result;
		
		result = bookingRepository.findOne(bookingId);
		
		return result;
	}
	
	// Other business methods -------------------------------------------------

	/**
	 * 
	 * @param booking
	 * 
	 * Marca como aprobado un booking
	 */
	// Requisito 11.2 - Approve or deny a service booking.
	public void approveBooking(Booking booking){
		
		Assert.notNull(booking);
		Assert.isTrue(booking.getId() != 0);
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can approve a booking");
		Assert.isTrue(!booking.getApproved(), "The selected booking is already approved");
		Assert.isTrue(!booking.getDenied(), "The selected booking is already denied");
		Assert.isTrue(!booking.getCanceled(), "The selected booking is already canceled");
		
		booking.setApproved(true);
		this.save(booking);
		
	}
	
	/**
	 * 
	 * @param booking
	 * 
	 * Marca como denegado un booking
	 */
	// Requisito 11.2 - Approve or deny a service booking.
	public void denyBooking(Booking booking){
		
		Assert.notNull(booking);
		Assert.isTrue(booking.getId() != 0);
		Assert.isTrue(actorService.checkAuthority("ADMIN"), "Only an admin can approve a booking");
		Assert.isTrue(!booking.getApproved(), "The selected booking is already approved");
		Assert.isTrue(!booking.getDenied(), "The selected booking is already denied");
		Assert.isTrue(!booking.getCanceled(), "The selected booking is already canceled");
		
		booking.setDenied(true);
		this.save(booking);
		
	}
}
