package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Storage;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Integer> {

	@Query("select s from Storage s where s.wareHouse.id = ?1 and s.item.id = ?2")
	Storage findByWareHouseIdAndItemId(int wareHouseId, int itemId);

	@Query("select s from Storage s where s.wareHouse.id = ?1")
	Collection<Storage> findAllByWarehouseId(int warehouseId);

	@Query("select s from Storage s where s.item.id = ?1")
	Collection<Storage> findAllByItemId(int itemId);
}
