package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Tax;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Integer> {

	@Query("select c.tax from Category c where c.id = ?1")
	Tax findByCategoryId(int categoryId);
}
