package cn.iocoder.yudao.module.park.service.park.basicAssociation.deviceextend;

import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.deviceextend.vo.DeviceExtendPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.deviceextend.vo.DeviceExtendSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.deviceextend.DeviceExtendDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 设备扩展 Service 接口
 *
 * @author zhucongquan
 */
public interface DeviceExtendService {

    /**
     * 创建设备扩展
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDeviceExtend(@Valid DeviceExtendSaveReqVO createReqVO);

    /**
     * 更新设备扩展
     *
     * @param updateReqVO 更新信息
     */
    void updateDeviceExtend(@Valid DeviceExtendSaveReqVO updateReqVO);

    /**
     * 删除设备扩展
     *
     * @param id 编号
     */
    void deleteDeviceExtend(Long id);

    /**
     * 获得设备扩展
     *
     * @param id 编号
     * @return 设备扩展
     */
    DeviceExtendDO getDeviceExtend(Long id);

    /**
     * 获得设备扩展分页
     *
     * @param pageReqVO 分页查询
     * @return 设备扩展分页
     */
    PageResult<DeviceExtendDO> getDeviceExtendPage(DeviceExtendPageReqVO pageReqVO);

}