package carrotauction.com.carrotauction.repository;

import carrotauction.com.carrotauction.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    default User findByUser_id(String user_id) {
        return null;
    }
}
