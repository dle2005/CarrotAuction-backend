package carrotauction.com.carrotauction.sample;

import carrotauction.com.carrotauction.CarrotauctionApplication;
import carrotauction.com.carrotauction.CarrotauctionApplicationTests;
import carrotauction.com.carrotauction.model.entity.User;
import carrotauction.com.carrotauction.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class UserSample extends CarrotauctionApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void sampleCreate(){
        for(int i = 1; i < 10; i++) {
            User user = User.builder()
                    .user_id("TestUser" + i)
                    .user_pw("TestUser" + i)
                    .location("서울특별시")
                    .nickname("도네왕" + i)
                    .build();

            userRepository.save(user);
        }
    }
}
