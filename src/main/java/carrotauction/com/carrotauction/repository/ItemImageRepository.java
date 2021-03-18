package carrotauction.com.carrotauction.repository;

import carrotauction.com.carrotauction.model.entity.ItemImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemImageRepository extends JpaRepository<ItemImage, Long> {
}
