package carrotauction.com.carrotauction.repository;

import carrotauction.com.carrotauction.model.entity.Alarm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AlarmRepositoryTest {

    @Autowired
    private AlarmRepository alarmRepository;

    @Test
    public void create() {
        Alarm alarm = Alarm.builder()
                .title("AlarmTest01")
                .description("AlarmTest01")
                .status("R")
                .build();

        alarmRepository.save(alarm);
    }

    @Test
    public void read() {
        System.out.println(alarmRepository.findAll());
    }
}