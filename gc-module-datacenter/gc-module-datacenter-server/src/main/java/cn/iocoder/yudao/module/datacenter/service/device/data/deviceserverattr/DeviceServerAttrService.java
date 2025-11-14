package cn.iocoder.yudao.module.datacenter.service.device.data.deviceserverattr;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.deviceserverattr.vo.DeviceServerAttrPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.deviceserverattr.vo.DeviceServerAttrSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.data.deviceserverattr.DeviceServerAttrDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 设备服务器属性配置 Service 接口
 *
 * @author zhucongquan
 */
public interface DeviceServerAttrService {

    /**
     * 创建设备服务器属性配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDeviceServerAttr(@Valid DeviceServerAttrSaveReqVO createReqVO);

    /**
     * 更新设备服务器属性配置
     *
     * @param updateReqVO 更新信息
     */
    void updateDeviceServerAttr(@Valid DeviceServerAttrSaveReqVO updateReqVO);

    /**
     * 删除设备服务器属性配置
     *
     * @param id 编号
     */
    void deleteDeviceServerAttr(Long id);

    /**
     * 获得设备服务器属性配置
     *
     * @param id 编号
     * @return 设备服务器属性配置
     */
    DeviceServerAttrDO getDeviceServerAttr(Long id);

    /**
     * 获得设备服务器属性配置分页
     *
     * @param pageReqVO 分页查询
     * @return 设备服务器属性配置分页
     */
    PageResult<DeviceServerAttrDO> getDeviceServerAttrPage(DeviceServerAttrPageReqVO pageReqVO);

}