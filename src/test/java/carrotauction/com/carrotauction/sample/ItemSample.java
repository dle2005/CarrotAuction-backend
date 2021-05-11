package carrotauction.com.carrotauction.sample;

import carrotauction.com.carrotauction.CarrotauctionApplicationTests;
import carrotauction.com.carrotauction.model.entity.Category;
import carrotauction.com.carrotauction.model.entity.Item;
import carrotauction.com.carrotauction.repository.CategoryRepository;
import carrotauction.com.carrotauction.repository.ItemRepository;
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

    enum CategoryType {
        ELECTRONICS, BOOK, CLOTH, FURNITURE, FITNESS, BAG, ETC
    }
    enum StateType {
        상, 중, 하
    }

    @Test
    public void sampleCreate() {
        for(int i = 1; i < 100; i++) {
            Category category = Category.builder()
                    .category(CategoryType.values()[i % 7].toString())
                    .buy_year("201" + (i % 10))
                    .buy_price((long) (i * 1000 + i * 100 + i * 10 + i))
                    .status(StateType.values()[i % 3].toString())
                    .build();

            Category newCategory = categoryRepository.save(category);

            Item item = Item.builder()
                    .title("TestItem" + i)
                    .description("TestItem" + i)
                    .start_price((long) (i * 1000 + i * 100 + i * 10 + i))
                    .duration(LocalDateTime.now().plusDays(i % 10))
                    .categoryId(newCategory.getId())
                    .userId((long) (i % 10))
                    .build();

            itemRepository.save(item);
        }
    }
}
