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
	
	/* == DASHBOARD == */
	
	/* Query 1 */
	@Query("select g1 from Gym g1 left join g1.feePayments f1 where f1.activeMoment < CURRENT_DATE AND f1.inactiveMoment > CURRENT_DATE group by g1 having count(f1.activeMoment) >= all(select count(f2.activeMoment) from Gym g2 left join g2.feePayments f2 where f2.activeMoment < CURRENT_DATE AND f2.inactiveMoment > CURRENT_DATE group by g2)")
	Collection<Gym> findMostPopularGyms();
	
	/* Query 2 */
	@Query("select g1 from Gym g1 join g1.feePayments f1 where f1.activeMoment < CURRENT_DATE AND f1.inactiveMoment > CURRENT_DATE group by g1 having count(f1.activeMoment) <= all(select count(f2.activeMoment) from Gym g2 join g2.feePayments f2 where f2.activeMoment < CURRENT_DATE AND f2.inactiveMoment > CURRENT_DATE group by g2)")
	Collection<Gym> findLeastPopularGym();
	
	/* Query 9 */
	@Query("select g from Gym g left join g.comments c group by g having count(c) >= all(select count(c) from Gym g left join g.comments c group by g)")
	Collection<Gym> findMostCommented();
	
	/* Query 12 */
	@Query("select avg(g.comments.size) from Gym g")
	Double findAverageNumberOfCommentsPerGym();
}
