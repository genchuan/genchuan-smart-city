package cn.iocoder.yudao.module.vehiclepass.dal.mysql.inspectmgmt.inspecttask;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.CheckTaskPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.CheckTaskRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.InspectTaskChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.InspectTaskChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.inspectmgmt.inspecttask.CheckTaskDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 稽查任务 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface CheckTaskMapper extends BaseMapperX<CheckTaskDO> {

    default PageResult<CheckTaskDO> selectPage(CheckTaskPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CheckTaskDO>()
                .eqIfPresent(CheckTaskDO::getTaskType, reqVO.getTaskType())
                .betweenIfPresent(CheckTaskDO::getDispatchTime, reqVO.getDispatchTime())
                .betweenIfPresent(CheckTaskDO::getDeadlineTime, reqVO.getDeadlineTime())
                .eqIfPresent(CheckTaskDO::getStatus, reqVO.getStatus())
                .eqIfPresent(CheckTaskDO::getAreaId, reqVO.getAreaId())
                .eqIfPresent(CheckTaskDO::getExecuteUserId, reqVO.getExecuteUserId())
                .betweenIfPresent(CheckTaskDO::getFinishTime, reqVO.getFinishTime())
                .eqIfPresent(CheckTaskDO::getTaskProgress, reqVO.getTaskProgress())
                .eqIfPresent(CheckTaskDO::getTransferReason, reqVO.getTransferReason())
                .eqIfPresent(CheckTaskDO::getRemark, reqVO.getRemark())
                .eqIfPresent(CheckTaskDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(CheckTaskDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(CheckTaskDO::getCreator, reqVO.getCreator())
                .eqIfPresent(CheckTaskDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(CheckTaskDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(CheckTaskDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(CheckTaskDO::getId));
    }

    /**
     * 分页查询（使用JOIN查询关联表）
     */
    IPage<CheckTaskRespVO> selectPageJoin(Page<?> page, @Param("reqVO") CheckTaskPageReqVO reqVO);

    /**
     * 统计任务类型分布
     */
    List<InspectTaskChartRespVO.TaskTypeCount> selectTaskTypeCount(@Param("reqVO") InspectTaskChartReqVO reqVO);

    /**
     * 统计任务处理时效趋势
     */
    List<InspectTaskChartRespVO.TaskHandleTrend> selectTaskHandleTrend(@Param("reqVO") InspectTaskChartReqVO reqVO);

    /**
     * 统计待处理任务数
     */
    Long selectWaitHandleTaskCount(@Param("reqVO") InspectTaskChartReqVO reqVO);

    /**
     * 统计已完成任务数
     */
    Long selectFinishedTaskCount(@Param("reqVO") InspectTaskChartReqVO reqVO);

}