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
	
	/* == DASHBOARD == */

	/* Query 5 */
	@Query("select c from Customer c left join c.feePayments f group by c having count(f) >= all(select count(f) from Customer c left join c.feePayments f group by c)")
	Collection<Customer> findCustomerWhoHasPaidMoreFees();
	
	/* Query 6 */
	@Query("select c from Customer c left join c.feePayments f group by c having count(f) <= all(select count(f) from Customer c left join c.feePayments f group by c)")
	Collection<Customer> findCustomerWhoHasPaidLessFees();
	
	/* Query 14 */
	@Query("select c from Customer c left join c.comments k where k.deleted IS TRUE group by c having count(k) >= all(select count(k) from Customer c left join c.comments k where k.deleted IS TRUE group by c)")
	Collection<Customer> findCustomerWhoHaveBeenRemovedMoreComments();
}
