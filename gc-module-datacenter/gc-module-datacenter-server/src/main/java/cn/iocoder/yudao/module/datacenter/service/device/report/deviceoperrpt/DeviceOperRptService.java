package cn.iocoder.yudao.module.datacenter.service.device.report.deviceoperrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.report.deviceoperrpt.vo.DeviceOperRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.report.deviceoperrpt.vo.DeviceOperRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.report.deviceoperrpt.DeviceOperRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 设备运行状态统计 Service 接口
 *
 * @author zhucongquan
 */
public interface DeviceOperRptService {

    /**
     * 创建设备运行状态统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDeviceOperRpt(@Valid DeviceOperRptSaveReqVO createReqVO);

    /**
     * 更新设备运行状态统计
     *
     * @param updateReqVO 更新信息
     */
    void updateDeviceOperRpt(@Valid DeviceOperRptSaveReqVO updateReqVO);

    /**
     * 删除设备运行状态统计
     *
     * @param id 编号
     */
    void deleteDeviceOperRpt(Long id);

    /**
     * 获得设备运行状态统计
     *
     * @param id 编号
     * @return 设备运行状态统计
     */
    DeviceOperRptDO getDeviceOperRpt(Long id);

    /**
     * 获得设备运行状态统计分页
     *
     * @param pageReqVO 分页查询
     * @return 设备运行状态统计分页
     */
    PageResult<DeviceOperRptDO> getDeviceOperRptPage(DeviceOperRptPageReqVO pageReqVO);

}