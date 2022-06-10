package ASSIGNMENT_JAVA5.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ASSIGNMENT_JAVA5.entities.OrderDetail;


@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
	@Query("SELECT o FROM OrderDetail o WHERE o.order.user.id=:pid")
	List<OrderDetail> FindListOrderDetailByAccountId(@Param("pid")Integer id);
	
	@Query("SELECT o FROM OrderDetail o WHERE o.order.id=:pid")
	List<OrderDetail> FindListOrderDetailByOrderId(@Param("pid")Integer id);
	
	@Query("SELECT o FROM OrderDetail o WHERE o.order.id=:oid and o.status=:status")
	List<OrderDetail> FindListOrderDetailByOrderId(@Param("oid")Integer id,@Param("status")Integer status);
	
	@Query("SELECT o FROM OrderDetail o WHERE o.status=:status")
	List<OrderDetail> FindListOrderDetailByStatus(@Param("status")Integer status);
	
	@Query("SELECT o FROM OrderDetail o WHERE o.status=:status and o.order.user.id=:pid")
	List<OrderDetail> FindListOrderDetailByStatusAndAccountId(@Param("status")Integer status,@Param("pid")Integer id);
	
//	@Query("SELECT o FROM OrderDetail o WHERE o.order.id=:oid and o.status=:status and p.user.id=:uid")
//	List<OrderDetail> FindListOrderDetailByOrderId(@Param("oid")Integer id,@Param("status")Integer status,@Param("uid")Integer uid);
}
