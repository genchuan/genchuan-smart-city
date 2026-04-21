package cn.iocoder.yudao.module.vehiclecharging.dal.mysql.sharingreport;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingreport.vo.SharingReportCooperatorRatioRespVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingreport.vo.SharingReportSummaryRespVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingreport.vo.SharingReportTimeTrendRespVO;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.sharingreport.SharingReportDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 分账报表 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface SharingReportMapper extends BaseMapperX<SharingReportDO> {

    Map<String, Object> selectTotalStats(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    List<SharingReportSummaryRespVO.LineData> selectLineData(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("groupPattern") String groupPattern);

    List<SharingReportSummaryRespVO.BarData> selectBarData(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    List<SharingReportSummaryRespVO.PieData> selectPieData(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    BigDecimal selectTotalSharingAmount(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    List<SharingReportTimeTrendRespVO.TimeTrendData> selectTimeTrend(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("groupPattern") String groupPattern);

    List<Map<String, Object>> selectCooperatorTimeAmount(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("groupPattern") String groupPattern);

    List<SharingReportCooperatorRatioRespVO.CooperatorRatioData> selectCooperatorRatio(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    Integer countGroupBy(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("pattern") String pattern);
}
