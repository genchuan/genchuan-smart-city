package cn.iocoder.yudao.module.datacenter.dal.mysql.device.report.devicecatrpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.report.devicecatrpt.vo.DeviceCatRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.report.devicecatrpt.DeviceCatRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备分类统计 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface DeviceCatRptMapper extends BaseMapperX<DeviceCatRptDO> {

    default PageResult<DeviceCatRptDO> selectPage(DeviceCatRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeviceCatRptDO>()
                .eqIfPresent(DeviceCatRptDO::getCatStatId, reqVO.getCatStatId())
                .eqIfPresent(DeviceCatRptDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(DeviceCatRptDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(DeviceCatRptDO::getRegionCode, reqVO.getRegionCode())
                .likeIfPresent(DeviceCatRptDO::getRegionName, reqVO.getRegionName())
                .eqIfPresent(DeviceCatRptDO::getDeviceMajorId, reqVO.getDeviceMajorId())
                .likeIfPresent(DeviceCatRptDO::getDeviceMajorName, reqVO.getDeviceMajorName())
                .eqIfPresent(DeviceCatRptDO::getDeviceMidId, reqVO.getDeviceMidId())
                .likeIfPresent(DeviceCatRptDO::getDeviceMidName, reqVO.getDeviceMidName())
                .eqIfPresent(DeviceCatRptDO::getDeviceMinorId, reqVO.getDeviceMinorId())
                .likeIfPresent(DeviceCatRptDO::getDeviceMinorName, reqVO.getDeviceMinorName())
                .eqIfPresent(DeviceCatRptDO::getTotalDeviceCount, reqVO.getTotalDeviceCount())
                .eqIfPresent(DeviceCatRptDO::getOnlineDeviceCount, reqVO.getOnlineDeviceCount())
                .eqIfPresent(DeviceCatRptDO::getOfflineDeviceCount, reqVO.getOfflineDeviceCount())
                .eqIfPresent(DeviceCatRptDO::getFaultDeviceCount, reqVO.getFaultDeviceCount())
                .eqIfPresent(DeviceCatRptDO::getNewDeviceCount, reqVO.getNewDeviceCount())
                .eqIfPresent(DeviceCatRptDO::getScrappedDeviceCount, reqVO.getScrappedDeviceCount())
                .betweenIfPresent(DeviceCatRptDO::getStatTime, reqVO.getStatTime())
                .eqIfPresent(DeviceCatRptDO::getStatUser, reqVO.getStatUser())
                .eqIfPresent(DeviceCatRptDO::getRptRemark, reqVO.getRptRemark())
                .eqIfPresent(DeviceCatRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DeviceCatRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DeviceCatRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DeviceCatRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DeviceCatRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceCatRptDO::getId));
    }

}