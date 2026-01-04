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
}
