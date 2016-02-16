package repositories;


import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	@Query("select c from Customer c where c.userAccount.id = ?1")
	Customer findByUserAccountId(int userAccountId);
	
	@Query("select distinct b.customer from Booking b where b.service.id = ?1")
	Collection<Customer> findByServiceBooked(int serviceId);
	
	@Query("select distinct b.customer from Booking b where b.gym.id = ?1")
	Collection<Customer> findByGymBooked(int gymId);
}
