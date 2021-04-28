package org.nju.iot.service;

import org.nju.iot.VO.RequestLogVO;
import org.nju.iot.dao.RequestLogDao;
import org.nju.iot.model.RequestLogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RequestLogService {

    @Autowired
    private RequestLogDao requestLogDao;

    public Map<Long, List<RequestLogVO>> getOverview() {
        Map<Long, List<RequestLogVO>> map = new HashMap<>();
        List<RequestLogEntity> logs = requestLogDao.findAll();
        for (RequestLogEntity requestLogEntity : logs) {
            Long key = requestLogEntity.getDeviceId();
            if (map.containsKey(key)) {
                RequestLogVO requestLogVO = new RequestLogVO();
                requestLogVO.setRequestTime(requestLogEntity.getRequestTime());
                requestLogVO.setStatus(requestLogEntity.getStatus());
                map.get(key).add(requestLogVO);
            } else {
                List<RequestLogVO> logVOS = new ArrayList<>();
                RequestLogVO requestLogVO = new RequestLogVO();
                requestLogVO.setRequestTime(requestLogEntity.getRequestTime());
                requestLogVO.setStatus(requestLogEntity.getStatus());
                logVOS.add(requestLogVO);
                map.put(key, logVOS);
            }
        }
        return map;
    }

}
