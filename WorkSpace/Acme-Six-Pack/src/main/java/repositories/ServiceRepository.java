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
	
}
