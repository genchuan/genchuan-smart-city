package cn.iocoder.yudao.module.datacenter.service.device.status.devicerealtimestatus;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.status.devicerealtimestatus.vo.DeviceRealTimeStatusPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.status.devicerealtimestatus.vo.DeviceRealTimeStatusSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.status.devicerealtimestatus.DeviceRealTimeStatusDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 设备实时状态 Service 接口
 *
 * @author zhucongquan
 */
public interface DeviceRealTimeStatusService {

    /**
     * 创建设备实时状态
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDeviceRealTimeStatus(@Valid DeviceRealTimeStatusSaveReqVO createReqVO);

    /**
     * 更新设备实时状态
     *
     * @param updateReqVO 更新信息
     */
    void updateDeviceRealTimeStatus(@Valid DeviceRealTimeStatusSaveReqVO updateReqVO);

    /**
     * 删除设备实时状态
     *
     * @param id 编号
     */
    void deleteDeviceRealTimeStatus(Long id);

    /**
     * 获得设备实时状态
     *
     * @param id 编号
     * @return 设备实时状态
     */
    DeviceRealTimeStatusDO getDeviceRealTimeStatus(Long id);

    /**
     * 获得设备实时状态分页
     *
     * @param pageReqVO 分页查询
     * @return 设备实时状态分页
     */
    PageResult<DeviceRealTimeStatusDO> getDeviceRealTimeStatusPage(DeviceRealTimeStatusPageReqVO pageReqVO);

}