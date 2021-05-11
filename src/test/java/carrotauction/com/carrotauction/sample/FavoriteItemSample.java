package carrotauction.com.carrotauction.sample;

import carrotauction.com.carrotauction.CarrotauctionApplicationTests;
import carrotauction.com.carrotauction.model.entity.FavoriteItem;
import carrotauction.com.carrotauction.repository.FavoriteItemRepository;
import carrotauction.com.carrotauction.repository.ItemRepository;
import carrotauction.com.carrotauction.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class FavoriteItemSample extends CarrotauctionApplicationTests {

    @Autowired
    private FavoriteItemRepository favoriteItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void sampleCreate() {
        for (int i = 1; i < 30; i++) {
            FavoriteItem favoriteItem = FavoriteItem.builder()
                    .user(userRepository.getOne((long)(i % 10 + 1)))
                    .item(itemRepository.getOne((long)(i)))
                    .build();

            favoriteItemRepository.save(favoriteItem);
        }
    }
}
