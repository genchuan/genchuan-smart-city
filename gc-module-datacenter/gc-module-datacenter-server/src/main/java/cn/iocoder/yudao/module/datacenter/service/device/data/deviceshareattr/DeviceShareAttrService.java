package cn.iocoder.yudao.module.datacenter.service.device.data.deviceshareattr;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.deviceshareattr.vo.DeviceShareAttrPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.deviceshareattr.vo.DeviceShareAttrSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.data.deviceshareattr.DeviceShareAttrDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 设备共享属性配置 Service 接口
 *
 * @author zhucongquan
 */
public interface DeviceShareAttrService {

    /**
     * 创建设备共享属性配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDeviceShareAttr(@Valid DeviceShareAttrSaveReqVO createReqVO);

    /**
     * 更新设备共享属性配置
     *
     * @param updateReqVO 更新信息
     */
    void updateDeviceShareAttr(@Valid DeviceShareAttrSaveReqVO updateReqVO);

    /**
     * 删除设备共享属性配置
     *
     * @param id 编号
     */
    void deleteDeviceShareAttr(Long id);

    /**
     * 获得设备共享属性配置
     *
     * @param id 编号
     * @return 设备共享属性配置
     */
    DeviceShareAttrDO getDeviceShareAttr(Long id);

    /**
     * 获得设备共享属性配置分页
     *
     * @param pageReqVO 分页查询
     * @return 设备共享属性配置分页
     */
    PageResult<DeviceShareAttrDO> getDeviceShareAttrPage(DeviceShareAttrPageReqVO pageReqVO);

}