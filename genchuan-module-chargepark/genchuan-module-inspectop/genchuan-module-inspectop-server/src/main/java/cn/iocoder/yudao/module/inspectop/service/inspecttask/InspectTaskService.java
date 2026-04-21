package cn.iocoder.yudao.module.inspectop.service.inspecttask;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.inspecttask.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspecttask.InspectTaskDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 巡检任务 Service 接口
 *
 * @author zhucongquan
 */
public interface InspectTaskService {

    /**
     * 创建巡检任务
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectTask(@Valid InspectTaskSaveReqVO createReqVO);

    /**
     * 更新巡检任务
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectTask(@Valid InspectTaskSaveReqVO updateReqVO);

    /**
     * 删除巡检任务
     *
     * @param id 编号
     */
    void deleteInspectTask(Long id);

    /**
    * 批量删除巡检任务
    *
    * @param ids 编号
    */
    void deleteInspectTaskListByIds(List<Long> ids);

    /**
     * 获得巡检任务
     *
     * @param id 编号
     * @return 巡检任务
     */
    InspectTaskDO getInspectTask(Long id);

    /**
     * 获得巡检任务分页（带关联查询）
     * 通过关联 inspect_plan 表查询计划名称
     * 通过关联 inspect_user 表查询人员姓名
     *
     * @param pageReqVO 分页查询
     * @return 巡检任务分页（包含计划名称和人员姓名）
     */
    PageResult<InspectTaskRespVO> getInspectTaskPage(InspectTaskPageReqVO pageReqVO);

    /**
     * 批量派发巡检任务
     * 将多个任务派发给指定的巡检人员
     *
     * @param batchDispatchReqVO 批量派发请求参数
     */
    void batchDispatchInspectTask(@Valid InspectTaskBatchDispatchReqVO batchDispatchReqVO);

    /**
     * 认领巡检任务
     * 将任务状态更新为"处理中"（字典值3）并设置认领时间
     *
     * @param claimReqVO 认领请求参数
     */
    void claimInspectTask(@Valid InspectTaskClaimReqVO claimReqVO);

    /**
     * 更新巡检任务进度
     *
     * @param updateProgressReqVO 更新进度请求参数
     */
    void updateInspectTaskProgress(@Valid InspectTaskUpdateProgressReqVO updateProgressReqVO);

    /**
     * 转派巡检任务
     * 将任务从一个巡检人员转派给另一个巡检人员
     *
     * @param transferReqVO 转派请求参数
     */
    void transferInspectTask(@Valid InspectTaskTransferReqVO transferReqVO);

    /**
     * 归档巡检任务
     * 将任务的isArchive字段更新为true（1）
     *
     * @param archiveReqVO 归档请求参数
     */
    void archiveInspectTask(@Valid InspectTaskArchiveReqVO archiveReqVO);

    /**
     * 获取巡检任务图表数据
     * 包含任务类型分布、处理时效趋势和卡片统计数据
     *
     * @param timeRange 时间范围
     * @return 图表数据
     */
    InspectTaskChartRespVO getInspectTaskChartData(String[] timeRange);


}