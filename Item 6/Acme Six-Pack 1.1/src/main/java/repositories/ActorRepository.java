package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

	@Query("select a from Actor a where a.userAccount.id = ?1")
	Actor findByUserAccountId(int userAccountId);
	
	/* == DASHBOARD == */

	/* Query 7 */
	@Query("select m1.sender from Folder f1 left join f1.messages m1 where f1.name = 'SpamBox' group by f1 having count(m1) >= all(select count(m2) from Folder f2 left join f2.messages m2 where f2.name = 'SpamBox' group by f2)")
	Collection<Actor> findActorWhoSendMoreSpam();
	
	/* Query 8 */
	@Query("select avg(a.sent.size) from Actor a")
	Double findAverageNumberOfMessagesInActorMessageBox();
	
	/* Query 11 */
	@Query("select avg(a.comments.size) from Actor a")
	Double findAverageNumberOfCommentWrittenByAnActor();
	
	/* Query 11 */
	@Query("select stddev(a.comments.size) from Actor a")
	Double findStandardDeviationNumberOfCommentWrittenByAnActor();
}
