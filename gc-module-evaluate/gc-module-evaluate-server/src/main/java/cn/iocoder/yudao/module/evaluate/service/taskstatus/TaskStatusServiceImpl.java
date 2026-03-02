package cn.iocoder.yudao.module.evaluate.service.taskstatus;

import cn.iocoder.yudao.module.evaluate.controller.admin.sys.taskstatus.vo.TaskStatusPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.taskstatus.vo.TaskStatusSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.evaluate.dal.dataobject.taskstatus.TaskStatusDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.evaluate.dal.mysql.taskstatus.TaskStatusMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.TASK_STATUS_NOT_EXISTS;

/**
 * 任务状态字典 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class TaskStatusServiceImpl implements TaskStatusService {

    @Resource
    private TaskStatusMapper taskStatusMapper;

    @Override
    public Long createTaskStatus(TaskStatusSaveReqVO createReqVO) {
        // 插入
        TaskStatusDO taskStatus = BeanUtils.toBean(createReqVO, TaskStatusDO.class);
        taskStatusMapper.insert(taskStatus);
        // 返回
        return taskStatus.getId();
    }

    @Override
    public void updateTaskStatus(TaskStatusSaveReqVO updateReqVO) {
        // 校验存在
        validateTaskStatusExists(updateReqVO.getId());
        // 更新
        TaskStatusDO updateObj = BeanUtils.toBean(updateReqVO, TaskStatusDO.class);
        taskStatusMapper.updateById(updateObj);
    }

    @Override
    public void deleteTaskStatus(Long id) {
        // 校验存在
        validateTaskStatusExists(id);
        // 删除
        taskStatusMapper.deleteById(id);
    }

    private void validateTaskStatusExists(Long id) {
        if (taskStatusMapper.selectById(id) == null) {
            throw exception(TASK_STATUS_NOT_EXISTS);
        }
    }

    @Override
    public TaskStatusDO getTaskStatus(Long id) {
        return taskStatusMapper.selectById(id);
    }

    @Override
    public PageResult<TaskStatusDO> getTaskStatusPage(TaskStatusPageReqVO pageReqVO) {
        return taskStatusMapper.selectPage(pageReqVO);
    }

}