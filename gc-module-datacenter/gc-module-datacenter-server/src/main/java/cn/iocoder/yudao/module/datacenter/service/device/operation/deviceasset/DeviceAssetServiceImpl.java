package cn.iocoder.yudao.module.datacenter.service.device.operation.deviceasset;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.deviceasset.vo.DeviceAssetPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.deviceasset.vo.DeviceAssetSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.deviceasset.DeviceAssetDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.device.operation.deviceasset.DeviceAssetMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 设备关联资产 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class DeviceAssetServiceImpl implements DeviceAssetService {

    @Resource
    private DeviceAssetMapper deviceAssetMapper;

    @Override
    public Long createDeviceAsset(DeviceAssetSaveReqVO createReqVO) {
        // 插入
        DeviceAssetDO deviceAsset = BeanUtils.toBean(createReqVO, DeviceAssetDO.class);
        deviceAssetMapper.insert(deviceAsset);
        // 返回
        return deviceAsset.getId();
    }

    @Override
    public void updateDeviceAsset(DeviceAssetSaveReqVO updateReqVO) {
        // 校验存在
        validateDeviceAssetExists(updateReqVO.getId());
        // 更新
        DeviceAssetDO updateObj = BeanUtils.toBean(updateReqVO, DeviceAssetDO.class);
        deviceAssetMapper.updateById(updateObj);
    }

    @Override
    public void deleteDeviceAsset(Long id) {
        // 校验存在
        validateDeviceAssetExists(id);
        // 删除
        deviceAssetMapper.deleteById(id);
    }

    private void validateDeviceAssetExists(Long id) {
        if (deviceAssetMapper.selectById(id) == null) {
            throw exception(DEVICE_ASSET_NOT_EXISTS);
        }
    }

    @Override
    public DeviceAssetDO getDeviceAsset(Long id) {
        return deviceAssetMapper.selectById(id);
    }

    @Override
    public PageResult<DeviceAssetDO> getDeviceAssetPage(DeviceAssetPageReqVO pageReqVO) {
        return deviceAssetMapper.selectPage(pageReqVO);
    }

}