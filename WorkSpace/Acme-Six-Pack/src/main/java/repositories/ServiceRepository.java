package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ServiceEntity;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Integer> {

}
