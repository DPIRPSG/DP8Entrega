package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.ShoppingCart;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

	@Query("select s from ShoppingCart s where s.consumer.id = ?1")
	ShoppingCart findByConsumerId(int consumerId);
}
