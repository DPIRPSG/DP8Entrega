package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Gym;

@Repository
public interface GymRepository extends JpaRepository<Gym, Integer> {

	@Query("select g from Gym g where (g.name like concat('%',?1,'%') or g.description like concat('%',?1,'%'))")
	Collection<Gym> findBySingleKeyword(String keyword);
	
	@Query("select g from Gym g join g.services s where s.id = ?1")
	Collection<Gym> findAllByService(int serviceId);

	@Query("select g from Gym g join g.feePayments f where f.activeMoment < ?1 and f.inactiveMoment > ?1 and f.customer.id = ?2")
	Collection<Gym> findAllWithFeePaymentActive(Date moment, int customerId);
}
