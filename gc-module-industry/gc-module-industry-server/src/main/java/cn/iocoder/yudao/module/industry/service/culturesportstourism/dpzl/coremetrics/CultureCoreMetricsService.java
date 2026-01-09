package cn.iocoder.yudao.module.industry.service.culturesportstourism.dpzl.coremetrics;

import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.coreindicators.vo.CultureCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.coreindicators.vo.CultureCoreMetricsRespVO;

public interface CultureCoreMetricsService {

    /**
     * 获取文旅核心指标数据
     * @param queryVO 查询参数（包含时间周期）
     * @return 核心指标数据
     */
    CultureCoreMetricsRespVO getCoreIndicators(CultureCoreMetricsQueryReqVO queryVO);
}