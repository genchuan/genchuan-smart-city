package cn.iocoder.yudao.module.industry.dal.mysql.emergency.dashboard.global.coremetrics;


import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.coremetrics.vo.EmergCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.coremetrics.vo.EmergCoreMetricsRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.coremetrics.vo.TimeValuePoint;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

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

    EmergCoreMetricsRespVO.EmergEventCompleteRate getEmergEventCompleteRate(EmergCoreMetricsQueryReqVO emergCoreMetricsQueryReqVO);

    List<TimeValuePoint> getEmergEventCompleteRateTrendList(EmergCoreMetricsQueryReqVO todayReq);
    List<TimeValuePoint> getEarlyWarnAccRateTrendList(EmergCoreMetricsQueryReqVO reqVO);

    /**
     * 查询资源调用率趋势列表
     * @param reqVO 查询条件，包含开始时间和结束时间
     * @return 按日期排列的资源调用率趋势列表
     */
    List<TimeValuePoint> getResUseRateTrendList(EmergCoreMetricsQueryReqVO reqVO);

    /**
     * 查询风险整改率趋势列表
     * @param reqVO 查询条件，包含开始时间和结束时间
     * @return 按日期排列的风险整改率趋势列表
     */
    List<TimeValuePoint> getRiskRectifyRateTrendList(EmergCoreMetricsQueryReqVO reqVO);
}
