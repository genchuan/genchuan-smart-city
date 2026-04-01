package cn.iocoder.yudao.module.vehiclecharging.dal.mysql.statusmonitor;

import java.util.*;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.tenant.core.aop.TenantIgnore;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.chart.AbnormalPoint;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.chart.ParamTrend;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.chart.StatusMonitorChartRespVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.newvo.StatusMonitorExportReqVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.newvo.StatusMonitorRefreshReqVO;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.statusmonitor.StatusMonitorDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.*;

/**
 * 实时监测 Mapper
 *
 * @author 亘川智城
 */
@Mapper
//@TenantIgnore // 添加这一行
public interface StatusMonitorMapper extends BaseMapperX<StatusMonitorDO> {

//    default PageResult<StatusMonitorDO> selectPage(StatusMonitorPageReqVO reqVO) {
//        return selectPage(reqVO, new LambdaQueryWrapperX<StatusMonitorDO>()
//                .eqIfPresent(StatusMonitorDO::getDeviceCode, reqVO.getDeviceCode())
//                .eqIfPresent(StatusMonitorDO::getStationId, reqVO.getStationId())
//                .eqIfPresent(StatusMonitorDO::getLotId, reqVO.getLotId())
//                .eqIfPresent(StatusMonitorDO::getDeviceType, reqVO.getDeviceType())
//                .eqIfPresent(StatusMonitorDO::getVoltage, reqVO.getVoltage())
//                .eqIfPresent(StatusMonitorDO::getCurrent, reqVO.getCurrent())
//                .eqIfPresent(StatusMonitorDO::getPower, reqVO.getPower())
//                .eqIfPresent(StatusMonitorDO::getAlarmLevel, reqVO.getAlarmLevel())
//                .eqIfPresent(StatusMonitorDO::getMonitorStatus, reqVO.getMonitorStatus())
//                .eqIfPresent(StatusMonitorDO::getDisposeUser, reqVO.getDisposeUser())
//                .eqIfPresent(StatusMonitorDO::getDisposeMeasure, reqVO.getDisposeMeasure())
//                .betweenIfPresent(StatusMonitorDO::getDisposeTime, reqVO.getDisposeTime())
//                .betweenIfPresent(StatusMonitorDO::getMonitorTime, reqVO.getMonitorTime())
//                .eqIfPresent(StatusMonitorDO::getRemark, reqVO.getRemark())
//                .eqIfPresent(StatusMonitorDO::getReserve1, reqVO.getReserve1())
//                .eqIfPresent(StatusMonitorDO::getReserve2, reqVO.getReserve2())
//                .betweenIfPresent(StatusMonitorDO::getCreateTime, reqVO.getCreateTime())
//                .orderByDesc(StatusMonitorDO::getId));
//    }
    List<StatusMonitorRespVO> selectPage(StatusMonitorPageReqVO reqVO);

    long selectPageCount(StatusMonitorPageReqVO reqVO);
    List<StatusMonitorDO> selectRefreshList(StatusMonitorRefreshReqVO reqVO);
    List<StatusMonitorDO> selectExportList(StatusMonitorExportReqVO reqVO);


    StatusMonitorChartRespVO selectCardStats();

    List<ParamTrend> selectParamTrend();

    List<AbnormalPoint> selectAbnormalPoints();
}
