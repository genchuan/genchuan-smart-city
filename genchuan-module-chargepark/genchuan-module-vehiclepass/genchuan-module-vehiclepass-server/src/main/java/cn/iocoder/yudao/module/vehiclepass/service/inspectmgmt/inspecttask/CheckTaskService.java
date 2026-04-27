package cn.iocoder.yudao.module.vehiclepass.service.inspectmgmt.inspecttask;

import java.util.*;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.CheckTaskPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.CheckTaskSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.CheckTaskRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.InspectTaskBatchDispatchReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.InspectTaskDispatchReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.InspectTaskClaimReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.InspectTaskUpdateProgressReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.InspectTaskTransferReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.InspectTaskArchiveReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.InspectTaskChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.InspectTaskChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.inspectmgmt.inspecttask.CheckTaskDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 稽查任务 Service 接口
 *
 * @author 亘川智城
 */
public interface CheckTaskService {

    /**
     * 创建稽查任务
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTask(@Valid CheckTaskSaveReqVO createReqVO);

    /**
     * 更新稽查任务
     *
     * @param updateReqVO 更新信息
     */
    void updateTask(@Valid CheckTaskSaveReqVO updateReqVO);

    /**
     * 删除稽查任务
     *
     * @param id 编号
     */
    void deleteTask(Long id);

    /**
     * 批量删除稽查任务
     *
     * @param ids 编号
     */
    void deleteTaskListByIds(List<Long> ids);

    /**
     * 获得稽查任务
     *
     * @param id 编号
     * @return 稽查任务
     */
    CheckTaskDO getTask(Long id);

    /**
     * 获得稽查任务分页
     *
     * @param pageReqVO 分页查询
     * @return 稽查任务分页
     */
    PageResult<CheckTaskDO> getTaskPage(CheckTaskPageReqVO pageReqVO);

    /**
     * 获得稽查任务分页（使用JOIN查询）
     *
     * @param pageReqVO 分页查询
     * @return 稽查任务分页（含关联表字段）
     */
    PageResult<CheckTaskRespVO> getTaskPageWithJoin(CheckTaskPageReqVO pageReqVO);

    /**
     * 批量派发稽查任务
     *
     * @param reqVO 批量派发请求
     */
    void batchDispatch(InspectTaskBatchDispatchReqVO reqVO);

    /**
     * 派发稽查任务
     *
     * @param reqVO 派发请求
     */
    void dispatch(InspectTaskDispatchReqVO reqVO);

    /**
     * 认领稽查任务
     *
     * @param id 任务ID
     */
    void claim(Long id);

    /**
     * 更新稽查任务进度
     *
     * @param reqVO 更新进度请求
     */
    void updateProgress(InspectTaskUpdateProgressReqVO reqVO);

    /**
     * 转派稽查任务
     *
     * @param reqVO 转派请求
     */
    void transfer(InspectTaskTransferReqVO reqVO);

    /**
     * 归档稽查任务
     *
     * @param id 任务ID
     */
    void archive(Long id);

    /**
     * 获取稽查任务统计
     *
     * @param reqVO 统计请求
     * @return 统计结果
     */
    InspectTaskChartRespVO getChart(InspectTaskChartReqVO reqVO);

}