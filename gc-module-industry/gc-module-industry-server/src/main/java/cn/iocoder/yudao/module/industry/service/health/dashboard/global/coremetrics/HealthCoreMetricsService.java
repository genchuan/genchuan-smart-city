package cn.iocoder.yudao.module.industry.service.health.dashboard.global.coremetrics;


import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.coremetrics.vo.HealthCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.coremetrics.vo.HealthCoreMetricsRespVO;

/**
 * 卫健核心指标 Service 接口
 *
 * @author lxs
 */
public interface HealthCoreMetricsService {

        /**
         * 查询卫健核心指标
         *
         * @param healthCoreMetricsQueryReqVO 查询条件
         * @return 查询结果
         */
        HealthCoreMetricsRespVO getHealthCoreMetrics(HealthCoreMetricsQueryReqVO healthCoreMetricsQueryReqVO);
}
