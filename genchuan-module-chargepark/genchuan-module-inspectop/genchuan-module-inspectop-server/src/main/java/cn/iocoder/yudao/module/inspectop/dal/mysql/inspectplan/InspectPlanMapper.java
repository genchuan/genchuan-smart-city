package cn.iocoder.yudao.module.inspectop.dal.mysql.inspectplan;

import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspectplan.InspectPlanDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.inspectop.controller.admin.inspectplan.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 巡检计划 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface InspectPlanMapper extends BaseMapperX<InspectPlanDO> {

    default PageResult<InspectPlanDO> selectPage(InspectPlanPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectPlanDO>()
                .likeIfPresent(InspectPlanDO::getName, reqVO.getName())
                .eqIfPresent(InspectPlanDO::getType, reqVO.getType())
                .eqIfPresent(InspectPlanDO::getScope, reqVO.getScope())
                .eqIfPresent(InspectPlanDO::getCycle, reqVO.getCycle())
                .eqIfPresent(InspectPlanDO::getDescription, reqVO.getDescription())
                .eqIfPresent(InspectPlanDO::getStatus, reqVO.getStatus())
                .eqIfPresent(InspectPlanDO::getProgress, reqVO.getProgress())
                .eqIfPresent(InspectPlanDO::getAuditUserId, reqVO.getAuditUserId())
                .betweenIfPresent(InspectPlanDO::getEffectTime, reqVO.getEffectTime())
                .betweenIfPresent(InspectPlanDO::getFinishTime, reqVO.getFinishTime())
                .eqIfPresent(InspectPlanDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(InspectPlanDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(InspectPlanDO::getCreator, reqVO.getCreator())
                .eqIfPresent(InspectPlanDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(InspectPlanDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(InspectPlanDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(InspectPlanDO::getId));
    }

    /**
     * 查询计划执行趋势数据
     *
     * @param timeRange 时间范围
     * @return 趋势数据列表
     */
    List<InspectPlanChartRespVO.TrendData> selectTrendData(@Param("timeRange") LocalDateTime[] timeRange);

    /**
     * 查询计划类型分布数据
     *
     * @param timeRange 时间范围
     * @return 类型分布数据列表
     */
    List<InspectPlanChartRespVO.TypeData> selectTypeData(@Param("timeRange") LocalDateTime[] timeRange);

    /**
     * 查询卡片统计数据
     *
     * @param timeRange 时间范围
     * @return 卡片统计数据
     */
    InspectPlanChartRespVO.CardData selectCardData(@Param("timeRange") LocalDateTime[] timeRange);

}