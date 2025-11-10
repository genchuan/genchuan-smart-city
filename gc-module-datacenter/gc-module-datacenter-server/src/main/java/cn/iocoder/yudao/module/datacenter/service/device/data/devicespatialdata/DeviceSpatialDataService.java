package cn.iocoder.yudao.module.datacenter.service.device.data.devicespatialdata;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicespatialdata.vo.DeviceSpatialDataPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicespatialdata.vo.DeviceSpatialDataSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.data.devicespatialdata.DeviceSpatialDataDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 设备空间数据 Service 接口
 *
 * @author zhucongquan
 */
public interface DeviceSpatialDataService {

    /**
     * 创建设备空间数据
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDeviceSpatialData(@Valid DeviceSpatialDataSaveReqVO createReqVO);

    /**
     * 更新设备空间数据
     *
     * @param updateReqVO 更新信息
     */
    void updateDeviceSpatialData(@Valid DeviceSpatialDataSaveReqVO updateReqVO);

    /**
     * 删除设备空间数据
     *
     * @param id 编号
     */
    void deleteDeviceSpatialData(Long id);

    /**
     * 获得设备空间数据
     *
     * @param id 编号
     * @return 设备空间数据
     */
    DeviceSpatialDataDO getDeviceSpatialData(Long id);

    /**
     * 获得设备空间数据分页
     *
     * @param pageReqVO 分页查询
     * @return 设备空间数据分页
     */
    PageResult<DeviceSpatialDataDO> getDeviceSpatialDataPage(DeviceSpatialDataPageReqVO pageReqVO);

}