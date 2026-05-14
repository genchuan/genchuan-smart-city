package cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.stationreport;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo.ops.StationOpReportChartReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo.ops.StationOpReportChartRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo.ops.StationOpReportCreateReqVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationmgmt.stationinfo.StationInfoDO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationreport.StationReportDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface StationReportMapper extends BaseMapperX<StationReportDO> {
    // ====================== 1. area_info 片区统计 ======================
    Map<String, Object> selectAreaReport(StationOpReportCreateReqVO reqVO);

    // ====================== 2. station_info 场站统计 ======================
    Map<String, Object> selectStationReport(StationOpReportCreateReqVO reqVO);

    // ====================== 3. parking_space_info 车位统计 ======================
    Map<String, Object> selectSpaceReport(StationOpReportCreateReqVO reqVO);

    // ====================== 4. 生效规则统计 ======================
    Map<String, Object> selectEffectiveRuleReport(StationOpReportCreateReqVO reqVO);

    // ====================== 5. 充停联动 订单+营收统计 ======================
    Map<String, Object> selectChargeParkOrderReport(StationOpReportCreateReqVO reqVO);

    // ====================== 6. debt_expand 追缴完成率 ======================
    Map<String, Object> selectDebtExpandReport(StationOpReportCreateReqVO reqVO);

    // ====================== 7. deposit_plan 押金订单量 ======================
    Map<String, Object> selectDepositPlanReport(StationOpReportCreateReqVO reqVO);


    // ====================== 8. 地图数据 ======================
    List<StationOpReportChartRespVO.MapData> selectMapData(StationOpReportChartReqVO reqVO);

    // ====================== 9. 柱状图数据 ======================
    List<StationOpReportChartRespVO.BarData> selectBarData(StationOpReportChartReqVO reqVO);

    // ====================== 10. 折线图数据 ======================
    List<StationOpReportChartRespVO.LineData> selectLineData(StationOpReportChartReqVO reqVO);

    List<StationOpReportChartRespVO.StationMapData> getStationMapData(StationOpReportChartReqVO reqVO);

    List<StationOpReportChartRespVO.ParkSpaceMapData> getParkSpaceMapData(StationOpReportChartReqVO reqVO);

    List<StationOpReportChartRespVO.StationTypeBarData> selectStationTypeBarData(StationOpReportChartReqVO reqVO);

    List<StationOpReportChartRespVO.StationOrderCountBarData> selectStationOrderCountBarData(StationOpReportChartReqVO reqVO);

    List<StationOpReportChartRespVO.StationRecoverFinishBarData> selectStationRecoverFinishBarData(StationOpReportChartReqVO reqVO);

    // ====================== 钻取查询（按卡片指标下钻到明细数据） ======================

    /** 总片区数 → 钻取片区列表 */
    List<Map<String, Object>> drillDownAreaList(@Param("startTime") LocalDateTime startTime,
                                                @Param("endTime") LocalDateTime endTime);

    /** 总站场数 → 钻取场站列表 */
    List<Map<String, Object>> drillDownStationList(@Param("startTime") LocalDateTime startTime,
                                                   @Param("endTime") LocalDateTime endTime);

    /** 覆盖场站数 → 钻取有归属片区的场站列表 */
    List<Map<String, Object>> drillDownCoverStationList(@Param("startTime") LocalDateTime startTime,
                                                        @Param("endTime") LocalDateTime endTime);

    /** 正常运营数 → 钻取运营中场站列表 */
    List<Map<String, Object>> drillDownNormalStationList(@Param("startTime") LocalDateTime startTime,
                                                         @Param("endTime") LocalDateTime endTime);

    /** 总车位数 → 钻取车位列表 */
    List<Map<String, Object>> drillDownSpaceList(@Param("startTime") LocalDateTime startTime,
                                                 @Param("endTime") LocalDateTime endTime);

    /** 可用车位数 → 钻取空闲车位列表 */
    List<Map<String, Object>> drillDownAvailableSpaceList(@Param("startTime") LocalDateTime startTime,
                                                          @Param("endTime") LocalDateTime endTime);

    /** 生效规则数 → 钻取规则列表 */
    List<Map<String, Object>> drillDownEffectiveRuleList(@Param("startTime") LocalDateTime startTime,
                                                         @Param("endTime") LocalDateTime endTime);

    /** 订单量/营收 → 钻取充停联动订单列表 */
    List<Map<String, Object>> drillDownOrderList(@Param("startTime") LocalDateTime startTime,
                                                 @Param("endTime") LocalDateTime endTime);

    /** 追缴完成率 → 钻取追缴记录列表 */
    List<Map<String, Object>> drillDownDebtExpandList(@Param("startTime") LocalDateTime startTime,
                                                      @Param("endTime") LocalDateTime endTime);

    /** 押金订单量 → 钻取押金计划列表 */
    List<Map<String, Object>> drillDownDepositPlanList(@Param("startTime") LocalDateTime startTime,
                                                       @Param("endTime") LocalDateTime endTime);
}
