package ASSIGNMENT_JAVA5.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ASSIGNMENT_JAVA5.entities.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
	@Query("SELECT o FROM Favorite o WHERE o.account.id=:aid and o.isLike=1")
	List<Favorite>findFavoriteByAccountId(@Param("aid")Integer id);
	
	@Query("SELECT o FROM Favorite o WHERE o.product.id=:pid")
	Favorite findFavoriteByProductId(@Param("pid")Integer id);
}
