package cn.iocoder.yudao.module.datacenter.service.device.operation.devicedevice;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicedevice.vo.DeviceDevicePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicedevice.vo.DeviceDeviceSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.devicedevice.DeviceDeviceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 设备关联设备 Service 接口
 *
 * @author zhucongquan
 */
public interface DeviceDeviceService {

    /**
     * 创建设备关联设备
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDeviceDevice(@Valid DeviceDeviceSaveReqVO createReqVO);

    /**
     * 更新设备关联设备
     *
     * @param updateReqVO 更新信息
     */
    void updateDeviceDevice(@Valid DeviceDeviceSaveReqVO updateReqVO);

    /**
     * 删除设备关联设备
     *
     * @param id 编号
     */
    void deleteDeviceDevice(Long id);

    /**
     * 获得设备关联设备
     *
     * @param id 编号
     * @return 设备关联设备
     */
    DeviceDeviceDO getDeviceDevice(Long id);

    /**
     * 获得设备关联设备分页
     *
     * @param pageReqVO 分页查询
     * @return 设备关联设备分页
     */
    PageResult<DeviceDeviceDO> getDeviceDevicePage(DeviceDevicePageReqVO pageReqVO);

}