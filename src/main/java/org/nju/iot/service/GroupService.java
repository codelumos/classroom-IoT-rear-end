package org.nju.iot.service;

import org.nju.iot.VO.DeviceVO;
import org.nju.iot.VO.GroupVO;
import org.nju.iot.clientMock.MqttService;
import org.nju.iot.dao.DeviceDao;
import org.nju.iot.dao.GroupDao;
import org.nju.iot.model.DeviceEntity;
import org.nju.iot.model.GroupEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {

    @Autowired
    private GroupDao groupDao;
    @Autowired
    private DeviceDao deviceDao;

    //设备分组信息
    public List<GroupVO> getAllGroups() {
        List<GroupVO> groupVOS = new ArrayList<>();
        List<GroupEntity> groups = groupDao.findAll();
        groups.forEach(g -> {
            GroupVO groupVO = new GroupVO();
            BeanUtils.copyProperties(g, groupVO);
            List<DeviceVO> devices = deviceDao.findByGroupId(g.getId()).stream().map(d -> {
                DeviceVO deviceVO = new DeviceVO();
                BeanUtils.copyProperties(d, deviceVO);
                deviceVO.setOnlineState(MqttService.hasClient(String.valueOf(d.getId())));
                return deviceVO;
            }).collect(Collectors.toList());
            groupVO.setDeviceVOS(devices);
            groupVOS.add(groupVO);
        });
        return groupVOS;
    }

    //获取单个设备详情
    public GroupVO getDetail(long groupId) {
        GroupEntity group = groupDao.getOne(groupId);
        GroupVO groupVO = new GroupVO();
        BeanUtils.copyProperties(group, groupVO);
        List<DeviceVO> devices = deviceDao.findByGroupId(group.getId()).stream().map(d -> {
            DeviceVO deviceVO = new DeviceVO();
            BeanUtils.copyProperties(d, deviceVO);
            deviceVO.setOnlineState(MqttService.hasClient(String.valueOf(d.getId())));
            return deviceVO;
        }).collect(Collectors.toList());
        groupVO.setDeviceVOS(devices);
        return groupVO;
    }

    //根据分组获取设备
    public List<DeviceVO> getDeviceByGroup(long groupId) {
        List<DeviceVO> deviceVOS = new ArrayList<>();
        List<DeviceEntity> deviceEntities = deviceDao.findByGroupId(groupId);
        deviceEntities.forEach(d -> {
            DeviceVO deviceVO = new DeviceVO();
            BeanUtils.copyProperties(d, deviceVO);
            deviceVOS.add(deviceVO);
        });
        return deviceVOS;
    }

    //添加分组
    public boolean addGroup(String teamName) {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setTeamName(teamName);
        groupEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        groupDao.save(groupEntity);
        return true;
    }

    //删除分组
    public boolean deleteGroup(List<Long> groupIds) {
        groupIds.forEach(g -> {
            List<DeviceEntity> deviceEntities = deviceDao.findByGroupId(g);
            deviceEntities.forEach(d ->{
                d.setGroupId(0);
                deviceDao.save(d);
            });
            groupDao.deleteById(g);
        });

        return true;
    }

    //获取没有分组的设备
    public List<DeviceVO> getDeviceWithoutGroup() {
        List<DeviceVO> deviceVOS = new ArrayList<>();
        List<DeviceEntity> deviceEntities = deviceDao.findAll().stream().filter(d -> d.getGroupId() == 0).collect(Collectors.toList());
        deviceEntities.forEach(d -> {
            DeviceVO deviceVO = new DeviceVO();
            BeanUtils.copyProperties(d, deviceVO);
            deviceVO.setOnlineState(MqttService.hasClient(String.valueOf(d.getId())));
            deviceVOS.add(deviceVO);
        });
        return deviceVOS;
    }

    //为设备添加分组
    public boolean addDeviceToGroup(List<Long> deviceIds, long groupId) {
        deviceIds.forEach(d -> {
            DeviceEntity deviceEntity = deviceDao.getOne(d);
            deviceEntity.setGroupId(groupId);
            deviceDao.save(deviceEntity);
        });
        return true;
    }

    //从分组删除设备
    public boolean deleteFromGroup(List<Long> deviceIds) {
        deviceIds.forEach(d -> {
            DeviceEntity deviceEntity = deviceDao.getOne(d);
            deviceEntity.setGroupId(0);
            deviceDao.save(deviceEntity);
        });
        return true;
    }
}
