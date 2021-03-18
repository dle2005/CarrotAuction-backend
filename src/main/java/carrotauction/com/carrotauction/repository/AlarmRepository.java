package carrotauction.com.carrotauction.repository;

import carrotauction.com.carrotauction.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlarmRepository extends JpaRepository<User, Long> {
}
