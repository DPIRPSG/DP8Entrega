package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
	@Query("select c from Comment c where c.commentedEntity.id = ?1 and c.deleted = false")
	Collection<Comment> findAllByCommentedEntityId(int commentedEntityId);
	
	// ---------------- Dashboard ------------------
	
//	The average number of comments written by the actors,
//	@Query("select count(distinct c1)*1.0/count(cu)*1.0 from Comment c1, Comment c2, Customer cu where c1.actor = c2.actor")
//	Double averageByActors();
	// select count(distinct c1) from Comment c1, Comment c2 where c1.actor = c2.actor;
	// select count(cu) from Customer cu;
	
	//select count(distinct c1)*1.0/count(cu)*1.0 from Comment c1, Customer cu group by c1.actor having c1.actor IN (select c2.actor from Comment c2);
	
//	including the standard deviation.
//	@Query("select c from Comment c where c.service is not null")
//	Double averageByActorsStandardDeviation();
	
//	The average number of comments per gym.
//	@Query("select avg(c) from Comment c where c.gym is not null and c.gym = (select g from Gym g)")
//	@Query("select count(c) from Gym g join g.comments c group by g")
//	Double averagePerGym();
	
//	The average number of comments per service.
//	@Query("select count(c) from ServiceEntity s join s.comments c group by s")
//	Double averagePerService();
	
	
}
