package carrotauction.com.carrotauction.repository;

import carrotauction.com.carrotauction.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String user_id);
}
