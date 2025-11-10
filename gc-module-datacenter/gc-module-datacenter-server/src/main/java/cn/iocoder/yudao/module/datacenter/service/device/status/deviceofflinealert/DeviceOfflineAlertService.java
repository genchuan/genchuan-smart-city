package cn.iocoder.yudao.module.datacenter.service.device.status.deviceofflinealert;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.status.deviceofflinealert.vo.DeviceOfflineAlertPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.status.deviceofflinealert.vo.DeviceOfflineAlertSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.status.deviceofflinealert.DeviceOfflineAlertDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 设备离线预警 Service 接口
 *
 * @author zhucongquan
 */
public interface DeviceOfflineAlertService {

    /**
     * 创建设备离线预警
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDeviceOfflineAlert(@Valid DeviceOfflineAlertSaveReqVO createReqVO);

    /**
     * 更新设备离线预警
     *
     * @param updateReqVO 更新信息
     */
    void updateDeviceOfflineAlert(@Valid DeviceOfflineAlertSaveReqVO updateReqVO);

    /**
     * 删除设备离线预警
     *
     * @param id 编号
     */
    void deleteDeviceOfflineAlert(Long id);

    /**
     * 获得设备离线预警
     *
     * @param id 编号
     * @return 设备离线预警
     */
    DeviceOfflineAlertDO getDeviceOfflineAlert(Long id);

    /**
     * 获得设备离线预警分页
     *
     * @param pageReqVO 分页查询
     * @return 设备离线预警分页
     */
    PageResult<DeviceOfflineAlertDO> getDeviceOfflineAlertPage(DeviceOfflineAlertPageReqVO pageReqVO);

}