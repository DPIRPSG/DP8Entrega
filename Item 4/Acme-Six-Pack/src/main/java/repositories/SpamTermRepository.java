package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.SpamTerm;

@Repository
public interface SpamTermRepository extends JpaRepository<SpamTerm, Integer> {

	@Query("select case when (count(i) > 0)  then true else false end from SpamTerm i where ?1 like concat('%',i.term,'%')")
	boolean checkSparmTermByWords(String words);

}
