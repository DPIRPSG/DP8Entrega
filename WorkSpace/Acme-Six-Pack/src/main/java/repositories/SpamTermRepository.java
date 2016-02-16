package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.SpamTerm;

@Repository
public interface SpamTermRepository extends JpaRepository<SpamTerm, Integer> {

	@Query("select i from SpamTerm i")
	boolean checkSpamTerm(String text);

}
