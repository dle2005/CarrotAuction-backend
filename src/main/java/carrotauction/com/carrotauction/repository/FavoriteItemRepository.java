package carrotauction.com.carrotauction.repository;

import carrotauction.com.carrotauction.model.entity.FavoriteItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteItemRepository extends JpaRepository<FavoriteItem, Long> {
}
