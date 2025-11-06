package cn.iocoder.yudao.module.datacenter.service.device.deviceinfo;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.deviceinfo.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.deviceinfo.DeviceInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 设备信息 Service 接口
 *
 * @author zhucongquan
 */
public interface DeviceInfoService {

    /**
     * 创建设备信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDeviceInfo(@Valid DeviceInfoSaveReqVO createReqVO);

    /**
     * 更新设备信息
     *
     * @param updateReqVO 更新信息
     */
    void updateDeviceInfo(@Valid DeviceInfoSaveReqVO updateReqVO);

    /**
     * 删除设备信息
     *
     * @param id 编号
     */
    void deleteDeviceInfo(Long id);

    /**
     * 获得设备信息
     *
     * @param id 编号
     * @return 设备信息
     */
    DeviceInfoDO getDeviceInfo(Long id);

    /**
     * 获得设备信息分页
     *
     * @param pageReqVO 分页查询
     * @return 设备信息分页
     */
    PageResult<DeviceInfoDO> getDeviceInfoPage(DeviceInfoPageReqVO pageReqVO);

}