package carrotauction.com.carrotauction.service;

import carrotauction.com.carrotauction.model.entity.Item;
import carrotauction.com.carrotauction.network.Header;
import carrotauction.com.carrotauction.network.request.ItemApiRequest;
import carrotauction.com.carrotauction.network.response.ItemApiResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemApiLogicService extends BaseService<ItemApiRequest, ItemApiResponse, Item> {

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {
        ItemApiRequest itemApiRequest = request.getData();

        Item item = Item.builder()
                .title(itemApiRequest.getTitle())
                .description(itemApiRequest.getDescription())
                .start_price(itemApiRequest.getStart_price())
                .duration(itemApiRequest.getDuration())
                .categoryId(itemApiRequest.getCategoryId())
                .itemImageId(itemApiRequest.getItemImageId())
                .userId(itemApiRequest.getUserId())
                .build();

        Item newItem = baseRepository.save(item);

        return response(newItem);
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {
        Optional<Item> item = baseRepository.findById(id);

        return item.map(selectItem -> response(selectItem))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }

    private Header<ItemApiResponse> response(Item item) {
        ItemApiResponse itemApiResponse = ItemApiResponse.builder()
                .id(item.getId())
                .title(item.getTitle())
                .description(item.getDescription())
                .start_price(item.getStart_price())
                .duration(item.getDuration())
                .categoryId(item.getCategoryId())
                .itemImageId(item.getItemImageId())
                .userId(item.getUserId())
                .build();

        return Header.OK(itemApiResponse);
    }
}
