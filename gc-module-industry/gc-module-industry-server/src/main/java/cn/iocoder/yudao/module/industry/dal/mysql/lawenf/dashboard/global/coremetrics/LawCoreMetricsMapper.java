package cn.iocoder.yudao.module.industry.dal.mysql.lawenf.dashboard.global.coremetrics;


import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.coremetrics.vo.CoreMetricVO;
import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.coremetrics.vo.LawCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.coremetrics.vo.LawCoreMetricsRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.coremetrics.vo.TreatTrendVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 执法核心指标 Mapper
 *
 * @author lxs
 */
@Mapper
public interface LawCoreMetricsMapper {

        /**
         * 查询执法核心指标
         *
         * @param lawCoreMetricsQueryReqVO 查询参数
         * @return LawCoreMetricsRespVO 结果
         */
        LawCoreMetricsRespVO getLawCoreMetrics2(LawCoreMetricsQueryReqVO lawCoreMetricsQueryReqVO);

//        LawCoreMetricsRespVO getLawCoreMetrics2(LawCoreMetricsQueryReqVO lawCoreMetricsQueryReqVO);

        // ===============================
        // 1. 平均案件办结时长
        // ===============================
        CoreMetricVO getAvgCaseHandleTime(LawCoreMetricsQueryReqVO lawCoreMetricsQueryReqVO);

        List<TreatTrendVO> getAvgCaseHandleTimeTrendVOList(LawCoreMetricsQueryReqVO lawCoreMetricsQueryReqVO);

        // ===============================
        // 2. 执法合规率
        // ===============================
        CoreMetricVO getLawComplianceRate(LawCoreMetricsQueryReqVO lawCoreMetricsQueryReqVO);

        List<TreatTrendVO> getLawComplianceRateTrendVOList(LawCoreMetricsQueryReqVO lawCoreMetricsQueryReqVO);

        // ===============================
        // 3. 重复举报率
        // ===============================
        CoreMetricVO getRepeatRptRate(LawCoreMetricsQueryReqVO lawCoreMetricsQueryReqVO);

        List<TreatTrendVO> getRepeatRptRateTrendVOList(LawCoreMetricsQueryReqVO lawCoreMetricsQueryReqVO);

        // ===============================
        // 4. 跨部门协同率
        // ===============================
        CoreMetricVO getCrossDeptCoopRate(LawCoreMetricsQueryReqVO lawCoreMetricsQueryReqVO);

        List<TreatTrendVO> getCrossDeptCoopRateTrendVOList(LawCoreMetricsQueryReqVO lawCoreMetricsQueryReqVO);
}
