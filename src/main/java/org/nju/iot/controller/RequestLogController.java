package org.nju.iot.controller;

import org.nju.iot.VO.RequestLogVO;
import org.nju.iot.service.RequestLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/requestLog")
public class RequestLogController {

    @Autowired
    private RequestLogService logService;

    @RequestMapping(value = "/overview", method = RequestMethod.GET)
    public Map<Long, List<RequestLogVO>> getOverview() {
        return logService.getOverview();
    }

}
