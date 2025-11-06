package cn.iocoder.yudao.module.datacenter.service.device.operation.devicearea;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicearea.vo.DeviceAreaPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicearea.vo.DeviceAreaSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.devicearea.DeviceAreaDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 设备关联行政区划 Service 接口
 *
 * @author zhucongquan
 */
public interface DeviceAreaService {

    /**
     * 创建设备关联行政区划
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDeviceArea(@Valid DeviceAreaSaveReqVO createReqVO);

    /**
     * 更新设备关联行政区划
     *
     * @param updateReqVO 更新信息
     */
    void updateDeviceArea(@Valid DeviceAreaSaveReqVO updateReqVO);

    /**
     * 删除设备关联行政区划
     *
     * @param id 编号
     */
    void deleteDeviceArea(Long id);

    /**
     * 获得设备关联行政区划
     *
     * @param id 编号
     * @return 设备关联行政区划
     */
    DeviceAreaDO getDeviceArea(Long id);

    /**
     * 获得设备关联行政区划分页
     *
     * @param pageReqVO 分页查询
     * @return 设备关联行政区划分页
     */
    PageResult<DeviceAreaDO> getDeviceAreaPage(DeviceAreaPageReqVO pageReqVO);

}