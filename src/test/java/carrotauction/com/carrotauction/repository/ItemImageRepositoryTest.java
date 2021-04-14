package carrotauction.com.carrotauction.repository;

import carrotauction.com.carrotauction.model.entity.ItemImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemImageRepositoryTest {

    @Autowired
    private ItemImageRepository itemImageRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create() {
        ItemImage itemImage = ItemImage.builder()
                .url("ItemImageTest01")
                .item(itemRepository.getOne(1L))
                .build();

        itemImageRepository.save(itemImage);
    }

    @Test
    public void read() {
        System.out.println(itemImageRepository.findAll());
    }
}