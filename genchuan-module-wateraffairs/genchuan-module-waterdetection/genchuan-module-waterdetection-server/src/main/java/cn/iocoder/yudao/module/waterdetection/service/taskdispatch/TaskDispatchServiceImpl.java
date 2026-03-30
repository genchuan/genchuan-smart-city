package cn.iocoder.yudao.module.waterdetection.service.taskdispatch;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.taskdispatch.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.taskdispatch.TaskDispatchDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.taskdispatch.TaskDispatchMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 任务派发 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class TaskDispatchServiceImpl implements TaskDispatchService {

    @Resource
    private TaskDispatchMapper taskDispatchMapper;

    @Override
    public Long createTaskDispatch(TaskDispatchSaveReqVO createReqVO) {
        // 插入
        TaskDispatchDO taskDispatch = BeanUtils.toBean(createReqVO, TaskDispatchDO.class);
        taskDispatchMapper.insert(taskDispatch);
        // 返回
        return taskDispatch.getId();
    }

    @Override
    public void updateTaskDispatch(TaskDispatchSaveReqVO updateReqVO) {
        // 校验存在
        validateTaskDispatchExists(updateReqVO.getId());
        // 更新
        TaskDispatchDO updateObj = BeanUtils.toBean(updateReqVO, TaskDispatchDO.class);
        taskDispatchMapper.updateById(updateObj);
    }

    @Override
    public void deleteTaskDispatch(Long id) {
        // 校验存在
        validateTaskDispatchExists(id);
        // 删除
        taskDispatchMapper.deleteById(id);
    }

    private void validateTaskDispatchExists(Long id) {
        if (taskDispatchMapper.selectById(id) == null) {
            throw exception(TASK_DISPATCH_NOT_EXISTS);
        }
    }

    @Override
    public TaskDispatchDO getTaskDispatch(Long id) {
        return taskDispatchMapper.selectById(id);
    }

    @Override
    public PageResult<TaskDispatchDO> getTaskDispatchPage(TaskDispatchPageReqVO pageReqVO) {
        return taskDispatchMapper.selectPage(pageReqVO);
    }

}