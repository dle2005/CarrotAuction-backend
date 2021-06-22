package carrotauction.com.carrotauction.service;

import carrotauction.com.carrotauction.model.entity.FavoriteItem;
import carrotauction.com.carrotauction.model.entity.Item;
import carrotauction.com.carrotauction.model.entity.ItemBider;
import carrotauction.com.carrotauction.model.entity.User;
import carrotauction.com.carrotauction.network.Header;
import carrotauction.com.carrotauction.network.Pagination;
import carrotauction.com.carrotauction.network.request.UserApiRequest;
import carrotauction.com.carrotauction.network.response.ItemApiResponse;
import carrotauction.com.carrotauction.network.response.UserApiResponse;
import carrotauction.com.carrotauction.repository.FavoriteItemRepository;
import carrotauction.com.carrotauction.repository.ItemRepository;
import carrotauction.com.carrotauction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse, User> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemApiLogicService itemApiLogicService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private FavoriteItemRepository favoriteItemRepository;

    @Autowired
    private ItemBiderApiLogicService itemBiderApiLogicService;

    @Autowired
    HttpSession session;

    @Override
    public Header<List<UserApiResponse>> search(Pageable pageable) {
        return null;
    }

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        UserApiRequest userApiRequest = request.getData();

        User user = User.builder()
                .email(userApiRequest.getEmail())
                .password(userApiRequest.getPassword())
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
        User user = (User) session.getAttribute("user");
//        User user = baseRepository.getOne(1L);
        UserApiRequest userApiRequest = request.getData();

       user.setId(user.getId())
            .setPassword(userApiRequest.getPassword() == null ? user.getPassword() : userApiRequest.getPassword())
            .setLocation(userApiRequest.getLocation() == null ? user.getLocation() : userApiRequest.getLocation())
            .setNickname(userApiRequest.getNickname() == null ? user.getNickname() : userApiRequest.getNickname());

        return response(baseRepository.save(user));
    }

    @Override
    public Header delete(Long id) {
        Optional<User> optional = baseRepository.findById(id);

        return optional.map(user -> {
            baseRepository.delete(user);
            return Header.OK();
        })
        .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<UserApiResponse> response(User user) {
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getEmail())
                .location(user.getLocation())
                .nickname(user.getNickname())
                .build();

        return Header.OK(userApiResponse);
    }

    public Header<UserApiResponse> login(Header<UserApiRequest> request) {
        User user = userRepository.findByEmail(request.getData().getEmail());

        if (user == null) {
            return Header.ERROR("Not exist user");
        } else {
            if(user.getPassword().equals(request.getData().getPassword())) {
                session.setAttribute("user", user);
                return response(user);
            }
            else return Header.ERROR("Wrong password");
        }
    }

    public Header<String> logout() {
        User user = (User) session.getAttribute("user");
        if(user == null) return Header.ERROR("Not login state");
        else {
            session.removeAttribute("user");
            return Header.OK("log out successful");
        }
    }

    public Header<UserApiResponse> loginUser() {
        User user = (User) session.getAttribute("user"); // login 해야만 데이터 가져올 수 있음

        if(user == null)
            return Header.ERROR("Not exist user");
        else return response(user);
    }

    public Header<UserApiResponse> register(Header<UserApiRequest> request) {
        UserApiRequest userApiRequest = request.getData();

        User user = userRepository.findByEmail(userApiRequest.getEmail());

        if (user == null) {
            User newUser = User.builder()
                    .email(userApiRequest.getEmail())
                    .password(userApiRequest.getPassword())
                    .location(userApiRequest.getLocation())
                    .nickname(userApiRequest.getNickname())
                    .build();

            User saveUser = baseRepository.save(newUser);

            return response(saveUser);
        } else {
            return Header.ERROR("Already exist user id");
        }
    }

    public Header<List<ItemApiResponse>> myItem(Pageable pageable) {
//        User user = userRepository.getOne(((User)session.getAttribute("user")).getId());
        User user = baseRepository.getOne(2L);

        List<Item> itemList = user.getItemList();

        Page<Item> items = new PageImpl<>(itemList, pageable, itemList.size());

        List<ItemApiResponse> itemApiResponseList = items.stream()
                .map(item -> itemApiLogicService.response(item).getData())
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(items.getTotalPages())
                .totalElements(items.getTotalElements())
                .currentPage(items.getNumber())
                .currentElements(items.getNumberOfElements())
                .build();

        return Header.OK(itemApiResponseList, pagination);
    }

    public Header<List<ItemApiResponse>> myBid(Pageable pageable) {
        User user = userRepository.getOne(((User)session.getAttribute("user")).getId());

        List<ItemBider> itemBiderList = user.getItemBiderList();

        List<Item> itemList = itemBiderList.stream()
                .map(itemBider -> itemRepository.getOne(itemBider.getItem().getId()))
                .collect(Collectors.toList());

        Page<Item> items = new PageImpl<Item>(itemList, pageable, itemList.size());

        List<ItemApiResponse> itemApiResponseList = items.stream()
                .map(item -> itemApiLogicService.response(item).getData())
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(items.getTotalPages())
                .totalElements(items.getTotalElements())
                .currentPage(items.getNumber())
                .currentElements(items.getNumberOfElements())
                .build();

        return Header.OK(itemApiResponseList, pagination);
    }


    @Transactional
    public Header<List<ItemApiResponse>> myFavorite(Pageable pageable) {
        User user = baseRepository.getOne(((User)session.getAttribute("user")).getId());

        List<FavoriteItem> favoriteItemList = user.getFavoriteItemList();

        List<Item> itemList = favoriteItemList.stream()
                .map(favoriteItem -> itemRepository.getOne(favoriteItem.getItem().getId()))
                .collect(Collectors.toList());

        Page<Item> items = new PageImpl<>(itemList, pageable, itemList.size());

        List<ItemApiResponse> itemApiResponseList = items.stream()
                .map(item -> itemApiLogicService.response(item).getData())
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(items.getTotalPages())
                .totalElements(items.getTotalElements())
                .currentPage(items.getNumber())
                .currentElements(items.getNumberOfElements())
                .build();

        return Header.OK(itemApiResponseList, pagination);
    }


