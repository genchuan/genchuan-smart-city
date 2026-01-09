package cn.iocoder.yudao.module.industry.service.businessservices.dpzl.coremetrics;

import cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.coremetrics.vo.BusiCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.coremetrics.vo.BusiCoreMetricsRespVO;

public interface BusiCoreMetricsService {

    /**
     * 获取关键指标展示数据
     *
     * @param queryReqVO 查询参数
     * @return 关键指标数据
     */
    BusiCoreMetricsRespVO getKeyIndicatorsData(BusiCoreMetricsQueryReqVO queryReqVO);
}