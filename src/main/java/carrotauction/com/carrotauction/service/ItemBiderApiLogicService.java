package carrotauction.com.carrotauction.service;

import carrotauction.com.carrotauction.model.entity.Alarm;
import carrotauction.com.carrotauction.model.entity.ItemBider;
import carrotauction.com.carrotauction.model.entity.User;
import carrotauction.com.carrotauction.network.Header;
import carrotauction.com.carrotauction.network.request.ItemBiderApiRequest;
import carrotauction.com.carrotauction.network.response.ItemBiderApiResponse;
import carrotauction.com.carrotauction.repository.AlarmRepository;
import carrotauction.com.carrotauction.repository.ItemRepository;
import carrotauction.com.carrotauction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
public class ItemBiderApiLogicService extends BaseService<ItemBiderApiRequest, ItemBiderApiResponse, ItemBider> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private AlarmRepository alarmRepository;

    @Override
    public Header<List<ItemBiderApiResponse>> search(Pageable pageable) {
        return null;
    }

    @Autowired
    HttpSession session;

    @Override
    public Header<ItemBiderApiResponse> create(Header<ItemBiderApiRequest> request) {
//        User user = userRepository.getOne(((User) session.getAttribute("user")).getId());
        User user = userRepository.getOne(15L);
        ItemBiderApiRequest itemBiderApiRequest = request.getData();

        ItemBider itemBider = ItemBider.builder()
                .price(itemBiderApiRequest.getPrice())
                .user(user)
                .item(itemRepository.getOne(itemBiderApiRequest.getItemId()))
                .build();

        ItemBider newItemBider = baseRepository.save(itemBider);

        Alarm bider = Alarm.builder()
                .title("입찰 완료")
                .description(itemBiderApiRequest.getItemId() + " 번 상품 입찰이 완료되었습니다.")
                .status("안읽음")
                .user(user)
                .item_id(itemBiderApiRequest.getItemId())
                .build();
        alarmRepository.save(bider);

        Alarm seller = Alarm.builder()
                .title("입찰 완료")
                .description(itemBiderApiRequest.getItemId() + " 번 상품 입찰이 완료되었습니다.")
                .status("안읽음")
                .user(userRepository.getOne(itemBiderApiRequest.getSeller()))
                .item_id(itemBiderApiRequest.getItemId())
                .build();
        alarmRepository.save(seller);

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
        Optional<ItemBider> optional = baseRepository.findById(id);

        return optional.map(itemBider -> {
            baseRepository.delete(itemBider);
            return Header.OK();
            })
            .orElseGet(() -> Header.ERROR("데이터 없음"));
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
