package carrotauction.com.carrotauction.repository;

import carrotauction.com.carrotauction.model.entity.FavoriteItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FavoriteItemRepositoryTest {

    @Autowired
    private FavoriteItemRepository favoriteItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create() {
        FavoriteItem favoriteItem = FavoriteItem.builder()
                .user(userRepository.getOne(1L))
                .item(itemRepository.getOne(2L))
                .build();

        favoriteItemRepository.save(favoriteItem);
    }
}