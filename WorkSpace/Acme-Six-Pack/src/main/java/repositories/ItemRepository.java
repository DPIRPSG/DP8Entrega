package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

	@Query("select i from Item i where i.deleted is false and i.sku IN (select oi.sku from OrderItem oi group by oi.sku having sum(oi.units)>=ALL(select sum(oi.units) from OrderItem oi group by oi.sku having oi.sku IN (select i.sku from Item i where i.deleted is false)))")
	Collection<Item> findItemBestSelling();
	
	@Query("select i from Item i where i.deleted is false and i.sku IN (select oi.sku from OrderItem oi group by oi.sku having sum(oi.units)<=ALL(select sum(oi.units) from OrderItem oi group by oi.sku having oi.sku IN (select i.sku from Item i where i.deleted is false)))")
	Collection<Item> findItemWorstSelling();
	
	@Query("select i from Item i where i.deleted is false and i.comments.size = (select max(i.comments.size) from Item i where i.deleted is false) group by i")
	Collection<Item> findItemMoreComments();
	
	@Query("select i from Item i where i.deleted = false order by i.category")
	Collection<Item> findAllNotDeleted();
	
	@Query("select i from Item i where i.deleted = false and i.category.id = ?1")
	Collection<Item> findAllByCategoryId(int categoryId);
	
	@Query("select i from Item i where i.category.id = ?1")
	Collection<Item> findAllNotDeletedByCategoryId(int categoryId);
	
	@Query("select i from Item i join i.storages s where i.deleted = false and s.wareHouse.id = ?1")
	Collection<Item> findAllByWareHouseId(int wareHouseId);
	
	@Query("select i from Item i where i.category.tax.id = ?1")
	Collection<Item> findByTaxId(int taxId);
	
//	@Query("select i from Item i where i.deleted = false and i.sku like '%?1%' or i.name like '%?1%' or i.description like '%?1%'")
//	Collection<Item> findBySingleKeyword(String keyword);
	
	@Query("select i from Item i where i.deleted = false and (i.sku like concat('%',?1,'%') or i.name like concat('%',?1,'%') or i.description like concat('%',?1,'%'))")
	Collection<Item> findBySingleKeyword(String keyword);
	
	@Query("select c.item from ShoppingCart s join s.contents c where c.item.deleted = false and s.id = ?1")
	Collection<Item> findAllByShoppingCartId(int shoppingCartId);

	@Query("Select i from Item i where i.sku like ?1")
	Item findOneBySKU(String sku);
}
