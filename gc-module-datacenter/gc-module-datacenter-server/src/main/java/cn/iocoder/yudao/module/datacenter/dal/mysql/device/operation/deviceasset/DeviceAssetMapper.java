package cn.iocoder.yudao.module.datacenter.dal.mysql.device.operation.deviceasset;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.deviceasset.vo.DeviceAssetPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.deviceasset.DeviceAssetDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备关联资产 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface DeviceAssetMapper extends BaseMapperX<DeviceAssetDO> {

    default PageResult<DeviceAssetDO> selectPage(DeviceAssetPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeviceAssetDO>()
                .eqIfPresent(DeviceAssetDO::getDeviceRelAssetId, reqVO.getDeviceRelAssetId())
                .eqIfPresent(DeviceAssetDO::getDeviceId, reqVO.getDeviceId())
                .likeIfPresent(DeviceAssetDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(DeviceAssetDO::getDeviceCode, reqVO.getDeviceCode())
                .eqIfPresent(DeviceAssetDO::getAssetId, reqVO.getAssetId())
                .likeIfPresent(DeviceAssetDO::getAssetName, reqVO.getAssetName())
                .eqIfPresent(DeviceAssetDO::getAssetCode, reqVO.getAssetCode())
                .betweenIfPresent(DeviceAssetDO::getRelTime, reqVO.getRelTime())
                .eqIfPresent(DeviceAssetDO::getRelUser, reqVO.getRelUser())
                .eqIfPresent(DeviceAssetDO::getRelStatus, reqVO.getRelStatus())
                .betweenIfPresent(DeviceAssetDO::getAdjustTime, reqVO.getAdjustTime())
                .eqIfPresent(DeviceAssetDO::getAdjustUser, reqVO.getAdjustUser())
                .eqIfPresent(DeviceAssetDO::getAdjustReason, reqVO.getAdjustReason())
                .eqIfPresent(DeviceAssetDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DeviceAssetDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DeviceAssetDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DeviceAssetDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DeviceAssetDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceAssetDO::getId));
    }

}