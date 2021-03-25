package carrotauction.com.carrotauction.controller.api;

import carrotauction.com.carrotauction.controller.CrudController;
import carrotauction.com.carrotauction.model.entity.FavoriteItem;
import carrotauction.com.carrotauction.network.request.FavoriteItemApiRequest;
import carrotauction.com.carrotauction.network.response.FavoriteItemApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/favoriteItem")
public class FavoriteItemApiController extends CrudController<FavoriteItemApiRequest, FavoriteItemApiResponse, FavoriteItem> {
}
