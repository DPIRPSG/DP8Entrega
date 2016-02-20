package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.FeePayment;

@Repository
public interface FeePaymentRepository extends JpaRepository<FeePayment, Integer> {

	@Query("select f from FeePayment f where f.activeMoment < ?1 and f.inactiveMoment > ?1")
	Collection<FeePayment> findAllActive(Date moment);

	@Query("select f from FeePayment f where f.customer.id = ?1 order by f.paymentMoment desc")
	Collection<FeePayment> findAllByCustomer(int customerId);

	@Query("select f from FeePayment f where f.customer.id = ?1 and f.gym.id = ?2 order by f.paymentMoment desc")
	Collection<FeePayment> findAllByCustomerAndGym(int customerId, int gymId);

}
