package carrotauction.com.carrotauction.controller.api;

import carrotauction.com.carrotauction.controller.CrudController;
import carrotauction.com.carrotauction.model.entity.Category;
import carrotauction.com.carrotauction.network.Header;
import carrotauction.com.carrotauction.network.request.CategoryApiRequest;
import carrotauction.com.carrotauction.network.response.AvgPriceApiResponse;
import carrotauction.com.carrotauction.network.response.CategoryApiResponse;
import carrotauction.com.carrotauction.service.CategoryApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryApiController extends CrudController<CategoryApiRequest, CategoryApiResponse, Category> {

    @Autowired
    private CategoryApiLogicService categoryApiLogicService;

    @GetMapping(value = "/avgPrice/{category}/{buy_year}/{buy_price}/{status}")
    public Header<AvgPriceApiResponse> getAvgPrice(@PathVariable String category, @PathVariable String buy_year, @PathVariable Long buy_price, @PathVariable String status) {
        return categoryApiLogicService.getAvgPrice(category, buy_year, buy_price, status);
    }

}
