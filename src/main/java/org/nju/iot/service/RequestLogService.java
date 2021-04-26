package org.nju.iot.service;

import org.nju.iot.VO.RequestLogVO;
import org.nju.iot.dao.DeviceDao;
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

    @Autowired
    private DeviceDao deviceDao;

    public Map<String, List<RequestLogVO>> getOverview(){
        Map<String, List<RequestLogVO>> map = new HashMap<>();
        List<RequestLogEntity> logs = requestLogDao.findAll();
        for (RequestLogEntity requestLogEntity : logs) {
            String key = deviceDao.getOne(requestLogEntity.getDeviceId()).getDeviceName();
            if (map.containsKey(key)) {
                RequestLogVO requestLogVO = new RequestLogVO();
                requestLogVO.setRequestTime(requestLogEntity.getRequestTime());
                requestLogVO.setStatus(requestLogEntity.getStatus());
                map.get(key).add(requestLogVO);
            }else {
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
