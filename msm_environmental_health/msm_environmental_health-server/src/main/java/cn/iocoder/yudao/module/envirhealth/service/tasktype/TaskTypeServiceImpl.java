package cn.iocoder.yudao.module.envirhealth.service.tasktype;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.envirhealth.controller.admin.tasktype.vo.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.tasktype.TaskTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.tasktype.TaskTypeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 任务类型字典表【通用复用】 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class TaskTypeServiceImpl implements TaskTypeService {

    @Resource
    private TaskTypeMapper taskTypeMapper;

    @Override
    public Long createTaskType(TaskTypeSaveReqVO createReqVO) {
        // 插入
        TaskTypeDO taskType = BeanUtils.toBean(createReqVO, TaskTypeDO.class);
        taskTypeMapper.insert(taskType);
        // 返回
        return taskType.getId();
    }

    @Override
    public void updateTaskType(TaskTypeSaveReqVO updateReqVO) {
        // 校验存在
        validateTaskTypeExists(updateReqVO.getId());
        // 更新
        TaskTypeDO updateObj = BeanUtils.toBean(updateReqVO, TaskTypeDO.class);
        taskTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteTaskType(Long id) {
        // 校验存在
        validateTaskTypeExists(id);
        // 删除
        taskTypeMapper.deleteById(id);
    }

    private void validateTaskTypeExists(Long id) {
        if (taskTypeMapper.selectById(id) == null) {
            throw exception(TASK_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public TaskTypeDO getTaskType(Long id) {
        return taskTypeMapper.selectById(id);
    }

    @Override
    public PageResult<TaskTypeDO> getTaskTypePage(TaskTypePageReqVO pageReqVO) {
        return taskTypeMapper.selectPage(pageReqVO);
    }

}