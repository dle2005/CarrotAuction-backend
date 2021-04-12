package carrotauction.com.carrotauction.repository;

import carrotauction.com.carrotauction.model.entity.FavoriteItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FavoriteItemRepositoryTest {

    @Autowired
    private  FavoriteItemRepository favoriteItemRepository;

    @Test
    public void create() {
        FavoriteItem favoriteItem = FavoriteItem.builder()

                .build();
    }
}