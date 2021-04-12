package carrotauction.com.carrotauction.controller.api;

import carrotauction.com.carrotauction.controller.CrudController;
import carrotauction.com.carrotauction.model.entity.User;
import carrotauction.com.carrotauction.network.Header;
import carrotauction.com.carrotauction.network.request.UserApiRequest;
import carrotauction.com.carrotauction.network.response.UserApiResponse;
import carrotauction.com.carrotauction.network.response.UserItemBiderApiResponse;
import carrotauction.com.carrotauction.service.UserApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse, User> {

    @Autowired
    private UserApiLogicService userApiLogicService;

    @GetMapping("/{id}/itemBiderInfo")
    public Header<UserItemBiderApiResponse> itemBiderInfo(@PathVariable Long id) {
        return userApiLogicService.itemBiderInfo(id);
    }



}
