package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.FeePayment;

@Repository
public interface FeePaymentRepository extends JpaRepository<FeePayment, Integer> {

}
