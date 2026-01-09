package cn.iocoder.yudao.module.datacenter.service.thingsboard.device.Dao;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.device.vo.DevicePageReqVO;
import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.common.data.DeviceInfo;
import org.thingsboard.server.common.data.alarm.Alarm;
import org.thingsboard.server.common.data.alarm.AlarmInfo;
import org.thingsboard.server.common.data.id.EntityId;
import org.thingsboard.server.common.data.kv.AttributeKvEntry;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;
import org.thingsboard.server.common.data.page.TimePageLink;

import java.util.List;
import java.util.Map;

public interface DeviceTbDao {
    PageResult<Device> getDevicePage(DevicePageReqVO pageReqVO);

    Device getDeviceById(String id);

    DeviceInfo getDeviceInfoById(String id);
    public List<AttributeKvEntry> getAttributeKvEntries(String id);

    /**
     * 获取告警列表（带分页）
     */
    PageData<AlarmInfo> getAlarms(TimePageLink pageLink);

    /**
     * 获取所有设备列表（分页）- 使用新的API
     * @param pageLink 分页参数
     * @return 设备分页数据
     */
    PageData<DeviceInfo> getAllDevices(PageLink pageLink);

    /**
     * 获取设备详情信息
     * @param deviceId 设备ID
     * @return 设备详情
     */
    DeviceInfo getDeviceInfo(String deviceId);

    /**
     * 创建设备到ThingsBoard
     * @param device 设备信息
     * @return 创建后的设备信息
     */
    Device createDevice(Device device);

    /**
     * 从ThingsBoard删除设备
     * @param deviceId 设备ID
     */
    void deleteDevice(String deviceId);

    /**
     * 更新设备信息到ThingsBoard
     * @param device 设备信息
     * @return 更新后的设备信息
     */
    Device updateDevice(Device device);

    /**
     * 获取设备属性
     * @param deviceId 设备ID
     * @return 属性列表
     */
    List<Map<String, Object>> getDeviceAttributes(String deviceId);

    /**
     * 添加设备属性
     * @param deviceId 设备ID
     * @param attributes 属性键值对
     */
    void addDeviceAttributes(String deviceId, Map<String, Object> attributes);

    /**
     * 删除设备属性
     * @param deviceId 设备ID
     * @param scope 属性作用域
     * @param keys 要删除的属性键列表
     */
    void deleteDeviceAttributes(String deviceId, String scope, List<String> keys);
}
