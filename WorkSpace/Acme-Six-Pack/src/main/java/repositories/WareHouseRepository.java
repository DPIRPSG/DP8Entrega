package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.WareHouse;

@Repository
public interface WareHouseRepository extends JpaRepository<WareHouse, Integer> {

	@Query("select w from WareHouse w join w.storages s where s.item.id = ?1")
	Collection<WareHouse> findAllByItemId(int itemId);
}
