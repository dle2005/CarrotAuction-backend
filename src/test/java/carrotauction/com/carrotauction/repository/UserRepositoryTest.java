package carrotauction.com.carrotauction.repository;

import carrotauction.com.carrotauction.model.entity.ItemBider;
import carrotauction.com.carrotauction.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemBiderRepository itemBiderRepository;

    @Test
    public void create() {
        User user = User.builder()
                .email("Test01")
                .password("Test01")
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
    public void readBidItem() {
        User user = userRepository.findById(1L).get();
        List<ItemBider> itemBiderList = itemBiderRepository.findByUser(user);

        for(ItemBider itemBider : itemBiderList)
            System.out.println(itemBider);
    }

    @Test
    public void update() {

    }
}