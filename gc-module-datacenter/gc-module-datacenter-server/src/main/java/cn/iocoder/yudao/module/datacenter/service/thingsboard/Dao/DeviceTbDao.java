package cn.iocoder.yudao.module.datacenter.service.thingsboard.Dao;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.vo.DevicePageReqVO;
import org.thingsboard.server.common.data.Device;

public interface DeviceTbDao {
    PageResult<Device> getDevicePage(DevicePageReqVO pageReqVO);
}
