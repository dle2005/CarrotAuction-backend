package carrotauction.com.carrotauction.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemApiResponse {

    private Long id;

    private String title;

    private String description;

    private Long start_price;

    private LocalDateTime duration;

    private Long categoryId;

    private Long itemImageId;

    private Long userId;
}
