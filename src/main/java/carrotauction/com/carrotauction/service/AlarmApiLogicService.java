package carrotauction.com.carrotauction.service;

import carrotauction.com.carrotauction.model.entity.Alarm;
import carrotauction.com.carrotauction.model.entity.User;
import carrotauction.com.carrotauction.network.Header;
import carrotauction.com.carrotauction.network.Pagination;
import carrotauction.com.carrotauction.network.request.AlarmApiRequest;
import carrotauction.com.carrotauction.network.response.AlarmApiResponse;
import carrotauction.com.carrotauction.repository.AlarmRepository;
import carrotauction.com.carrotauction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlarmApiLogicService extends BaseService<AlarmApiRequest, AlarmApiResponse, Alarm> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AlarmRepository alarmRepository;

    @Override
    public Header<List<AlarmApiResponse>> search(Pageable pageable) {
        return null;
    }

    public Header<List<AlarmApiResponse>> searchMyAlarm(Pageable pageable, Long id) {
        User user = userRepository.getOne(id);

        Page<Alarm> alarms = alarmRepository.findAll(pageable);

        List<AlarmApiResponse> alarmApiResponses = alarms.stream()
                .filter(alarm -> alarm.getUser().equals(user))
                .map(alarm -> response(alarm).getData())
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(alarms.getTotalPages())
                .totalElements(alarms.getTotalElements())
                .currentPage(alarms.getNumber())
                .currentElements(alarms.getNumberOfElements())
                .build();

        return Header.OK(alarmApiResponses, pagination);
    }

    @Override
    public Header<AlarmApiResponse> create(Header<AlarmApiRequest> request) {
        AlarmApiRequest alarmApiRequest = request.getData();

        Alarm alarm = Alarm.builder()
                .title(alarmApiRequest.getTitle())
                .description(alarmApiRequest.getDescription())
                .status(alarmApiRequest.getStatus())
                .build();

        Alarm newAlarm = baseRepository.save(alarm);

        return response(newAlarm);
    }

    @Override
    public Header<AlarmApiResponse> read(Long id) {
        Optional<Alarm> alarm = baseRepository.findById(id);

        return alarm.map(selectAlarm -> response(selectAlarm))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<AlarmApiResponse> update(Header<AlarmApiRequest> request) {
        AlarmApiRequest alarmApiRequest = request.getData();

        Optional<Alarm> optional = baseRepository.findById(alarmApiRequest.getId());

        return optional.map(alarm -> {
            alarm.setTitle(alarm.getTitle())
                    .setDescription(alarm.getDescription())
                    .setStatus(alarmApiRequest.getStatus())
                    .setItem_id(alarm.getItem_id())
                    .setUser(alarm.getUser());

            return alarm;
            })
            .map(alarm -> baseRepository.save(alarm))
            .map(alarm -> response(alarm))
            .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<Alarm> optional = baseRepository.findById(id);

        return optional.map(alarm -> {
            baseRepository.delete(alarm);
            return Header.OK();
            })
            .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<AlarmApiResponse> response(Alarm alarm) {
        AlarmApiResponse alarmApiResponse = AlarmApiResponse.builder()
                .id(alarm.getId())
                .title(alarm.getTitle())
                .description(alarm.getDescription())
                .status(alarm.getStatus())
                .build();

        return Header.OK(alarmApiResponse);
    }



}
