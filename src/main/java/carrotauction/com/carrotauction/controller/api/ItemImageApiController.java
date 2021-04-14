package carrotauction.com.carrotauction.controller.api;

import carrotauction.com.carrotauction.controller.CrudController;
import carrotauction.com.carrotauction.model.entity.ItemImage;
import carrotauction.com.carrotauction.network.request.ItemImageApiRequest;
import carrotauction.com.carrotauction.network.response.ItemImageApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/itemImage")
public class ItemImageApiController extends CrudController<ItemImageApiRequest, ItemImageApiResponse, ItemImage> {
}
