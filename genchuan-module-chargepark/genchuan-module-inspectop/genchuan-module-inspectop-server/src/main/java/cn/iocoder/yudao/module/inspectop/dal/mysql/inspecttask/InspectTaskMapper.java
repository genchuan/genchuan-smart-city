package cn.iocoder.yudao.module.inspectop.dal.mysql.inspecttask;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspecttask.InspectTaskDO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.inspectop.controller.admin.inspecttask.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 巡检任务 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface InspectTaskMapper extends BaseMapperX<InspectTaskDO> {

    default PageResult<InspectTaskDO> selectPage(InspectTaskPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectTaskDO>()
                .eqIfPresent(InspectTaskDO::getPlanId, reqVO.getPlanId())
                .eqIfPresent(InspectTaskDO::getUserId, reqVO.getUserId())
                .betweenIfPresent(InspectTaskDO::getDispatchTime, reqVO.getDispatchTime())
                .betweenIfPresent(InspectTaskDO::getClaimTime, reqVO.getClaimTime())
                .eqIfPresent(InspectTaskDO::getStatus, reqVO.getStatus())
                .eqIfPresent(InspectTaskDO::getProgress, reqVO.getProgress())
                .eqIfPresent(InspectTaskDO::getIsArchive, reqVO.getIsArchive())
                .eqIfPresent(InspectTaskDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(InspectTaskDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(InspectTaskDO::getCreator, reqVO.getCreator())
                .eqIfPresent(InspectTaskDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(InspectTaskDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(InspectTaskDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(InspectTaskDO::getId));
    }

    /**
     * 关联查询分页方法
     * 通过关联 inspect_plan 表查询计划名称
     * 通过关联 inspect_user 表查询人员姓名
     *
     * @param page  MyBatis-Plus分页参数
     * @param reqVO 查询条件
     * @return 包含计划名称和人员姓名的分页结果
     */
    Page<InspectTaskRespVO> selectPageWithJoin(@Param("page") Page<InspectTaskRespVO> page,
                                               @Param("reqVO") InspectTaskPageReqVO reqVO);

    /**
     * 根据ID列表查询任务
     *
     * @param ids 任务ID列表
     * @return 任务列表
     */
    default List<InspectTaskDO> selectListByIds(List<Long> ids) {
        return selectList(new LambdaQueryWrapperX<InspectTaskDO>()
                .in(InspectTaskDO::getId, ids));
    }

    /**
     * 查询任务类型分布数据
     *
     * @param timeRange 时间范围
     * @return 任务类型分布数据列表
     */
    List<InspectTaskChartRespVO.TypeData> selectTaskTypeDistribution(@Param("timeRange") String[] timeRange);

    /**
     * 查询任务处理时效趋势数据
     *
     * @param timeRange 时间范围
     * @return 任务处理时效趋势数据列表
     */
    List<InspectTaskChartRespVO.TrendData> selectTaskTrendData(@Param("timeRange") String[] timeRange);

    /**
     * 查询任务卡片统计数据
     *
     * @return 卡片统计数据
     */
    InspectTaskChartRespVO.CardData selectTaskCardData();

}