//    public Header<UserItemBiderApiResponse> itemBiderInfo(Long id) {
//        User user = baseRepository.getOne(id);
//        UserApiResponse userApiResponse = response(user).getData();
//
//        List<ItemBider> itemBiderList = user.getItemBiderList();
//        List<ItemBiderApiResponse> itemBiderApiResponseList = itemBiderList.stream()
//                .map(itemBider -> {
//                    ItemBiderApiResponse itemBiderApiResponse = itemBiderApiLogicService.response(itemBider).getData();
//                    Item item = itemBider.getItem();
//                    ItemApiResponse itemApiResponse = itemApiLogicService.response(item).getData();
//
//                    itemBiderApiResponse.setItemApiResponse(itemApiResponse);
//                    return itemBiderApiResponse;
//                })
//                .collect(Collectors.toList());
//
//        userApiResponse.setItemBiderApiResponseList(itemBiderApiResponseList);
//
//        UserItemBiderApiResponse userItemBiderApiResponse = UserItemBiderApiResponse.builder()
//                .userApiResponse(userApiResponse)
//                .build();
//
//        return Header.OK(userItemBiderApiResponse);
//    }
//
//    public Header<List<ItemBiderApiResponse>> searchItemBider(Pageable pageable, Long id) {
//        User user = baseRepository.getOne(id);
//
//        Page<ItemBider> itemBiders = itemBiderRepository.findAll(pageable);
//        List<ItemBiderApiResponse> itemBiderApiResponseList = itemBiders.stream()
//                .filter(itemBider -> itemBider.getUser().equals(user))
//                .map(itemBider -> {
//                    ItemBiderApiResponse itemBiderApiResponse = itemBiderApiLogicService.response(itemBider).getData();
//                    Item item = itemBider.getItem();
//                    ItemApiResponse itemApiResponse = itemApiLogicService.response(item).getData();
//
//                    itemBiderApiResponse.setItemApiResponse(itemApiResponse);
//                    return itemBiderApiResponse;
//                })
//                .collect(Collectors.toList());
//
//        Pagination pagination = Pagination.builder()
//                .totalPages(itemBiders.getTotalPages())
//                .totalElements(itemBiders.getTotalElements())
//                .currentPage(itemBiders.getNumber())
//                .currentElements(itemBiders.getNumberOfElements())
//                .build();
//
//
//        return Header.OK(itemBiderApiResponseList, pagination);
//    }
//
//    public Header<List<FavoriteItemApiResponse>> searchFavoriteItem(Pageable pageable, Long id) {
//        User user = baseRepository.getOne(id);
//
//        Page<FavoriteItem> favoriteItems = favoriteItemRepository.findAll(pageable);
//        List<FavoriteItemApiResponse> favoriteItemApiResponsesList = favoriteItems.stream()
//                .filter(favoriteItem -> favoriteItem.getUser().equals(user))
//                .map(favoriteItem -> {
//                    FavoriteItemApiResponse favoriteItemApiResponse = favoriteItemApiLogicService.response(favoriteItem).getData();
//                    Item item = favoriteItem.getItem();
//                    ItemApiResponse itemApiResponse = itemApiLogicService.response(item).getData();
//
//                    favoriteItemApiResponse.setItemApiResponse(itemApiResponse);
//                    return favoriteItemApiResponse;
//                })
//                .collect(Collectors.toList());
//
//        Pagination pagination = Pagination.builder()
//                .totalPages(favoriteItems.getTotalPages())
//                .totalElements(favoriteItems.getTotalElements())
//                .currentPage(favoriteItems.getNumber())
//                .currentElements(favoriteItems.getNumberOfElements())
//                .build();
//
//        return Header.OK(favoriteItemApiResponsesList, pagination);
//    }
//


}






