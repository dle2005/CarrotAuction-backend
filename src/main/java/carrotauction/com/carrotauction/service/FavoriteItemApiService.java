package carrotauction.com.carrotauction.service;

import carrotauction.com.carrotauction.model.entity.FavoriteItem;
import carrotauction.com.carrotauction.network.Header;
import carrotauction.com.carrotauction.network.request.FavoriteItemApiRequest;
import carrotauction.com.carrotauction.network.response.FavoriteItemApiResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteItemApiService extends BaseService<FavoriteItemApiRequest, FavoriteItemApiResponse, FavoriteItem> {

    @Override
    public Header<List<FavoriteItemApiResponse>> search(Pageable pageable) {
        return null;
    }

    @Override
    public Header<FavoriteItemApiResponse> create(Header<FavoriteItemApiRequest> request) {
        FavoriteItemApiRequest favoriteItemApiRequest = request.getData();

        FavoriteItem favoriteItem = FavoriteItem.builder()
//                .user(favoriteItemApiRequest.getUser())
                .item(favoriteItemApiRequest.getItem())
                .build();

        FavoriteItem newFavoriteItem = baseRepository.save(favoriteItem);

        return response(newFavoriteItem);
    }

    @Override
    public Header<FavoriteItemApiResponse> read(Long id) {
        Optional<FavoriteItem> favoriteItem = baseRepository.findById(id);

        return favoriteItem.map(selectFavoriteItem -> response(selectFavoriteItem))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<FavoriteItemApiResponse> update(Header<FavoriteItemApiRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }

    private Header<FavoriteItemApiResponse> response(FavoriteItem favoriteItem) {
        FavoriteItemApiResponse favoriteItemApiResponse = FavoriteItemApiResponse.builder()
                .id(favoriteItem.getId())
//                .user(favoriteItem.getUser())
                .item(favoriteItem.getItem())
                .build();

        return Header.OK(favoriteItemApiResponse);
    }
}
