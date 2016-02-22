package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.ServiceEntity;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Integer> {

	@Query("select s from ServiceEntity s join s.gyms g where g.id = ?1")
	Collection<ServiceEntity> findAllByGym(int gymId);

	@Query("select s from ServiceEntity s where s.name like ?1")
	ServiceEntity findOneByName(String name);

	@Query("select s from ServiceEntity s where s.name not like 'Fitness'")
	Collection<ServiceEntity> findAllWithoutFitness();
	//select s from ServiceEntity s group by s having s != (select distinct(s) from Customer c join c.booking b join b.service s join c.feePayment f where c.id = ?1 and f.gym in (select distinct(g) from Customer c join c.booking b join b.service s join s.gyms g where c.id = ?1))
	//select s from ServiceEntity s group by s having s != (select distinct(s) from Customer c join c.booking b join b.service s where c.id = ?1)")
	@Query("select s from ServiceEntity s group by s having s != (select distinct(s) from Customer c join c.booking b join b.service s where c.id = ?1) and s in (select distinct(s) from Customer c join c.feePayment f join f.gym.service s where c.id = ?1 and f.activeMoment < CURRENT_DATE and f.inactiveMoment > CURRENT_DATE)")
	Collection<ServiceEntity> findAllPaidAndNotBookedByCustomerId(int customerId);
	
	// ---------------- Dashboard ------------------
	
	// The service/s that has/have more comments.
	@Query("select s from ServiceEntity s join s.comments c group by s having count(s.comments.size) = (select max(s.comments.size) from ServiceEntity s)")
	// Another option to do it:
	// @Query("select s1 from Comment c1 join c1.service s1 group by s1 having count(c1) >= all(select count(c2) from ServiceEntity s2 join s2.comments c2 group by s2)")
	Collection<ServiceEntity> findMostCommented();
	
}
