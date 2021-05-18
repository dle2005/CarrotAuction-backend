package carrotauction.com.carrotauction.network.response;

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
public class ItemDetailApiResponse {

    private String title;

    private String description;

    private String category;

    private Long start_price;

    private Long current_price;

    private LocalDateTime duration;

    private Long item_id;

    private Long user_id;

    private String nickname;

    private String location;

    private List<ItemImageApiResponse> itemImageApiResponseList;
}
