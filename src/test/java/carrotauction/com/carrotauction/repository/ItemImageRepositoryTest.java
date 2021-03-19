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

    @Test
    public void create() {
        ItemImage itemImage = ItemImage.builder()
                .url("ItemImageTest01")
                .build();

        itemImageRepository.save(itemImage);
    }

    @Test
    public void read() {
        System.out.println(itemImageRepository.findAll());
    }
}