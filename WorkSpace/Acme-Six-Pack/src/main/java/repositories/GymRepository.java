package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Gym;

@Repository
public interface GymRepository extends JpaRepository<Gym, Integer> {

	@Query("select g from Gym g where (g.name like concat('%',?1,'%') or g.description like concat('%',?1,'%'))")
	Collection<Gym> findBySingleKeyword(String keyword);
	
	@Query("select g from Gym g join g.service s where s.id = ?1")
	Collection<Gym> findAllByService(int serviceId);
}
