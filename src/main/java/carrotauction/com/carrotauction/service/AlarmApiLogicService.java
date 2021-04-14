package carrotauction.com.carrotauction.service;

import carrotauction.com.carrotauction.model.entity.Alarm;
import carrotauction.com.carrotauction.network.Header;
import carrotauction.com.carrotauction.network.request.AlarmApiRequest;
import carrotauction.com.carrotauction.network.response.AlarmApiResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class AlarmApiLogicService extends BaseService<AlarmApiRequest, AlarmApiResponse, Alarm> {

    @Override
    public Header<List<AlarmApiResponse>> search(Pageable pageable) {
        return null;
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
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
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
