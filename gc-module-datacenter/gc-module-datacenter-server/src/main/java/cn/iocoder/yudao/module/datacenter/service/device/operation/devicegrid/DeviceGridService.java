package cn.iocoder.yudao.module.datacenter.service.device.operation.devicegrid;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicegrid.vo.DeviceGridPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicegrid.vo.DeviceGridSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.devicegrid.DeviceGridDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 设备关联网格 Service 接口
 *
 * @author zhucongquan
 */
public interface DeviceGridService {

    /**
     * 创建设备关联网格
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDeviceGrid(@Valid DeviceGridSaveReqVO createReqVO);

    /**
     * 更新设备关联网格
     *
     * @param updateReqVO 更新信息
     */
    void updateDeviceGrid(@Valid DeviceGridSaveReqVO updateReqVO);

    /**
     * 删除设备关联网格
     *
     * @param id 编号
     */
    void deleteDeviceGrid(Long id);

    /**
     * 获得设备关联网格
     *
     * @param id 编号
     * @return 设备关联网格
     */
    DeviceGridDO getDeviceGrid(Long id);

    /**
     * 获得设备关联网格分页
     *
     * @param pageReqVO 分页查询
     * @return 设备关联网格分页
     */
    PageResult<DeviceGridDO> getDeviceGridPage(DeviceGridPageReqVO pageReqVO);

}