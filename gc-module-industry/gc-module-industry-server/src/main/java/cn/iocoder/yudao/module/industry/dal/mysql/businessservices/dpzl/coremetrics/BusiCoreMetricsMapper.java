package cn.iocoder.yudao.module.industry.dal.mysql.businessservices.dpzl.coremetrics;

import cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.coremetrics.vo.BusiCoreMetricsRespVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface BusiCoreMetricsMapper {

    /**
     * 获取最新审批办结率
     */
    BigDecimal selectLatestApprovalCompleteRate(@Param("statCycle") String statCycle);

    /**
     * 获取最新政策兑现率
     */
    BigDecimal selectLatestPolFulfillRate(@Param("statCycle") String statCycle);

    /**
     * 获取最新企业满意度
     */
    BigDecimal selectLatestEntSatisfy(@Param("statCycle") String statCycle);

    /**
     * 获取最新诉求超期率
     */
    BigDecimal selectLatestAppealOverdueRate(@Param("statCycle") String statCycle);

    /**
     * 获取各区域审批办结率对比
     */
    List<BusiCoreMetricsRespVO.RegionApprovalRateVO> selectRegionApprovalRates(@Param("statCycle") String statCycle);

    /**
     * 获取近30天政策兑现趋势
     */
    List<BusiCoreMetricsRespVO.PolFulfillTrendVO> selectPolFulfillTrendsLast30Days();
}