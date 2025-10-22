package cn.iocoder.yudao.module.datacenter.service.thingsboard.Dao;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.vo.DevicePageReqVO;
import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.common.data.DeviceInfo;
import org.thingsboard.server.common.data.id.EntityId;
import org.thingsboard.server.common.data.kv.AttributeKvEntry;

import java.util.List;

public interface DeviceTbDao {
    PageResult<Device> getDevicePage(DevicePageReqVO pageReqVO);

    Device getDeviceById(String id);

    DeviceInfo getDeviceInfoById(String id);
    public List<AttributeKvEntry> getAttributeKvEntries(String id);

}
