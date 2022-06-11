package ASSIGNMENT_JAVA5.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ASSIGNMENT_JAVA5.entities.Order;
import ASSIGNMENT_JAVA5.entities.OrderDetail;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	@Query("SELECT o FROM Order o WHERE o.user.id=:uid")
	List<Order>findAllByAccountId(@Param("uid")Integer id);
	
	@Query("SELECT o FROM Order o WHERE o.user.email=:aemail")
	List<Order>findAllByEmail(@Param("aemail")String id);
	
	@Query("SELECT o FROM Order o WHERE o.PhoneNumber=:phone")
	List<Order>findAllByPhone(@Param("phone")String phone);
	
	
	
	@Query("SELECT o FROM Order o WHERE o.user.id=:uid and o.status=:status")
	List<Order>findAllByAccountIdAndSatus(@Param("uid")Integer id,@Param("status")Integer status);
	
	
}
