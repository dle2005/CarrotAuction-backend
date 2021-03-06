package carrotauction.com.carrotauction.repository;

import carrotauction.com.carrotauction.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByCategoryAndStatus(String category, String status);
}
