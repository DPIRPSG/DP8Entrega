package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Folder;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Integer> {

	@Query("select f from Folder f where f.actor.id = ?1")
	Collection<Folder> findAllByActorId(int actorId);
	
	@Query("select f from Folder f where f.name = ?1 and f.isSystem = ?2 and f.actor.id = ?3")
	Collection<Folder> findByNameActorIDIsSystem(String name, boolean isSystem, int actorId);
}
