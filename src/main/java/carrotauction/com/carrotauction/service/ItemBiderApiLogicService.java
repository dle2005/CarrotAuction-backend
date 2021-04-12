package carrotauction.com.carrotauction.service;

import carrotauction.com.carrotauction.model.entity.ItemBider;
import carrotauction.com.carrotauction.network.Header;
import carrotauction.com.carrotauction.network.request.ItemBiderApiRequest;
import carrotauction.com.carrotauction.network.response.ItemApiResponse;
import carrotauction.com.carrotauction.network.response.ItemBiderApiResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemBiderApiLogicService extends BaseService<ItemBiderApiRequest, ItemBiderApiResponse, ItemBider> {

    @Override
    public Header<ItemBiderApiResponse> create(Header<ItemBiderApiRequest> request) {
        ItemBiderApiRequest itemBiderApiRequest = request.getData();

        ItemBider itemBider = ItemBider.builder()
                .price(itemBiderApiRequest.getPrice())
//                .user(itemBiderApiRequest.getUserId())
//                .itemId(itemBiderApiRequest.getItemId())
                .build();

        ItemBider newItemBider = baseRepository.save(itemBider);

        return response(newItemBider);
    }

    @Override
    public Header<ItemBiderApiResponse> read(Long id) {
        Optional<ItemBider> itemBider = baseRepository.findById(id);

        return itemBider.map(selectItemBider -> response(selectItemBider))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<ItemBiderApiResponse> update(Header<ItemBiderApiRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }

    public Header<ItemBiderApiResponse> response(ItemBider itemBider) {
        ItemBiderApiResponse itemBiderApiResponse = ItemBiderApiResponse.builder()
                .id(itemBider.getId())
                .price(itemBider.getPrice())
                .userId(itemBider.getUser().getId())
                .itemId(itemBider.getItem().getId())
                .build();

        return Header.OK(itemBiderApiResponse);
    }
}
