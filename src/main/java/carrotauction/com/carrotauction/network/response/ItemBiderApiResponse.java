package carrotauction.com.carrotauction.network.response;

import carrotauction.com.carrotauction.model.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemBiderApiResponse {

    private Long id;

    private Long price;

    private Long userId;

    private Long itemId;

    private ItemApiResponse itemApiResponse;
}
