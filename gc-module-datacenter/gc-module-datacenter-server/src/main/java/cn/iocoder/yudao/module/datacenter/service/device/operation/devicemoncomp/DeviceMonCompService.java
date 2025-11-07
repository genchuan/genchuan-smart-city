package cn.iocoder.yudao.module.datacenter.service.device.operation.devicemoncomp;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicemoncomp.vo.DeviceMonCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicemoncomp.vo.DeviceMonCompSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.devicemoncomp.DeviceMonCompDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 设备关联监测部件 Service 接口
 *
 * @author zhucongquan
 */
public interface DeviceMonCompService {

    /**
     * 创建设备关联监测部件
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDeviceMonComp(@Valid DeviceMonCompSaveReqVO createReqVO);

    /**
     * 更新设备关联监测部件
     *
     * @param updateReqVO 更新信息
     */
    void updateDeviceMonComp(@Valid DeviceMonCompSaveReqVO updateReqVO);

    /**
     * 删除设备关联监测部件
     *
     * @param id 编号
     */
    void deleteDeviceMonComp(Long id);

    /**
     * 获得设备关联监测部件
     *
     * @param id 编号
     * @return 设备关联监测部件
     */
    DeviceMonCompDO getDeviceMonComp(Long id);

    /**
     * 获得设备关联监测部件分页
     *
     * @param pageReqVO 分页查询
     * @return 设备关联监测部件分页
     */
    PageResult<DeviceMonCompDO> getDeviceMonCompPage(DeviceMonCompPageReqVO pageReqVO);

}