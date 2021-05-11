package carrotauction.com.carrotauction.controller.api;

import carrotauction.com.carrotauction.controller.CrudController;
import carrotauction.com.carrotauction.model.entity.Alarm;
import carrotauction.com.carrotauction.network.Header;
import carrotauction.com.carrotauction.network.request.AlarmApiRequest;
import carrotauction.com.carrotauction.network.response.AlarmApiResponse;
import carrotauction.com.carrotauction.service.AlarmApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/alarm")
public class AlarmApiController extends CrudController<AlarmApiRequest, AlarmApiResponse, Alarm> {

    @Autowired
    private AlarmApiLogicService alarmApiLogicService;

    @GetMapping("/{id}/myAlarm")
    public Header<List<AlarmApiResponse>> searchMyAlarm(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 10) Pageable pageable, @PathVariable Long id) {
        return alarmApiLogicService.searchMyAlarm(pageable, id);
    }
}
