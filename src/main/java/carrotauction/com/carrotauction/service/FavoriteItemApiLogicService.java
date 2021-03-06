package carrotauction.com.carrotauction.service;

import carrotauction.com.carrotauction.model.entity.FavoriteItem;
import carrotauction.com.carrotauction.model.entity.Item;
import carrotauction.com.carrotauction.model.entity.ItemBider;
import carrotauction.com.carrotauction.model.entity.User;
import carrotauction.com.carrotauction.network.Header;
import carrotauction.com.carrotauction.network.request.FavoriteItemApiRequest;
import carrotauction.com.carrotauction.network.response.FavoriteItemApiResponse;
import carrotauction.com.carrotauction.repository.ItemRepository;
import carrotauction.com.carrotauction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
public class FavoriteItemApiLogicService extends BaseService<FavoriteItemApiRequest, FavoriteItemApiResponse, FavoriteItem> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    HttpSession session;

    @Override
    public Header<List<FavoriteItemApiResponse>> search(Pageable pageable) {
        return null;
    }

    @Override
    public Header<FavoriteItemApiResponse> create(Header<FavoriteItemApiRequest> request) {
        FavoriteItemApiRequest favoriteItemApiRequest = request.getData();

        FavoriteItem favoriteItem = FavoriteItem.builder()
                .user(userRepository.getOne(favoriteItemApiRequest.getUser()))
                .item(itemRepository.getOne(favoriteItemApiRequest.getItem()))
                .build();

        FavoriteItem newFavoriteItem = baseRepository.save(favoriteItem);

        return response(newFavoriteItem);
    }

    @Override
    public Header<FavoriteItemApiResponse> read(Long id) {
        Optional<FavoriteItem> favoriteItem = baseRepository.findById(id);

        return favoriteItem.map(selectFavoriteItem -> response(selectFavoriteItem))
                .orElseGet(() -> Header.ERROR("????????? ??????"));
    }

    @Override
    public Header<FavoriteItemApiResponse> update(Header<FavoriteItemApiRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        Optional<FavoriteItem> optional = baseRepository.findById(id);

        return optional.map(favoriteItem -> {
            baseRepository.delete(favoriteItem);
            return Header.OK();
            })
            .orElseGet(() -> Header.ERROR("????????? ??????"));
    }

    public Header<FavoriteItemApiResponse> response(FavoriteItem favoriteItem) {
        FavoriteItemApiResponse favoriteItemApiResponse = FavoriteItemApiResponse.builder()
                .id(favoriteItem.getId())
                .user(favoriteItem.getUser().getId())
                .item(favoriteItem.getItem().getId())
                .build();

        return Header.OK(favoriteItemApiResponse);
    }

    public Header<String> toggle(Long id) { // id = item id
        User user = userRepository.getOne(((User) session.getAttribute("user")).getId());
//        User user = userRepository.getOne(1L);

        Item item = itemRepository.getOne(id);

        for (FavoriteItem favoriteItem : user.getFavoriteItemList()) {
            if (favoriteItem.getItem().equals(item)) {
                delete(favoriteItem.getId());
                return Header.OK();
            }
        }

        FavoriteItem favoriteItem = FavoriteItem.builder()
                .user(user)
                .item(item)
                .build();

        FavoriteItem newFavoriteItem = baseRepository.save(favoriteItem);

        return Header.OK();
    }
}
