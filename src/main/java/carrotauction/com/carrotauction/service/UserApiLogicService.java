package carrotauction.com.carrotauction.service;

import carrotauction.com.carrotauction.model.entity.Item;
import carrotauction.com.carrotauction.model.entity.ItemBider;
import carrotauction.com.carrotauction.model.entity.User;
import carrotauction.com.carrotauction.network.Header;
import carrotauction.com.carrotauction.network.Pagination;
import carrotauction.com.carrotauction.network.request.UserApiRequest;
import carrotauction.com.carrotauction.network.response.ItemApiResponse;
import carrotauction.com.carrotauction.network.response.ItemBiderApiResponse;
import carrotauction.com.carrotauction.network.response.UserApiResponse;
import carrotauction.com.carrotauction.network.response.UserItemBiderApiResponse;
import carrotauction.com.carrotauction.repository.ItemBiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse, User> {

    @Autowired
    private ItemBiderApiLogicService itemBiderApiLogicService;

    @Autowired
    private ItemApiLogicService itemApiLogicService;

    @Autowired
    private ItemBiderRepository itemBiderRepository;

    @Override
    public Header<List<UserApiResponse>> search(Pageable pageable) {
        return null;
    }

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        UserApiRequest userApiRequest = request.getData();

        User user = User.builder()
                .user_id(userApiRequest.getUser_id())
                .user_pw(userApiRequest.getUser_pw())
                .location(userApiRequest.getLocation())
                .nickname(userApiRequest.getNickname())
                .build();

        User newUser = baseRepository.save(user);

        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        Optional<User> user = baseRepository.findById(id);

        return user.map(selectUser -> response(selectUser))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }

    private Header<UserApiResponse> response(User user) {
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .user_id(user.getUser_id())
                .user_pw(user.getUser_pw())
                .location(user.getLocation())
                .nickname(user.getNickname())
                .build();

        return Header.OK(userApiResponse);
    }

    public Header<UserItemBiderApiResponse> itemBiderInfo(Long id) {
        User user = baseRepository.getOne(id);
        UserApiResponse userApiResponse = response(user).getData();

        List<ItemBider> itemBiderList = user.getItemBiderList();
        List<ItemBiderApiResponse> itemBiderApiResponseList = itemBiderList.stream()
                .map(itemBider -> {
                    ItemBiderApiResponse itemBiderApiResponse = itemBiderApiLogicService.response(itemBider).getData();
                    Item item = itemBider.getItem();
                    ItemApiResponse itemApiResponse = itemApiLogicService.response(item).getData();

                    itemBiderApiResponse.setItemApiResponse(itemApiResponse);
                    return itemBiderApiResponse;
                })
                .collect(Collectors.toList());

        userApiResponse.setItemBiderApiResponseList(itemBiderApiResponseList);

        UserItemBiderApiResponse userItemBiderApiResponse = UserItemBiderApiResponse.builder()
                .userApiResponse(userApiResponse)
                .build();

        return Header.OK(userItemBiderApiResponse);
    }

    public Header<List<ItemBiderApiResponse>> searchItemBider(Pageable pageable, Long id) {
        User user = baseRepository.getOne(id);

        Page<ItemBider> itemBiders = itemBiderRepository.findAll(pageable);
        List<ItemBiderApiResponse> itemBiderApiResponseList = itemBiders.stream()
                .filter(itemBider -> itemBider.getUser().equals(user))
                .map(itemBider -> {
                    ItemBiderApiResponse itemBiderApiResponse = itemBiderApiLogicService.response(itemBider).getData();
                    Item item = itemBider.getItem();
                    ItemApiResponse itemApiResponse = itemApiLogicService.response(item).getData();

                    itemBiderApiResponse.setItemApiResponse(itemApiResponse);
                    return itemBiderApiResponse;
                })
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(itemBiders.getTotalPages())
                .totalElements(itemBiders.getTotalElements())
                .currentPage(itemBiders.getNumber())
                .currentElements(itemBiders.getNumberOfElements())
                .build();


        return Header.OK(itemBiderApiResponseList, pagination);
    }
}
