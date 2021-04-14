package carrotauction.com.carrotauction.network.request;

import carrotauction.com.carrotauction.model.entity.ItemImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemApiRequest {

    private Long id;

    private String title;

    private String description;

    private Long start_price;

    private LocalDateTime duration;

    private String category;

    private List<ItemImage> itemImages;

    private String buy_year;

    private Long buy_price;

    private String status;

    private Long userId;
}
