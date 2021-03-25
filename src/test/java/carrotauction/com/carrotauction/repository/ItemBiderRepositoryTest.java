package carrotauction.com.carrotauction.repository;

import carrotauction.com.carrotauction.model.entity.ItemBider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ItemBiderRepositoryTest {

    @Autowired
    private ItemBiderRepository itemBuyerRepository;

    @Test
    public void create() {
        ItemBider itemBider = ItemBider.builder()
                .price(10000L)
                .build();

        itemBuyerRepository.save(itemBider);
    }

    @Test
    public void read() {
        System.out.println(itemBuyerRepository.findAll());
    }
}