package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.ExchangeRate;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Integer> {

	@Query("select e from ExchangeRate e where e.name = ?1")
	ExchangeRate findOneByName(String name);

}
