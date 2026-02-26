package cn.iocoder.yudao.module.evaluate.service.taskstatus;

import cn.iocoder.yudao.module.evaluate.controller.admin.sys.taskstatus.vo.TaskStatusPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.taskstatus.vo.TaskStatusSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.taskstatus.TaskStatusDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 任务状态字典 Service 接口
 *
 * @author 亘川智城
 */
public interface TaskStatusService {

    /**
     * 创建任务状态字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTaskStatus(@Valid TaskStatusSaveReqVO createReqVO);

    /**
     * 更新任务状态字典
     *
     * @param updateReqVO 更新信息
     */
    void updateTaskStatus(@Valid TaskStatusSaveReqVO updateReqVO);

    /**
     * 删除任务状态字典
     *
     * @param id 编号
     */
    void deleteTaskStatus(Long id);

    /**
     * 获得任务状态字典
     *
     * @param id 编号
     * @return 任务状态字典
     */
    TaskStatusDO getTaskStatus(Long id);

    /**
     * 获得任务状态字典分页
     *
     * @param pageReqVO 分页查询
     * @return 任务状态字典分页
     */
    PageResult<TaskStatusDO> getTaskStatusPage(TaskStatusPageReqVO pageReqVO);

}