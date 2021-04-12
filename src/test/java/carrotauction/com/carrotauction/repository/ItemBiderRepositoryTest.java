package carrotauction.com.carrotauction.repository;

import carrotauction.com.carrotauction.model.entity.ItemBider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ItemBiderRepositoryTest {

    @Autowired
    private ItemBiderRepository itemBuyerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create() {
        ItemBider itemBider = ItemBider.builder()
                .price(20000L)
                .user(userRepository.getOne(1L))
                .item(itemRepository.getOne(1L))
                .build();

        itemBuyerRepository.save(itemBider);
    }

    @Test
    public void read() {
        System.out.println(itemBuyerRepository.findAll());
    }

}