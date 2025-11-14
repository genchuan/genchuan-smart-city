package cn.iocoder.yudao.module.datacenter.service.device.data.deviceclientattr;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.deviceclientattr.vo.DeviceClientAttrPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.deviceclientattr.vo.DeviceClientAttrSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.data.deviceclientattr.DeviceClientAttrDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 设备客户端属性配置 Service 接口
 *
 * @author zhucongquan
 */
public interface DeviceClientAttrService {

    /**
     * 创建设备客户端属性配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDeviceClientAttr(@Valid DeviceClientAttrSaveReqVO createReqVO);

    /**
     * 更新设备客户端属性配置
     *
     * @param updateReqVO 更新信息
     */
    void updateDeviceClientAttr(@Valid DeviceClientAttrSaveReqVO updateReqVO);

    /**
     * 删除设备客户端属性配置
     *
     * @param id 编号
     */
    void deleteDeviceClientAttr(Long id);

    /**
     * 获得设备客户端属性配置
     *
     * @param id 编号
     * @return 设备客户端属性配置
     */
    DeviceClientAttrDO getDeviceClientAttr(Long id);

    /**
     * 获得设备客户端属性配置分页
     *
     * @param pageReqVO 分页查询
     * @return 设备客户端属性配置分页
     */
    PageResult<DeviceClientAttrDO> getDeviceClientAttrPage(DeviceClientAttrPageReqVO pageReqVO);

}