package cn.iocoder.yudao.module.facility.dal.mysql.road.roadmonitor;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.facility.controller.admin.road.monitor.vo.RoadMonitorPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.road.monitor.vo.RealtimePageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.road.monitor.vo.RealtimePageRespVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.road.roadmonitor.RoadMonitorDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 道路监测 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface RoadMonitorMapper extends BaseMapperX<RoadMonitorDO> {

    default PageResult<RoadMonitorDO> selectPage(RoadMonitorPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RoadMonitorDO>()
                .eqIfPresent(RoadMonitorDO::getMonitorCode, reqVO.getMonitorCode())
                .eqIfPresent(RoadMonitorDO::getRoadId, reqVO.getRoadId())
                .eqIfPresent(RoadMonitorDO::getDeviceId, reqVO.getDeviceId())
                .eqIfPresent(RoadMonitorDO::getConfigId, reqVO.getConfigId())
                .eqIfPresent(RoadMonitorDO::getPotholeNum, reqVO.getPotholeNum())
                .eqIfPresent(RoadMonitorDO::getCrackLength, reqVO.getCrackLength())
                .eqIfPresent(RoadMonitorDO::getRoadTemp, reqVO.getRoadTemp())
                .eqIfPresent(RoadMonitorDO::getTrafficFlow, reqVO.getTrafficFlow())
                .eqIfPresent(RoadMonitorDO::getPotholeNumThreshold, reqVO.getPotholeNumThreshold())
                .eqIfPresent(RoadMonitorDO::getCrackLengthThreshold, reqVO.getCrackLengthThreshold())
                .eqIfPresent(RoadMonitorDO::getRoadTempThreshold, reqVO.getRoadTempThreshold())
                .eqIfPresent(RoadMonitorDO::getTrafficFlowThreshold, reqVO.getTrafficFlowThreshold())
                .eqIfPresent(RoadMonitorDO::getCollectFrequencySnapshot, reqVO.getCollectFrequencySnapshot())
                .eqIfPresent(RoadMonitorDO::getIsWarning, reqVO.getIsWarning())
                .eqIfPresent(RoadMonitorDO::getWarningIdListStr, reqVO.getWarningIdListStr())
                .eqIfPresent(RoadMonitorDO::getMonitorStatus, reqVO.getMonitorStatus())
                .eqIfPresent(RoadMonitorDO::getStaffId, reqVO.getStaffId())
                .likeIfPresent(RoadMonitorDO::getStaffName, reqVO.getStaffName())
                .eqIfPresent(RoadMonitorDO::getSyncDuration, reqVO.getSyncDuration())
                .betweenIfPresent(RoadMonitorDO::getRecordTime, reqVO.getRecordTime())
                .betweenIfPresent(RoadMonitorDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(RoadMonitorDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(RoadMonitorDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(RoadMonitorDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(RoadMonitorDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(RoadMonitorDO::getId));
    }


    List<RealtimePageRespVO> getRealtimePage(RealtimePageReqVO reqVO);

    long countRealtimePage(RealtimePageReqVO reqVO);

    int updateAllMonitorStatus(String monitorStatus);

    int batchUpdateMonitorStatus(List<Long> roadIdList, String monitorStatus);
}
