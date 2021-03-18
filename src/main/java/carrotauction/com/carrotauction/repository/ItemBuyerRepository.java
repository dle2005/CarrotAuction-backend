package carrotauction.com.carrotauction.repository;

import carrotauction.com.carrotauction.model.entity.ItemBuyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemBuyerRepository extends JpaRepository<ItemBuyer, Long> {
}
