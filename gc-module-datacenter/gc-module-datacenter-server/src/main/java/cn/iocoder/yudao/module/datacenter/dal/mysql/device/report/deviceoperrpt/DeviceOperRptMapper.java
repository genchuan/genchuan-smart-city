package cn.iocoder.yudao.module.datacenter.dal.mysql.device.report.deviceoperrpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.report.deviceoperrpt.vo.DeviceOperRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.report.deviceoperrpt.DeviceOperRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备运行状态统计 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface DeviceOperRptMapper extends BaseMapperX<DeviceOperRptDO> {

    default PageResult<DeviceOperRptDO> selectPage(DeviceOperRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeviceOperRptDO>()
                .eqIfPresent(DeviceOperRptDO::getOperStatId, reqVO.getOperStatId())
                .eqIfPresent(DeviceOperRptDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(DeviceOperRptDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(DeviceOperRptDO::getRegionCode, reqVO.getRegionCode())
                .likeIfPresent(DeviceOperRptDO::getRegionName, reqVO.getRegionName())
                .eqIfPresent(DeviceOperRptDO::getDeviceMajorId, reqVO.getDeviceMajorId())
                .likeIfPresent(DeviceOperRptDO::getDeviceMajorName, reqVO.getDeviceMajorName())
                .eqIfPresent(DeviceOperRptDO::getTotalDeviceCount, reqVO.getTotalDeviceCount())
                .eqIfPresent(DeviceOperRptDO::getAvgOnlineCount, reqVO.getAvgOnlineCount())
                .eqIfPresent(DeviceOperRptDO::getDeviceOnlineRate, reqVO.getDeviceOnlineRate())
                .eqIfPresent(DeviceOperRptDO::getTotalOfflineEndure, reqVO.getTotalOfflineEndure())
                .eqIfPresent(DeviceOperRptDO::getAvgOfflineEndure, reqVO.getAvgOfflineEndure())
                .eqIfPresent(DeviceOperRptDO::getGeneralAlertCount, reqVO.getGeneralAlertCount())
                .eqIfPresent(DeviceOperRptDO::getImportantAlertCount, reqVO.getImportantAlertCount())
                .eqIfPresent(DeviceOperRptDO::getUrgentAlertCount, reqVO.getUrgentAlertCount())
                .eqIfPresent(DeviceOperRptDO::getHandledAlertCount, reqVO.getHandledAlertCount())
                .eqIfPresent(DeviceOperRptDO::getAlertHandleRate, reqVO.getAlertHandleRate())
                .betweenIfPresent(DeviceOperRptDO::getStatTime, reqVO.getStatTime())
                .eqIfPresent(DeviceOperRptDO::getStatUser, reqVO.getStatUser())
                .eqIfPresent(DeviceOperRptDO::getRptRemark, reqVO.getRptRemark())
                .eqIfPresent(DeviceOperRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DeviceOperRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DeviceOperRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DeviceOperRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DeviceOperRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceOperRptDO::getId));
    }

}