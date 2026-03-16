package cn.iocoder.yudao.module.envirhealth.service.task;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.task.vo.TaskPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.task.vo.TaskSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.task.TaskDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.task.detail.TaskDetailWithGarbageTransferDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.task.detail.TaskDetailWithPublicInstitutionDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.task.detail.TaskDetailWithPublicToiletDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.task.TaskMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.TASK_NOT_EXISTS;

/**
 * 任务 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class TaskServiceImpl implements TaskService {

    @Resource
    private TaskMapper taskMapper;

    @Override
    public Long createTask(TaskSaveReqVO createReqVO) {
        // 插入
        TaskDO task = BeanUtils.toBean(createReqVO, TaskDO.class);
        taskMapper.insert(task);
        // 返回
        return task.getId();
    }

    @Override
    public void updateTask(TaskSaveReqVO updateReqVO) {
        // 校验存在
        validateTaskExists(updateReqVO.getId());
        // 更新
        TaskDO updateObj = BeanUtils.toBean(updateReqVO, TaskDO.class);
        taskMapper.updateById(updateObj);
    }

    @Override
    public void deleteTask(Long id) {
        // 校验存在
        validateTaskExists(id);
        // 删除
        taskMapper.deleteById(id);
    }

    private void validateTaskExists(Long id) {
        if (taskMapper.selectById(id) == null) {
            throw exception(TASK_NOT_EXISTS);
        }
    }

    @Override
    public TaskDO getTask(Long id) {
        return taskMapper.selectById(id);
    }

    @Override
    public PageResult<TaskDO> getTaskPage(TaskPageReqVO pageReqVO) {
        return taskMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<TaskDetailWithPublicToiletDO> getTaskDetailPageWithPublicToilet(TaskPageReqVO pageReqVO) {
        Long total = taskMapper.selectCountWithPublicToilet(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<TaskDetailWithPublicToiletDO> list = taskMapper.selectDetailPageWithPublicToilet(pageReqVO);
        return new PageResult<>(list, total);
    }

    @Override
    public PageResult<TaskDetailWithGarbageTransferDO> getTaskDetailPageWithGarbageTransfer(TaskPageReqVO pageReqVO) {
        Long total = taskMapper.selectCountWithGarbageTransfer(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<TaskDetailWithGarbageTransferDO> list = taskMapper.selectDetailPageWithGarbageTransfer(pageReqVO);
        return new PageResult<>(list, total);
    }

    @Override
    public PageResult<TaskDetailWithPublicInstitutionDO> getTaskDetailPageWithPublicInstitution(TaskPageReqVO pageReqVO) {
        Long total = taskMapper.selectCountWithPublicInstitution(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<TaskDetailWithPublicInstitutionDO> list = taskMapper.selectDetailPageWithPublicInstitution(pageReqVO);
        return new PageResult<>(list, total);
    }
}