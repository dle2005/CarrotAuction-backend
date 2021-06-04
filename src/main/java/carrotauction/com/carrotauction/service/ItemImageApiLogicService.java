package carrotauction.com.carrotauction.service;

import carrotauction.com.carrotauction.model.entity.ItemImage;
import carrotauction.com.carrotauction.network.Header;
import carrotauction.com.carrotauction.network.request.ItemImageApiRequest;
import carrotauction.com.carrotauction.network.response.ItemImageApiResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemImageApiLogicService extends BaseService<ItemImageApiRequest, ItemImageApiResponse, ItemImage> {

    @Override
    public Header<List<ItemImageApiResponse>> search(Pageable pageable) {
        return null;
    }

    @Override
    public Header<ItemImageApiResponse> create(Header<ItemImageApiRequest> request) {
        ItemImageApiRequest itemImageApiRequest = request.getData();

        ItemImage itemImage = ItemImage.builder()
                .url(itemImageApiRequest.getUrl())
                .build();

        ItemImage newItemImage = baseRepository.save(itemImage);

        return response(newItemImage);
    }

    @Override
    public Header<ItemImageApiResponse> read(Long id) {
        Optional<ItemImage> itemImage = baseRepository.findById(id);

        return itemImage.map(selectItemImage -> response(selectItemImage))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<ItemImageApiResponse> update(Header<ItemImageApiRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        Optional<ItemImage> optional = baseRepository.findById(id);

        return optional.map(itemImage -> {
            baseRepository.delete(itemImage);
            return Header.OK();
            })
            .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    public Header<ItemImageApiResponse> response(ItemImage itemImage) {
        ItemImageApiResponse itemImageApiResponse = ItemImageApiResponse.builder()
                .id(itemImage.getId())
                .url(itemImage.getUrl())
                .build();

        return Header.OK(itemImageApiResponse);
    }
}
