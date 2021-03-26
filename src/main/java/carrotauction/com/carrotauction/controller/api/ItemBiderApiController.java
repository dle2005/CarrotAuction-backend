package carrotauction.com.carrotauction.controller.api;

import carrotauction.com.carrotauction.controller.CrudController;
import carrotauction.com.carrotauction.model.entity.ItemBider;
import carrotauction.com.carrotauction.network.request.ItemBiderApiRequest;
import carrotauction.com.carrotauction.network.response.ItemBiderApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/itemBider")
public class ItemBiderApiController extends CrudController<ItemBiderApiRequest, ItemBiderApiResponse, ItemBider> {
}
