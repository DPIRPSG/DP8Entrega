package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
