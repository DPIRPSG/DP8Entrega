package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Content;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {
	
	@Query("select c from Content c where c.shoppingCart.id = ?1 and c.item.id = ?2")
	Content findByShoppingCartIdAndItemId(int shoppingCartId, int itemId);

	@Query("select c from Content c where c.shoppingCart.id = ?1")
	Collection<Content> findByShoppingCartID(int shoppingCartId);

	@Query("Select c from Content c where c.item.id = ?1")
	Collection<Content> findAllByItemId(int itemId);
	
}
