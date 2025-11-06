package cn.iocoder.yudao.module.datacenter.dal.mysql.device.operation.devicecatmng;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicecatmng.vo.DeviceCatMngPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.devicecatmng.DeviceCatMngDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备分类管理 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface DeviceCatMngMapper extends BaseMapperX<DeviceCatMngDO> {

    default PageResult<DeviceCatMngDO> selectPage(DeviceCatMngPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeviceCatMngDO>()
                .eqIfPresent(DeviceCatMngDO::getCatMngId, reqVO.getCatMngId())
                .eqIfPresent(DeviceCatMngDO::getDeviceId, reqVO.getDeviceId())
                .likeIfPresent(DeviceCatMngDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(DeviceCatMngDO::getDeviceCode, reqVO.getDeviceCode())
                .eqIfPresent(DeviceCatMngDO::getDeviceMajorId, reqVO.getDeviceMajorId())
                .likeIfPresent(DeviceCatMngDO::getDeviceMajorName, reqVO.getDeviceMajorName())
                .eqIfPresent(DeviceCatMngDO::getDeviceMidId, reqVO.getDeviceMidId())
                .likeIfPresent(DeviceCatMngDO::getDeviceMidName, reqVO.getDeviceMidName())
                .eqIfPresent(DeviceCatMngDO::getDeviceMinorId, reqVO.getDeviceMinorId())
                .likeIfPresent(DeviceCatMngDO::getDeviceMinorName, reqVO.getDeviceMinorName())
                .betweenIfPresent(DeviceCatMngDO::getCatTime, reqVO.getCatTime())
                .eqIfPresent(DeviceCatMngDO::getCatUser, reqVO.getCatUser())
                .eqIfPresent(DeviceCatMngDO::getCatStatus, reqVO.getCatStatus())
                .betweenIfPresent(DeviceCatMngDO::getAdjustTime, reqVO.getAdjustTime())
                .eqIfPresent(DeviceCatMngDO::getAdjustUser, reqVO.getAdjustUser())
                .eqIfPresent(DeviceCatMngDO::getAdjustReason, reqVO.getAdjustReason())
                .eqIfPresent(DeviceCatMngDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DeviceCatMngDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DeviceCatMngDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DeviceCatMngDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DeviceCatMngDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceCatMngDO::getId));
    }

}