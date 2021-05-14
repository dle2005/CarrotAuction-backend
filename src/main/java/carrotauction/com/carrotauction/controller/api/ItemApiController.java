package carrotauction.com.carrotauction.controller.api;

import carrotauction.com.carrotauction.controller.CrudController;
import carrotauction.com.carrotauction.model.entity.Item;
import carrotauction.com.carrotauction.network.Header;
import carrotauction.com.carrotauction.network.request.ItemApiRequest;
import carrotauction.com.carrotauction.network.response.ItemApiResponse;
import carrotauction.com.carrotauction.service.ItemApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse, Item> {

    @Autowired
    private ItemApiLogicService itemApiLogicService;

    @GetMapping("/deadlineItemList")
    public Header<List<ItemApiResponse>> deadlineItemList(@PageableDefault(sort = "duration", direction = Sort.Direction.ASC, size = 5) Pageable pageable) {
        return itemApiLogicService.deadlineItemList(pageable);
    }

    @GetMapping("/newItemList")
    public Header<List<ItemApiResponse>> newItemList(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 5) Pageable pageable) {
        return itemApiLogicService.newItemList(pageable);
    }
}
