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
import java.time.LocalDateTime;

@Mapper
public interface CycleReportMapper extends BaseMapperX<CycleReportDO> {

    IPage<CycleReportDO> selectPageJoin(Page<?> page, @Param("reqVO") CycleReportPageReqVO reqVO);

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

}