package cn.iocoder.yudao.module.smartcity.service.monitoringdevice;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.monitoringdevice.vo.MonitoringDevicePageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.monitoringdevice.vo.MonitoringDeviceSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.monitoringdevice.MonitoringDeviceDO;
import jakarta.validation.Valid;

/**
 * 监测设备 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface MonitoringDeviceService {

    /**
     * 创建监测设备
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMonitoringDevice(@Valid MonitoringDeviceSaveReqVO createReqVO);

    /**
     * 更新监测设备
     *
     * @param updateReqVO 更新信息
     */
    void updateMonitoringDevice(@Valid MonitoringDeviceSaveReqVO updateReqVO);

    /**
     * 删除监测设备
     *
     * @param id 编号
     */
    void deleteMonitoringDevice(Long id);

    /**
     * 获得监测设备
     *
     * @param id 编号
     * @return 监测设备
     */
    MonitoringDeviceDO getMonitoringDevice(Long id);

    /**
     * 获得监测设备分页
     *
     * @param pageReqVO 分页查询
     * @return 监测设备分页
     */
    PageResult<MonitoringDeviceDO> getMonitoringDevicePage(MonitoringDevicePageReqVO pageReqVO);

}