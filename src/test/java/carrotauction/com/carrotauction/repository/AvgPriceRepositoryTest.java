package carrotauction.com.carrotauction.repository;

import carrotauction.com.carrotauction.model.entity.AvgPrice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AvgPriceRepositoryTest {

    @Autowired
    private AvgPriceRepository avgPriceRepository;

    @Test
    public void create() {
        AvgPrice avgPrice = AvgPrice.builder()
                .price(10000L)
                .build();

        avgPriceRepository.save(avgPrice);
    }

    @Test
    public void read() {
        System.out.println(avgPriceRepository.findAll());
    }
}