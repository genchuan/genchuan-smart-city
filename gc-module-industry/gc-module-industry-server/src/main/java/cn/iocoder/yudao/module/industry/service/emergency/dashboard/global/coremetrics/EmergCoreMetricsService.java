package cn.iocoder.yudao.module.industry.service.emergency.dashboard.global.coremetrics;


import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.coremetrics.vo.EmergCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.coremetrics.vo.EmergCoreMetricsRespVO;

/**
 * 应急核心指标 Service 接口
 *
 * @author lxs
 */
public interface EmergCoreMetricsService {

        /**
         * 查询应急核心指标
         *
         * @param emergCoreMetricsQueryReqVO 查询条件
         * @return 查询结果
         */
        EmergCoreMetricsRespVO getEmergCoreMetrics(EmergCoreMetricsQueryReqVO emergCoreMetricsQueryReqVO);
//        EmergCoreMetricsRespVO getEmergCoreMetrics2(EmergCoreMetricsQueryReqVO emergCoreMetricsQueryReqVO);
}
