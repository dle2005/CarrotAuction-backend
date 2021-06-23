package carrotauction.com.carrotauction.sample;

import carrotauction.com.carrotauction.CarrotauctionApplicationTests;
import carrotauction.com.carrotauction.model.entity.Category;
import carrotauction.com.carrotauction.model.entity.Item;
import carrotauction.com.carrotauction.repository.CategoryRepository;
import carrotauction.com.carrotauction.repository.ItemRepository;
import carrotauction.com.carrotauction.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Slf4j
public class ItemSample extends CarrotauctionApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    enum CategoryType {
        ELECTRONICS, BOOK, CLOTH, FURNITURE, FITNESS, BAG, ETC
    }
    enum StateType {
        상, 중, 하
    }

    @Test
    public void sampleCreate() {
        for (int i = 1; i < 100; i++) {
            Category category = Category.builder()
                    .category(CategoryType.values()[i % 7].toString())
                    .buy_year("201" + (i % 10))
                    .buy_price((long) (i * 1000))
                    .status(StateType.values()[i % 3].toString())
                    .build();

            Category newCategory = categoryRepository.save(category);

            Item item = Item.builder()
                    .title("TestItem" + i)
                    .description("TestItem" + i)
                    .start_price((long) (i * 1000))
                    .duration(LocalDateTime.now().plusDays(i % 10).plusMinutes(3))
                    .categoryId(newCategory.getId())
                    .status("판매중")
                    .user(userRepository.getOne((long) (i % 9) + 1))
                    .build();

            itemRepository.save(item);
        }
    }
}
