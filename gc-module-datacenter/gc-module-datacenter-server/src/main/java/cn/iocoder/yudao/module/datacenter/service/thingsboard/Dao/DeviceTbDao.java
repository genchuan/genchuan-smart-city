package cn.iocoder.yudao.module.datacenter.service.thingsboard.Dao;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.vo.DevicePageReqVO;
import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.common.data.DeviceInfo;
import org.thingsboard.server.common.data.alarm.Alarm;
import org.thingsboard.server.common.data.alarm.AlarmInfo;
import org.thingsboard.server.common.data.id.EntityId;
import org.thingsboard.server.common.data.kv.AttributeKvEntry;
import org.thingsboard.server.common.data.page.PageData;
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
}
