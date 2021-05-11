package carrotauction.com.carrotauction.sample;

import carrotauction.com.carrotauction.CarrotauctionApplicationTests;
import carrotauction.com.carrotauction.model.entity.Alarm;
import carrotauction.com.carrotauction.repository.AlarmRepository;
import carrotauction.com.carrotauction.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class AlarmSample extends CarrotauctionApplicationTests {

    @Autowired
    private AlarmRepository alarmRepository;

    @Autowired
    private UserRepository userRepository;

    enum AlarmType {
        읽음, 안읽음
    }

    @Test
    public void sampleCreate() {

        for (int i = 1; i < 100; i++) {
            Alarm alarm = Alarm.builder()
                    .title("AlarmTest" + i)
                    .description("AlarmTest" + i)
                    .status(AlarmType.안읽음.toString())
                    .user(userRepository.getOne((long) (i % 9 + 1)))
                    .item_id((long) i)
                    .build();

            alarmRepository.save(alarm);
        }
    }
}
