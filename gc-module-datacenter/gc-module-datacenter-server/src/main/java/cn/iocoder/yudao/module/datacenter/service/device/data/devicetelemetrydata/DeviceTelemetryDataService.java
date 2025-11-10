package cn.iocoder.yudao.module.datacenter.service.device.data.devicetelemetrydata;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicetelemetrydata.vo.DeviceTelemetryDataPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicetelemetrydata.vo.DeviceTelemetryDataSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.data.devicetelemetrydata.DeviceTelemetryDataDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 设备遥测数据 Service 接口
 *
 * @author zhucongquan
 */
public interface DeviceTelemetryDataService {

    /**
     * 创建设备遥测数据
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDeviceTelemetryData(@Valid DeviceTelemetryDataSaveReqVO createReqVO);

    /**
     * 更新设备遥测数据
     *
     * @param updateReqVO 更新信息
     */
    void updateDeviceTelemetryData(@Valid DeviceTelemetryDataSaveReqVO updateReqVO);

    /**
     * 删除设备遥测数据
     *
     * @param id 编号
     */
    void deleteDeviceTelemetryData(Long id);

    /**
     * 获得设备遥测数据
     *
     * @param id 编号
     * @return 设备遥测数据
     */
    DeviceTelemetryDataDO getDeviceTelemetryData(Long id);

    /**
     * 获得设备遥测数据分页
     *
     * @param pageReqVO 分页查询
     * @return 设备遥测数据分页
     */
    PageResult<DeviceTelemetryDataDO> getDeviceTelemetryDataPage(DeviceTelemetryDataPageReqVO pageReqVO);

}