package carrotauction.com.carrotauction.repository;

import carrotauction.com.carrotauction.model.entity.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create() {
        Item item = Item.builder()
                .title("ItemTest01")
                .description("ItemTest01")
                .start_price(10000L)
                .duration(LocalDateTime.now().plusDays(1))
                .build();

        itemRepository.save(item);
    }

    @Test
    public void read() {
        System.out.println(itemRepository.findAll());
    }
}