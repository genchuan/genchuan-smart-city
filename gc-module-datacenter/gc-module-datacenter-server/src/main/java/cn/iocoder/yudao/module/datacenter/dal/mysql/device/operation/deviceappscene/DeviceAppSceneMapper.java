package cn.iocoder.yudao.module.datacenter.dal.mysql.device.operation.deviceappscene;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.deviceappscene.vo.DeviceAppScenePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.deviceappscene.DeviceAppSceneDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备关联应用场景 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface DeviceAppSceneMapper extends BaseMapperX<DeviceAppSceneDO> {

    default PageResult<DeviceAppSceneDO> selectPage(DeviceAppScenePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeviceAppSceneDO>()
                .eqIfPresent(DeviceAppSceneDO::getDeviceRelAppSceneId, reqVO.getDeviceRelAppSceneId())
                .eqIfPresent(DeviceAppSceneDO::getDeviceId, reqVO.getDeviceId())
                .likeIfPresent(DeviceAppSceneDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(DeviceAppSceneDO::getDeviceCode, reqVO.getDeviceCode())
                .eqIfPresent(DeviceAppSceneDO::getAppSceneId, reqVO.getAppSceneId())
                .likeIfPresent(DeviceAppSceneDO::getAppSceneName, reqVO.getAppSceneName())
                .eqIfPresent(DeviceAppSceneDO::getAppSceneCode, reqVO.getAppSceneCode())
                .betweenIfPresent(DeviceAppSceneDO::getRelTime, reqVO.getRelTime())
                .eqIfPresent(DeviceAppSceneDO::getRelUser, reqVO.getRelUser())
                .eqIfPresent(DeviceAppSceneDO::getRelStatus, reqVO.getRelStatus())
                .betweenIfPresent(DeviceAppSceneDO::getAdjustTime, reqVO.getAdjustTime())
                .eqIfPresent(DeviceAppSceneDO::getAdjustUser, reqVO.getAdjustUser())
                .eqIfPresent(DeviceAppSceneDO::getAdjustReason, reqVO.getAdjustReason())
                .eqIfPresent(DeviceAppSceneDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DeviceAppSceneDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DeviceAppSceneDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DeviceAppSceneDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DeviceAppSceneDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceAppSceneDO::getId));
    }

}