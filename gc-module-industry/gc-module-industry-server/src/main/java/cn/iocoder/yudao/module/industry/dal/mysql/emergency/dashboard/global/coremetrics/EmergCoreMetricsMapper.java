package cn.iocoder.yudao.module.industry.dal.mysql.emergency.dashboard.global.coremetrics;


import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.coremetrics.vo.EmergCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.coremetrics.vo.EmergCoreMetricsRespVO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

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

        /**
         * 查询应急核心指标的最新更新时间（updateTime）
         *
         * 逻辑说明：
         *  1. 分别查询四个模块的最大时间：
         *     - stat_mon_evt_rpt.stat_time         → 应急事件模块
         *     - stat_early_warn_region.stat_time  → 预警模块
         *     - biz_emerg_res.update_time          → 应急资源模块
         *     - biz_risk_hazard.discover_time     → 风险隐患模块
         *  2. 使用 SQL GREATEST() 获取四个最大值作为整体更新时间。
         *  3. 如果某个模块没有数据，对应 MAX() 返回 null。
         *  4. 如果所有模块都没有数据，GREATEST 返回 null。
         *
         * @param emergCoreMetricsQueryReqVO 查询参数
         * @return 最新更新时间，如果无数据则返回 null
         */
        LocalDateTime getEmergCoreMetricsUpdateTime(EmergCoreMetricsQueryReqVO emergCoreMetricsQueryReqVO);

}
