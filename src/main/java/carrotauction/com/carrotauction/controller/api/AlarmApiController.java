package carrotauction.com.carrotauction.controller.api;

import carrotauction.com.carrotauction.controller.CrudController;
import carrotauction.com.carrotauction.model.entity.Alarm;
import carrotauction.com.carrotauction.network.request.AlarmApiRequest;
import carrotauction.com.carrotauction.network.response.AlarmApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alarm")
public class AlarmApiController extends CrudController<AlarmApiRequest, AlarmApiResponse, Alarm> {
}
