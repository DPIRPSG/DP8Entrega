package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.SpamTerm;

@Repository
public interface SpamTermRepository extends JpaRepository<SpamTerm, Integer> {

}
