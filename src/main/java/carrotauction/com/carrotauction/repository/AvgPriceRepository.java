package carrotauction.com.carrotauction.repository;

import carrotauction.com.carrotauction.model.entity.AvgPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvgPriceRepository extends JpaRepository<AvgPrice, Long> {
}
