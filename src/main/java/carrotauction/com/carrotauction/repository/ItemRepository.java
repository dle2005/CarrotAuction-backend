package carrotauction.com.carrotauction.repository;

import carrotauction.com.carrotauction.model.entity.Item;
import carrotauction.com.carrotauction.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
//    Page<Item> findAllByUserId(Long id, Pageable pageable);

//    List<Item> findAllByTitleRegex(String regex);

    List<Item> findAllByDurationBetween(LocalDateTime from, LocalDateTime to);
}
