package carrotauction.com.carrotauction.repository;

import carrotauction.com.carrotauction.model.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void create() {
        Category category = Category.builder()
                .category("CategoryTest01")
                .buy_year("2021")
                .buy_price(40000L)
                .status("상")
                .build();

        categoryRepository.save(category);
    }

    @Test
    public void read() {
        System.out.println(categoryRepository.findAll());
    }
}