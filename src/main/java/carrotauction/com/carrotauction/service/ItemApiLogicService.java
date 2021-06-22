package carrotauction.com.carrotauction.service;

import carrotauction.com.carrotauction.model.entity.*;
import carrotauction.com.carrotauction.network.Header;
import carrotauction.com.carrotauction.network.Pagination;
import carrotauction.com.carrotauction.network.request.ItemApiRequest;
import carrotauction.com.carrotauction.network.response.ItemApiResponse;
import carrotauction.com.carrotauction.network.response.ItemDetailApiResponse;
import carrotauction.com.carrotauction.network.response.ItemImageApiResponse;
import carrotauction.com.carrotauction.repository.CategoryRepository;
import carrotauction.com.carrotauction.repository.ItemImageRepository;
import carrotauction.com.carrotauction.repository.ItemRepository;
import carrotauction.com.carrotauction.repository.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemApiLogicService extends BaseService<ItemApiRequest, ItemApiResponse, Item> {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemImageRepository itemImageRepository;

    @Autowired
    private ItemImageApiLogicService itemImageApiLogicService;

    @Autowired
    HttpSession session;

    private S3Service s3Service;

    @Override
    public Header<List<ItemApiResponse>> search(Pageable pageable) {
        Page<Item> items = baseRepository.findAll(pageable);

        List<ItemApiResponse> itemApiResponseList = items.stream()
                .map(item -> response(item).getData())
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(items.getTotalPages())
                .totalElements(items.getTotalElements())
                .currentPage(items.getNumber())
                .currentElements(items.getNumberOfElements())
                .build();

        return Header.OK(itemApiResponseList, pagination);
    }

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) throws IOException {
//        User user = userRepository.getOne(((User) session.getAttribute("user")).getId());
        User user = userRepository.getOne(1L);

        ItemApiRequest itemApiRequest = request.getData();

        Category category = Category.builder()
                .category(itemApiRequest.getCategory())
                .buy_year(itemApiRequest.getBuy_year())
                .buy_price(itemApiRequest.getBuy_price())
                .build();

        Category newCategory = categoryRepository.save(category);

        Item item = Item.builder()
                .title(itemApiRequest.getTitle())
                .description(itemApiRequest.getDescription())
                .start_price(itemApiRequest.getStart_price())
                .duration(LocalDateTime.now().plusDays(itemApiRequest.getDuration()))
                .categoryId(newCategory.getId())
                .status("판매중")
                .user(user)
                .build();

        Item newItem = baseRepository.save(item);

        System.out.println(itemApiRequest.getUrl().size());

        if (itemApiRequest.getUrl() != null) {
            System.out.println("test");
            List<String> url = itemApiRequest.getUrl();
            for (int i = 0; i < url.size(); i++) {
                ItemImage itemImage = ItemImage.builder()
                        .url(url.get(i))
                        .item(newItem)
                        .build();

                itemImageRepository.save(itemImage);
            }
        }

        return response(newItem);
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {
        Optional<Item> item = baseRepository.findById(id);

        List<ItemImage> itemImages = itemImageRepository.findByItem(item);

        return item.map(selectItem -> response(selectItem, itemImages))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        Optional<Item> optional = baseRepository.findById(id);

        return optional.map(item -> {
            baseRepository.delete(item);
            return Header.OK();
            })
            .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    public Header<ItemApiResponse> response(Item item) {
        User user = null;

        if((User) session.getAttribute("user") != null)
            user = userRepository.getOne(((User)session.getAttribute("user")).getId());

        ItemApiResponse itemApiResponse = ItemApiResponse.builder()
                .id(item.getId())
                .title(item.getTitle())
                .description(item.getDescription())
                .url(item.getItemImages().size() == 0 ? "" : item.getItemImages().get(0).getUrl())
                .start_price(item.getStart_price())
                .duration(item.getDuration())
                .categoryId(item.getCategoryId())
                .userId(item.getUser().getId())
                .nickname(item.getUser().getNickname())
                .location(item.getUser().getLocation())
                .favorite(user == null ? false : user.getFavoriteItemList().contains(item) ? true : false)
                .likes(item.getFavoriteItemList() == null ? 0 : (long) item.getFavoriteItemList().size())
                .build();

        return Header.OK(itemApiResponse);
    }

    public Header<ItemApiResponse> response(Item item, List<ItemImage> itemImages) {
        List<ItemImageApiResponse> itemImageApiResponseList = itemImages.stream()
                .map(itemImage -> itemImageApiLogicService.response(itemImage).getData())
                .collect(Collectors.toList());

        ItemApiResponse itemApiResponse = ItemApiResponse.builder()
                .id(item.getId())
                .title(item.getTitle())
                .description(item.getDescription())
                .start_price(item.getStart_price())
                .duration(item.getDuration())
                .categoryId(item.getCategoryId())
                .userId(item.getUser().getId())
                .build();

        return Header.OK(itemApiResponse);
    }

    public Header<List<ItemApiResponse>> deadlineItemList(Pageable pageable) {
        Page<Item> items = baseRepository.findAll(pageable);

        List<ItemApiResponse> itemApiResponseList = items.stream()
                .map(item -> response(item).getData())
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(items.getTotalPages())
                .totalElements(items.getTotalElements())
                .currentPage(items.getNumber())
                .currentElements(items.getNumberOfElements())
                .build();

        return Header.OK(itemApiResponseList, pagination);
    }

    public Header<List<ItemApiResponse>> newItemList(Pageable pageable) {
        Page<Item> items = baseRepository.findAll(pageable);

        List<ItemApiResponse> itemApiResponseList = items.stream()
                .map(item -> response(item).getData())
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(items.getTotalPages())
                .totalElements(items.getTotalElements())
                .currentPage(items.getNumber())
                .currentElements(items.getNumberOfElements())
                .build();

        return Header.OK(itemApiResponseList, pagination);
    }

    public Header<ItemDetailApiResponse> itemDetail(Long id) {
        Item item = baseRepository.getOne(id);

        ItemDetailApiResponse itemDetailApiResponse = ItemDetailApiResponse.builder()
                .title(item.getTitle())
                .description(item.getDescription())
                .category(categoryRepository.getOne(item.getCategoryId()).getCategory())
                .start_price(item.getStart_price())
                .current_price(item.getItemBiderList().get(0).getPrice())
                .duration(item.getDuration())
                .item_id(item.getId())
                .user_id(item.getUser().getId())
                .nickname(item.getUser().getNickname())
                .location(item.getUser().getLocation())
                .build();

        return Header.OK(itemDetailApiResponse);
    }

    public Header<List<ItemApiResponse>> searchItem(Pageable pageable, String title) {
        List<Item> itemList = baseRepository.findAll()
                .stream().filter(item -> item.getTitle().matches(".*"+title+".*"))
                .collect(Collectors.toList());

        Page<Item> items = new PageImpl<Item>(itemList, pageable, itemList.size());

        List<ItemApiResponse> itemApiResponseList = items.stream()
                .map(item -> response(item).getData())
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(items.getTotalPages())
                .totalElements(items.getTotalElements())
                .currentPage(items.getNumber())
                .currentElements(items.getNumberOfElements())
                .build();

        return Header.OK(itemApiResponseList, pagination);
    }
}
