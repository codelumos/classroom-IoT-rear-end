package org.nju.iot.service;

import org.nju.iot.VO.DeviceVO;
import org.nju.iot.VO.GroupVO;
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
		groups.forEach(g ->{
			GroupVO groupVO = new GroupVO();
			BeanUtils.copyProperties(g, groupVO);
			List<DeviceVO> devices = deviceDao.findByGroupId(g.getId()).stream().map(d -> {
				DeviceVO deviceVO = new DeviceVO();
				BeanUtils.copyProperties(d,deviceVO);
				return deviceVO;
			}).collect(Collectors.toList());
			groupVO.setDeviceVOS(devices);
		});
		return groupVOS;
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
	public boolean addGroup(String groupName) {
		GroupEntity groupEntity = new GroupEntity();
		groupEntity.setGroupName(groupName);
		groupEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
		groupDao.save(groupEntity);
		return true;
	}

	//删除分组
	public boolean deleteGroup(long groupId) {
		groupDao.deleteById(groupId);
		return true;
	}

	//获取没有分组的设备
	public List<DeviceVO> getDeviceWithoutGroup() {
		List<DeviceVO> deviceVOS = new ArrayList<>();
		List<DeviceEntity> deviceEntities = deviceDao.findAll().stream().filter(d -> d.getGroupId() == 0).collect(Collectors.toList());
		deviceEntities.forEach(d -> {
			DeviceVO deviceVO = new DeviceVO();
			BeanUtils.copyProperties(d, deviceVO);
			deviceVOS.add(deviceVO);
		});
		return deviceVOS;
	}

	//为设备添加分组
	public boolean addDeviceToGroup(long deviceId, long groupId) {
		deviceDao.updateGroupIdById(deviceId, groupId);
		return true;
	}

	//从分组删除设备
	public boolean deleteFromGroup(long deviceId) {
		deviceDao.updateGroupIdById(deviceId, 0);
		return true;
	}
}
