package carrotauction.com.carrotauction.repository;

import carrotauction.com.carrotauction.model.entity.ItemBuyer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ItemBuyerRepositoryTest {

    @Autowired
    private ItemBuyerRepository itemBuyerRepository;

    @Test
    public void create() {
        ItemBuyer itemBuyer = ItemBuyer.builder()
                .price(10000L)
                .build();

        itemBuyerRepository.save(itemBuyer);
    }

    @Test
    public void read() {
        System.out.println(itemBuyerRepository.findAll());
    }
}