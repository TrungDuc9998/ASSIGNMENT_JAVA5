package ASSIGNMENT_JAVA5.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ASSIGNMENT_JAVA5.entities.Order;
import ASSIGNMENT_JAVA5.entities.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	@Query("SELECT o FROM Product o WHERE o.category.id=:pid")
	List<Product> findByIdPro(@Param("pid")Integer id);
	
	@Query("SELECT o FROM Product o WHERE o.id=:oid")
	Product findById1(@Param("oid")Integer id);
	
	@Query("SELECT o FROM Product o WHERE o.name LIKE:keyword")
	Product findProductByName(@Param("keyword")String nameProduct);
	
	@Query("SELECT o FROM Product o WHERE o.name LIKE:keyword and o.color LIKE:color and o.size LIKE:size")
	Product findProductByNameAndSizeAndColor(@Param("keyword")String nameProduct,@Param("color")String color,@Param("size")String size);
	
}
