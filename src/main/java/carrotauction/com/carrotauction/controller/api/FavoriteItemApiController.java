package carrotauction.com.carrotauction.controller.api;

import carrotauction.com.carrotauction.controller.CrudController;
import carrotauction.com.carrotauction.model.entity.FavoriteItem;
import carrotauction.com.carrotauction.network.Header;
import carrotauction.com.carrotauction.network.request.FavoriteItemApiRequest;
import carrotauction.com.carrotauction.network.response.FavoriteItemApiResponse;
import carrotauction.com.carrotauction.service.FavoriteItemApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/favoriteItem")
public class FavoriteItemApiController extends CrudController<FavoriteItemApiRequest, FavoriteItemApiResponse, FavoriteItem> {

    @Autowired
    private FavoriteItemApiLogicService favoriteItemApiLogicService;

    @GetMapping("/toggle/{id}")
    public Header<String> toggle(@PathVariable Long id) {
        return favoriteItemApiLogicService.toggle(id);
    }
}
