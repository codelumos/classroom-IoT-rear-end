package org.nju.iot.controller;

import org.nju.iot.VO.DeviceVO;
import org.nju.iot.VO.GroupVO;
import org.nju.iot.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    // 设备分组信息
    @RequestMapping(value = "/overview", method = RequestMethod.GET)
    public List<GroupVO> getAllGroups() {
        return groupService.getAllGroups();
    }

    // 获取单个分组
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public GroupVO getDetail(@RequestParam long groupId) {
        return groupService.getDetail(groupId);
    }

    // 根据分组获得设备
    @RequestMapping(value = "/detail/group_id", method = RequestMethod.GET)
    public List<DeviceVO> getDeviceByGroup(@RequestParam long groupId) {
        return groupService.getDeviceByGroup(groupId);
    }

    // 添加分组
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public boolean addGroup(@RequestParam String groupName) {
        return groupService.addGroup(groupName);
    }

    // 删除分组
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public boolean deleteGroup(@RequestParam List<Long> groupIds) {
        return groupService.deleteGroup(groupIds);
    }

    // 获取没有分组的设备
    @RequestMapping(value = "/without", method = RequestMethod.GET)
    public List<DeviceVO> getDeviceWithoutGroup() {
        return groupService.getDeviceWithoutGroup();
    }

    // 为设备添加分组
    @RequestMapping(value = "/add/to", method = RequestMethod.POST)
    public boolean addDeviceToGroup(@RequestParam List<Long> deviceIds,
                                    @RequestParam long groupId) {
        return groupService.addDeviceToGroup(deviceIds, groupId);
    }

    // 从分组删除设备
    @RequestMapping(value = "/delete/from", method = RequestMethod.POST)
    public boolean deleteFromGroup(@RequestParam List<Long> deviceIds) {
        return groupService.deleteFromGroup(deviceIds);
    }

}
