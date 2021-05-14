package carrotauction.com.carrotauction.controller.api;

import carrotauction.com.carrotauction.controller.CrudController;
import carrotauction.com.carrotauction.model.entity.User;
import carrotauction.com.carrotauction.network.Header;
import carrotauction.com.carrotauction.network.request.UserApiRequest;
import carrotauction.com.carrotauction.network.response.*;
import carrotauction.com.carrotauction.service.UserApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse, User> {

    @Autowired
    private UserApiLogicService userApiLogicService;

    @PostMapping("/register")
    public Header<UserApiResponse> register(@RequestBody Header<UserApiRequest> request) {
        return userApiLogicService.register(request);
    }

    @PostMapping("/login")
    public Header<UserApiResponse> login(@RequestBody Header<UserApiRequest> request) {
        return userApiLogicService.login(request);
    }

    @GetMapping("/myItem")
    public Header<List<ItemApiResponse>> searchMyItem(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 10)Pageable pageable) {
        return userApiLogicService.myItem(pageable);
    }

//    @GetMapping("/{id}/itemBiderInfo")
//    public Header<UserItemBiderApiResponse> itemBiderInfo(@PathVariable Long id) {
//        return userApiLogicService.itemBiderInfo(id);
//    }
//
//    @GetMapping("/{id}/itemBider")
//    public Header<List<ItemBiderApiResponse>> searchItemBider(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 10)Pageable pageable, @PathVariable Long id) {
//        return userApiLogicService.searchItemBider(pageable, id);
//    }
//
//    @GetMapping("/{id}/favoriteItem")
//    public Header<List<FavoriteItemApiResponse>> searchFavoriteItem(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 10)Pageable pageable, @PathVariable Long id) {
//        return userApiLogicService.searchFavoriteItem(pageable, id);
//    }
//
//
}
