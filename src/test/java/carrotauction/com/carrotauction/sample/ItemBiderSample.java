package carrotauction.com.carrotauction.sample;

import carrotauction.com.carrotauction.model.entity.ItemBider;
import carrotauction.com.carrotauction.repository.ItemBiderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemBiderSample {

    @Autowired
    private ItemBiderRepository itemBiderRepository;

    @Test
    public void sampleCreate() {
        for (int i = 1; i < 20; i++) {
            ItemBider itemBider = ItemBider.builder()
                    .price((long) (i * 77))
                    .build();
        }
    }
}
