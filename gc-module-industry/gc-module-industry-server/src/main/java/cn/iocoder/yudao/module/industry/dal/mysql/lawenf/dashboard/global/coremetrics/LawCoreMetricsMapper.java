package cn.iocoder.yudao.module.industry.dal.mysql.lawenf.dashboard.global.coremetrics;


import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.coremetrics.vo.LawCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.coremetrics.vo.LawCoreMetricsRespVO;
import org.apache.ibatis.annotations.Mapper;
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
        LawCoreMetricsRespVO getLawCoreMetrics(LawCoreMetricsQueryReqVO lawCoreMetricsQueryReqVO);

}
