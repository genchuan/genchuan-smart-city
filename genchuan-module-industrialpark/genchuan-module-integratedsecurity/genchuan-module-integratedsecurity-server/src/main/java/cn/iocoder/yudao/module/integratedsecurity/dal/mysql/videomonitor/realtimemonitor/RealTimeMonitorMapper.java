package cn.iocoder.yudao.module.integratedsecurity.dal.mysql.videomonitor.realtimemonitor;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.integratedsecurity.controller.admin.videomonitor.realtimemonitor.vo.RealTimeMonitorChartRespVO;
import cn.iocoder.yudao.module.integratedsecurity.controller.admin.videomonitor.realtimemonitor.vo.RealTimeMonitorPageReqVO;
import cn.iocoder.yudao.module.integratedsecurity.dal.dataobject.videomonitor.realtimemonitor.RealTimeMonitorDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 实时监控 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface RealTimeMonitorMapper extends BaseMapperX<RealTimeMonitorDO> {

    default PageResult<RealTimeMonitorDO> selectPage(RealTimeMonitorPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RealTimeMonitorDO>()
                .eqIfPresent(RealTimeMonitorDO::getCameraId, reqVO.getCameraId())
                .likeIfPresent(RealTimeMonitorDO::getCameraName, reqVO.getCameraName())
                .likeIfPresent(RealTimeMonitorDO::getArea, reqVO.getArea())
                .eqIfPresent(RealTimeMonitorDO::getRunStatus, reqVO.getRunStatus())
                .eqIfPresent(RealTimeMonitorDO::getAlarmStatus, reqVO.getAlarmStatus())
                .orderByDesc(RealTimeMonitorDO::getId));
    }

    default Long selectCountByRunStatus(String runStatus) {
        return selectCount(new LambdaQueryWrapperX<RealTimeMonitorDO>()
                .eq(RealTimeMonitorDO::getRunStatus, runStatus));
    }

    default Long selectCountByAlarmStatus(String alarmStatus) {
        return selectCount(new LambdaQueryWrapperX<RealTimeMonitorDO>()
                .eq(RealTimeMonitorDO::getAlarmStatus, alarmStatus));
    }

    default Long selectCountByHandleResultIsNotNull() {
        return selectCount(new LambdaQueryWrapperX<RealTimeMonitorDO>()
                .isNotNull(RealTimeMonitorDO::getHandleResult));
    }

    List<RealTimeMonitorChartRespVO.AreaStat> selectAreaStatList();

    List<RealTimeMonitorChartRespVO.CameraMapItem> selectCameraMapList();

}