package ASSIGNMENT_JAVA5.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ASSIGNMENT_JAVA5.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
	@Query("SELECT c FROM Comment c WHERE c.product.id=:pid")
	List<Comment>listCommentByProductID(@Param("pid")Integer id);
}
