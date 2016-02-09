package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import domain.Customer;

@Repository
public interface ConsumerRepository extends JpaRepository<Customer, Integer> {

	@Query("select c from Consumer c join c.orders o group by c having count(c.orders.size) = (select max(c.orders.size) from Consumer c)")
	Collection<Customer> findConsumerMoreOrders();
	
	@Query("select c from Consumer c join c.orders o where o.cancelMoment is null group by c having max(o.amount) = (select max(o.amount) from Order o)")
	Collection<Customer> findConsumerSpentMoreMoney();
	
	@Query("select c1 from Order o1 join o1.consumer c1 where o1.cancelMoment is not null group by c1 having count(o1) >= all(select count(o2) from Consumer c2 join c2.orders o2 where o2.cancelMoment is not null group by c2)")
	Collection<Customer> findConsumerMoreOrdersCancelled();
	
	@Query("select c1 from Order o1 join o1.consumer c1 where o1.cancelMoment is not null group by c1 having count(o1) <= all(select count(o2) from Consumer c2 join c2.orders o2 where o2.cancelMoment is not null group by c2)")
	Collection<Customer> findConsumerLessOrdersCancelled();
	
	@Query("select c from Consumer c where c.userAccount.id = ?1")
	Customer findByUserAccountId(int userAccountId);
	}
