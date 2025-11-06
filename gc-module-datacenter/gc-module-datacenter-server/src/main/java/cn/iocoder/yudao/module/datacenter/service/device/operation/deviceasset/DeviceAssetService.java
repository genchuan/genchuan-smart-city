package cn.iocoder.yudao.module.datacenter.service.device.operation.deviceasset;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.deviceasset.vo.DeviceAssetPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.deviceasset.vo.DeviceAssetSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.deviceasset.DeviceAssetDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 设备关联资产 Service 接口
 *
 * @author zhucongquan
 */
public interface DeviceAssetService {

    /**
     * 创建设备关联资产
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDeviceAsset(@Valid DeviceAssetSaveReqVO createReqVO);

    /**
     * 更新设备关联资产
     *
     * @param updateReqVO 更新信息
     */
    void updateDeviceAsset(@Valid DeviceAssetSaveReqVO updateReqVO);

    /**
     * 删除设备关联资产
     *
     * @param id 编号
     */
    void deleteDeviceAsset(Long id);

    /**
     * 获得设备关联资产
     *
     * @param id 编号
     * @return 设备关联资产
     */
    DeviceAssetDO getDeviceAsset(Long id);

    /**
     * 获得设备关联资产分页
     *
     * @param pageReqVO 分页查询
     * @return 设备关联资产分页
     */
    PageResult<DeviceAssetDO> getDeviceAssetPage(DeviceAssetPageReqVO pageReqVO);

}