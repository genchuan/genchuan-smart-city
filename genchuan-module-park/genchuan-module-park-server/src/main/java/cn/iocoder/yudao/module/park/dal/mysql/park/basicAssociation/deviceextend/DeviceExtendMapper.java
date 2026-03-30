package cn.iocoder.yudao.module.park.dal.mysql.park.basicAssociation.deviceextend;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.deviceextend.vo.DeviceExtendPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.deviceextend.DeviceExtendDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备扩展 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface DeviceExtendMapper extends BaseMapperX<DeviceExtendDO> {

    default PageResult<DeviceExtendDO> selectPage(DeviceExtendPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeviceExtendDO>()
                .eqIfPresent(DeviceExtendDO::getDeviceType, reqVO.getDeviceType())
                .eqIfPresent(DeviceExtendDO::getAssetId, reqVO.getAssetId())
                .eqIfPresent(DeviceExtendDO::getDeviceCode, reqVO.getDeviceCode())
                .eqIfPresent(DeviceExtendDO::getDeviceStatus, reqVO.getDeviceStatus())
                .betweenIfPresent(DeviceExtendDO::getInstallTime, reqVO.getInstallTime())
                .betweenIfPresent(DeviceExtendDO::getLastMaintainTime, reqVO.getLastMaintainTime())
                .betweenIfPresent(DeviceExtendDO::getNextMaintainTime, reqVO.getNextMaintainTime())
                .betweenIfPresent(DeviceExtendDO::getDeviceCreateTime, reqVO.getDeviceCreateTime())
                .betweenIfPresent(DeviceExtendDO::getDeviceUpdateTime, reqVO.getDeviceUpdateTime())
                .eqIfPresent(DeviceExtendDO::getDeviceRemark, reqVO.getDeviceRemark())
                .betweenIfPresent(DeviceExtendDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceExtendDO::getId));
    }

}
