package cn.iocoder.yudao.module.industry.dal.mysql.health.dashboard.global.coremetrics;

import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.coremetrics.vo.HealthCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.coremetrics.vo.HealthCoreMetricsRespVO.TrendPointVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface HealthCoreMetricsMapper {

        // 1. 门诊诊疗人次
        BigDecimal selectOutpatientVolume(@Param("req") HealthCoreMetricsQueryReqVO reqVO);
        List<TrendPointVO> selectOutpatientTrend(@Param("req") HealthCoreMetricsQueryReqVO reqVO);

        // 2. 住院人次
        BigDecimal selectInpatientVolume(@Param("req") HealthCoreMetricsQueryReqVO reqVO);
        List<TrendPointVO> selectInpatientTrend(@Param("req") HealthCoreMetricsQueryReqVO reqVO);

        // 3. 家庭医生签约率
        BigDecimal selectFamilyDoctorSignRate(@Param("req") HealthCoreMetricsQueryReqVO reqVO);
        List<TrendPointVO> selectFamilyDoctorTrend(@Param("req") HealthCoreMetricsQueryReqVO reqVO);

        // 4. 疫苗接种完成率
        BigDecimal selectVaccineCompleteRate(@Param("req") HealthCoreMetricsQueryReqVO reqVO);
        List<TrendPointVO> selectVaccineTrend(@Param("req") HealthCoreMetricsQueryReqVO reqVO);

        // 5. 传染病报告及时率
        BigDecimal selectInfectRptTimelyRate(@Param("req") HealthCoreMetricsQueryReqVO reqVO);
        List<TrendPointVO> selectInfectTrend(@Param("req") HealthCoreMetricsQueryReqVO reqVO);
}
