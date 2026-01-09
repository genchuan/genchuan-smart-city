package cn.iocoder.yudao.module.industry.dal.mysql.universal.dashboard.global.coremetrics;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coremetrics.vo.UniversalCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coremetrics.vo.UniversalCoreMetricsRespVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 通用大屏核心指标看板 Mapper
 *
 */
@Mapper
public interface UniversalCoreMetricsMapper {

    /**
     * 查询通用大屏核心指标看板
     *
     * @param universalCoreMetricsQueryReqVO 查询参数
     * @return UniversalCoreMetricsRespVO 结果
     */
    UniversalCoreMetricsRespVO getUniversalCoreMetrics(UniversalCoreMetricsQueryReqVO universalCoreMetricsQueryReqVO);

}
