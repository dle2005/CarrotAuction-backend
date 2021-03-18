package carrotauction.com.carrotauction.repository;

import carrotauction.com.carrotauction.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
        User user = User.builder()
                .uid("Test01")
                .upw("Test01")
                .location("서울특별시")
                .nickname("도네왕")
                .build();

        userRepository.save(user);
    }

    @Test
    public void read() {
        System.out.println(userRepository.findAll());
    }

    @Test
    public void update() {

    }
}