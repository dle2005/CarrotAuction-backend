package carrotauction.com.carrotauction.service;

import carrotauction.com.carrotauction.model.entity.User;
import carrotauction.com.carrotauction.network.Header;
import carrotauction.com.carrotauction.network.request.UserApiRequest;
import carrotauction.com.carrotauction.network.response.UserApiResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse, User> {

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
}
