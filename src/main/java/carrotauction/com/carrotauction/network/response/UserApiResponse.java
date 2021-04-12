package carrotauction.com.carrotauction.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserApiResponse {

    private Long id;

    private String user_id;

    private String user_pw;

    private String location;

    private String nickname;

    private List<ItemBiderApiResponse> itemBiderApiResponseList;
}
