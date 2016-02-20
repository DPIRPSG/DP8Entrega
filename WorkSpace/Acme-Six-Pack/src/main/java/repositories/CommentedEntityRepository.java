package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.CommentedEntity;

@Repository
public interface CommentedEntityRepository extends JpaRepository<CommentedEntity, Integer> {

}
