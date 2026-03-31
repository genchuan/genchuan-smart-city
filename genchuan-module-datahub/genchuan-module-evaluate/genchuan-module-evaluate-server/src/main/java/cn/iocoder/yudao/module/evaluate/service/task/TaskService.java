package cn.iocoder.yudao.module.evaluate.service.task;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.task.vo.TaskPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.task.vo.TaskRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.task.vo.TaskSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.task.TaskDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 评价任务 Service 接口
 *
 * @author 芋道源码
 */
public interface TaskService {

    /**
     * 创建评价任务
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTask(@Valid TaskSaveReqVO createReqVO);

    /**
     * 更新评价任务
     *
     * @param updateReqVO 更新信息
     */
    void updateTask(@Valid TaskSaveReqVO updateReqVO);

    /**
     * 删除评价任务
     *
     * @param id 编号
     */
    void deleteTask(Long id);

    /**
    * 批量删除评价任务
    *
    * @param ids 编号
    */
    void deleteTaskListByIds(List<Long> ids);

    /**
     * 获得评价任务
     *
     * @param id 编号
     * @return 评价任务
     */
    TaskDO getTask(Long id);

    /**
     * 获得评价任务分页
     *
     * @param pageReqVO 分页查询
     * @return 评价任务分页
     */
    PageResult<TaskDO> getTaskPage(TaskPageReqVO pageReqVO);

    PageResult<TaskRespVO> getTaskPagerelevant(TaskPageReqVO reqVO);
}