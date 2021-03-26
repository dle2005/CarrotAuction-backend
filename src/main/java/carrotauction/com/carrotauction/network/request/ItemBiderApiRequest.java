package carrotauction.com.carrotauction.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemBiderApiRequest {

    private Long id;

    private Long price;

    private Long userId;

    private Long itemId;
}
