package cn.iocoder.yudao.module.industry.dal.mysql.emergency.dashboard.global.coremetrics;


import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.coremetrics.vo.EmergCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.coremetrics.vo.EmergCoreMetricsRespVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 应急核心指标 Mapper
 *
 * @author lxs
 */
@Mapper
public interface EmergCoreMetricsMapper {

        /**
         * 查询应急核心指标
         *
         * @param emergCoreMetricsQueryReqVO 查询参数
         * @return EmergCoreMetricsRespVO 结果
         */
        EmergCoreMetricsRespVO getEmergCoreMetrics(EmergCoreMetricsQueryReqVO emergCoreMetricsQueryReqVO);

}
