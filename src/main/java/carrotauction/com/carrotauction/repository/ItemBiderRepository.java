package carrotauction.com.carrotauction.repository;

import carrotauction.com.carrotauction.model.entity.ItemBider;
import carrotauction.com.carrotauction.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemBiderRepository extends JpaRepository<ItemBider, Long> {

    List<ItemBider> findByUser(User user);

}
