package cn.iocoder.yudao.module.vehiclepass.dal.mysql.passreport.cyclereport;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport.vo.CycleReportPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.passreport.cyclereport.CycleReportDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface CycleReportMapper extends BaseMapperX<CycleReportDO> {

    IPage<CycleReportDO> selectPageJoin(Page<?> page, @Param("reqVO") CycleReportPageReqVO reqVO);

    CycleReportDO selectByIdWithStation(@Param("id") Long id);

    String selectStationName(@Param("id") Long id);

    Integer selectEnterCount(@Param("stationId") Long stationId,
                             @Param("startTime") LocalDateTime startTime,
                             @Param("endTime") LocalDateTime endTime,
                             @Param("tenantId") Long tenantId);

    Integer selectLeaveCount(@Param("stationId") Long stationId,
                             @Param("startTime") LocalDateTime startTime,
                             @Param("endTime") LocalDateTime endTime,
                             @Param("tenantId") Long tenantId);

    Integer selectParkingCount(@Param("stationId") Long stationId,
                               @Param("endTime") LocalDateTime endTime,
                               @Param("tenantId") Long tenantId);

    BigDecimal selectIdentifySuccessRate(@Param("stationId") Long stationId,
                                         @Param("startTime") LocalDateTime startTime,
                                         @Param("endTime") LocalDateTime endTime,
                                         @Param("tenantId") Long tenantId);

    BigDecimal selectCheckSuccessRate(@Param("stationId") Long stationId,
                                      @Param("startTime") LocalDateTime startTime,
                                      @Param("endTime") LocalDateTime endTime,
                                      @Param("tenantId") Long tenantId);

    BigDecimal selectAbnormalHandleRate(@Param("stationId") Long stationId,
                                        @Param("startTime") LocalDateTime startTime,
                                        @Param("endTime") LocalDateTime endTime,
                                        @Param("tenantId") Long tenantId);

    BigDecimal selectEtcPassSuccessRate(@Param("stationId") Long stationId,
                                        @Param("startTime") LocalDateTime startTime,
                                        @Param("endTime") LocalDateTime endTime,
                                        @Param("tenantId") Long tenantId);

    List<CycleReportDO> selectChartByConditions(@Param("stationId") Long stationId,
                                                @Param("statTime") LocalDateTime statTime,
                                                @Param("tenantId") Long tenantId,
                                                @Param("reportCycle") String reportCycle);

    List<Map<String, Object>> selectMapData(@Param("stationId") Long stationId,
                                            @Param("statTime") LocalDateTime statTime,
                                            @Param("tenantId") Long tenantId,
                                            @Param("reportCycle") String reportCycle);

    List<Map<String, Object>> selectBarData(@Param("stationId") Long stationId,
                                            @Param("statTime") LocalDateTime statTime,
                                            @Param("tenantId") Long tenantId,
                                            @Param("reportCycle") String reportCycle);

    List<Map<String, Object>> selectLineData(@Param("stationId") Long stationId,
                                             @Param("statTime") LocalDateTime statTime,
                                             @Param("tenantId") Long tenantId,
                                             @Param("reportCycle") String reportCycle);

    List<Map<String, Object>> selectPieData(@Param("stationId") Long stationId,
                                            @Param("statTime") LocalDateTime statTime,
                                            @Param("tenantId") Long tenantId,
                                            @Param("reportCycle") String reportCycle);

}