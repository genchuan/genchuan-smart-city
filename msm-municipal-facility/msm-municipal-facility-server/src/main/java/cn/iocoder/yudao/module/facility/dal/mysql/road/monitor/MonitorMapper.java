package cn.iocoder.yudao.module.facility.dal.mysql.road.monitor;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.facility.controller.admin.road.monitor.vo.MonitorPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.road.monitor.vo.RealtimePageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.road.monitor.vo.RealtimePageRespVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.road.monitor.MonitorDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 道路监测 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface MonitorMapper extends BaseMapperX<MonitorDO> {

    default PageResult<MonitorDO> selectPage(MonitorPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MonitorDO>()
                .eqIfPresent(MonitorDO::getMonitorCode, reqVO.getMonitorCode())
                .eqIfPresent(MonitorDO::getRoadId, reqVO.getRoadId())
                .eqIfPresent(MonitorDO::getDeviceId, reqVO.getDeviceId())
                .eqIfPresent(MonitorDO::getConfigId, reqVO.getConfigId())
                .eqIfPresent(MonitorDO::getPotholeNum, reqVO.getPotholeNum())
                .eqIfPresent(MonitorDO::getCrackLength, reqVO.getCrackLength())
                .eqIfPresent(MonitorDO::getRoadTemp, reqVO.getRoadTemp())
                .eqIfPresent(MonitorDO::getTrafficFlow, reqVO.getTrafficFlow())
                .eqIfPresent(MonitorDO::getPotholeNumThreshold, reqVO.getPotholeNumThreshold())
                .eqIfPresent(MonitorDO::getCrackLengthThreshold, reqVO.getCrackLengthThreshold())
                .eqIfPresent(MonitorDO::getRoadTempThreshold, reqVO.getRoadTempThreshold())
                .eqIfPresent(MonitorDO::getTrafficFlowThreshold, reqVO.getTrafficFlowThreshold())
                .eqIfPresent(MonitorDO::getCollectFrequencySnapshot, reqVO.getCollectFrequencySnapshot())
                .eqIfPresent(MonitorDO::getIsWarning, reqVO.getIsWarning())
                .eqIfPresent(MonitorDO::getWarningId, reqVO.getWarningId())
                .eqIfPresent(MonitorDO::getMonitorStatus, reqVO.getMonitorStatus())
                .eqIfPresent(MonitorDO::getStaffId, reqVO.getStaffId())
                .likeIfPresent(MonitorDO::getStaffName, reqVO.getStaffName())
                .eqIfPresent(MonitorDO::getSyncDuration, reqVO.getSyncDuration())
                .betweenIfPresent(MonitorDO::getRecordTime, reqVO.getRecordTime())
                .betweenIfPresent(MonitorDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(MonitorDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(MonitorDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(MonitorDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(MonitorDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(MonitorDO::getId));
    }


    List<RealtimePageRespVO> getRealtimePage(RealtimePageReqVO reqVO);

    long countRealtimePage(RealtimePageReqVO reqVO);
}
