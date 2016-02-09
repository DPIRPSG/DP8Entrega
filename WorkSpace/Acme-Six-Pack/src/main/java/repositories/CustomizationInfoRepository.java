package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.CustomizationInfo;

@Repository
public interface CustomizationInfoRepository extends JpaRepository<CustomizationInfo, Integer> {

}
