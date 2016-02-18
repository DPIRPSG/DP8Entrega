package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.FeePayment;

@Repository
public interface FeePaymentRepository extends JpaRepository<FeePayment, Integer> {

	@Query("select f from FeePayment f where f.activeMoment < ?1 and f.inactiveMoment > ?1")
	Collection<FeePayment> findAllActive();

}
