package cn.iocoder.yudao.module.industry.service.lawenf.dashboard.global.coremetrics;


import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.coremetrics.vo.LawCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.coremetrics.vo.LawCoreMetricsRespVO;

/**
 * 执法核心指标 Service 接口
 *
 * @author lxs
 */
public interface LawCoreMetricsService {

        /**
         * 查询执法核心指标
         *
         * @param lawCoreMetricsQueryReqVO 查询条件
         * @return 查询结果
         */
        LawCoreMetricsRespVO getLawCoreMetrics2(LawCoreMetricsQueryReqVO lawCoreMetricsQueryReqVO);

        /**
         * 查询执法核心指标
         *
         * @param lawCoreMetricsQueryReqVO 查询条件
         * @return 查询结果
         */
        LawCoreMetricsRespVO getLawCoreMetrics(LawCoreMetricsQueryReqVO lawCoreMetricsQueryReqVO);
}
