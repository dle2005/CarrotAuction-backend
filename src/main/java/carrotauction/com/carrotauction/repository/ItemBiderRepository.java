package carrotauction.com.carrotauction.repository;

import carrotauction.com.carrotauction.model.entity.ItemBider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemBiderRepository extends JpaRepository<ItemBider, Long> {
}
