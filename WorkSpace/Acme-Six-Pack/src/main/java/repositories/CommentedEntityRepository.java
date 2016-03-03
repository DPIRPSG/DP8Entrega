package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.CommentedEntity;

@Repository
public interface CommentedEntityRepository extends JpaRepository<CommentedEntity, Integer> {

	@Query("select c from CommentedEntity c where c.id = ?1")
	CommentedEntity findOneById(int commentedEntityId);

}
