package carrotauction.com.carrotauction.repository;

import carrotauction.com.carrotauction.model.entity.Alarm;
import carrotauction.com.carrotauction.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long> {
//    Page<Alarm> findAllByUser(Pageable pageable);
}
