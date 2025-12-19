package cn.iocoder.yudao.module.industry.service.universal.dashboard.global.coremetrics;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coremetrics.vo.UniversalCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coremetrics.vo.UniversalCoreMetricsRespVO;

/**
 * 通用大屏核心指标看板 Service 接口
 *
 */
public interface UniversalCoreMetricsService {

    /**
     * 查询通用大屏核心指标看板
     *
     * @param universalCoreMetricsQueryReqVO 查询条件
     * @return 查询结果
     */
    UniversalCoreMetricsRespVO getUniversalCoreMetrics(UniversalCoreMetricsQueryReqVO universalCoreMetricsQueryReqVO);
}
