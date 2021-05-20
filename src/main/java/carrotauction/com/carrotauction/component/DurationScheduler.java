package carrotauction.com.carrotauction.component;

import carrotauction.com.carrotauction.model.entity.Alarm;
import carrotauction.com.carrotauction.model.entity.Item;
import carrotauction.com.carrotauction.repository.AlarmRepository;
import carrotauction.com.carrotauction.repository.ItemRepository;
import carrotauction.com.carrotauction.repository.UserRepository;
import carrotauction.com.carrotauction.service.ItemApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DurationScheduler {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private AlarmRepository alarmRepository;

    @Autowired
    private UserRepository userRepository;

    @Scheduled(fixedDelay = 60000)
    public void auctionEnd() {
        List<Item> itemList = itemRepository.findAllByDurationBetween(LocalDateTime.now(), LocalDateTime.now().plusMinutes(1));

        System.out.println(itemList.size());

        for (Item item : itemList) {
            item.setTitle(item.getTitle())
                    .setDescription(item.getDescription())
                    .setStart_price(item.getStart_price())
                    .setDuration(item.getDuration())
                    .setCategoryId(item.getCategoryId())
                    .setUser(item.getUser())
                    .setItemBiderList(item.getItemBiderList())
                    .setItemImages(item.getItemImages())
                    .setStatus("종료");

            Alarm seller = Alarm.builder()
                    .title("상품 종료")
                    .description("상품 종료")
                    .status("안읽음")
                    .user(userRepository.getOne(item.getUser().getId()))
                    .item_id(item.getId())
                    .build();
            alarmRepository.save(seller);


//            if(item.getItemBiderList().size() > 0) {
//                Alarm buyer = Alarm.builder()
//                        .title("상품 종료")
//                        .description("상품 종료")
//                        .status("안읽음")
//                        .user(userRepository.getOne(item.getItemBiderList().get(0).getId()))
//                        .item_id(item.getId())
//                        .build();
//
//                alarmRepository.save(buyer);
//            }


            itemRepository.save(item);
        }


    }
}
