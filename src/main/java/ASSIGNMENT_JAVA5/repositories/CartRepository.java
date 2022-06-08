package ASSIGNMENT_JAVA5.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ASSIGNMENT_JAVA5.entities.Card;
import ASSIGNMENT_JAVA5.entities.Product;

@Repository
public interface CartRepository extends JpaRepository<Card, Integer> {
	@Query("SELECT o FROM Card o WHERE o.product.id=:uid ")
	Card findByProductId(@Param("uid")Integer id);
	
	@Query("SELECT o FROM Card o WHERE o.account.id=:uid")
	List<Card> findAllCartByAccountId(@Param("uid")Integer id);
	
	@Query("SELECT o FROM Card o WHERE o.account.id=:uid and o.product.id=:pid")
	List<Card> findCardByProductIdAndAccountId(@Param("uid")Integer idAccount,@Param("pid")Integer productId);
}
