package cn.iocoder.yudao.module.vehiclecharging.dal.mysql.pilealarm;

import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.pilealarm.PilealarmDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.pilealarm.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 充电桩告警 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PilealarmMapper extends BaseMapperX<PilealarmDO> {

    default PageResult<PilealarmDO> selectPage(PilealarmPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PilealarmDO>()
                .eqIfPresent(PilealarmDO::getAlarmCode, reqVO.getAlarmCode())
                .eqIfPresent(PilealarmDO::getPileId, reqVO.getPileId())
                .eqIfPresent(PilealarmDO::getStationId, reqVO.getStationId())
                .eqIfPresent(PilealarmDO::getFaultType, reqVO.getFaultType())
                .eqIfPresent(PilealarmDO::getFaultDesc, reqVO.getFaultDesc())
                .eqIfPresent(PilealarmDO::getAlarmLevel, reqVO.getAlarmLevel())
                .betweenIfPresent(PilealarmDO::getAlarmTime, reqVO.getAlarmTime())
                .eqIfPresent(PilealarmDO::getHandlerId, reqVO.getHandlerId())
                .eqIfPresent(PilealarmDO::getHandleHour, reqVO.getHandleHour())
                .eqIfPresent(PilealarmDO::getHandleResult, reqVO.getHandleResult())
                .eqIfPresent(PilealarmDO::getStatus, reqVO.getStatus())
                .eqIfPresent(PilealarmDO::getRemark, reqVO.getRemark())
                .eqIfPresent(PilealarmDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(PilealarmDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(PilealarmDO::getReserve3, reqVO.getReserve3())
                .eqIfPresent(PilealarmDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(PilealarmDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(PilealarmDO::getUpdateBy, reqVO.getUpdateBy())
                .orderByDesc(PilealarmDO::getId));
    }

    /**
     * 充电桩告警 - 新告警列表
     * @param page 分页参数
     * @param reqVO 查询参数
     * @return 告警列表
     */
    IPage<NewPileAlarmRespVO> selectAlarmPage(IPage<NewPileAlarmRespVO> page,
                                              @Param("reqVO") NewPileAlarmPageReqVO reqVO);

    // 图表统计（XML 实现）
    PileAlarmChartRespVO selectAlarmChart(@Param("query") PileAlarmChartReqVO reqVO);

    // 柱状图数据
    List<PileAlarmChartRespVO.BarData> selectBarData(@Param("query") PileAlarmChartReqVO reqVO);

    // 饼图数据
    List<PileAlarmChartRespVO.PieData> selectPieData(@Param("query") PileAlarmChartReqVO reqVO);

    // 日统计
    List<PileAlarmChartRespVO.BarData> selectDailyCount(@Param("query") PileAlarmDailyCountReqVO reqVO);


    /**
     * 统计告警类型占比
     */
    List<PileAlarmTypeRatioRespVO> selectAlarmTypeRatio(
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime,
            @Param("stationId") Long stationId);

    /**
     * 告警处置统计（联表查询）
     */
    PileAlarmHandleCountRespVO selectHandleCount(
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime,
            @Param("stationId") Long stationId);

}