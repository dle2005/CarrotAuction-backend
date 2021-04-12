package carrotauction.com.carrotauction.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    private String buy_year;

    private Long buy_price;

    private String status;

    private Long itemImageId;

    private Long userId;
}
