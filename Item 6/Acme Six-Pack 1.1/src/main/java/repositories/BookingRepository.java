package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

	@Query("select b from Booking b where b.customer.id = ?1")
	Collection<Booking> findAllByCustomer(int customerId);
	
}
