package cn.iocoder.yudao.module.datacenter.service.commanddispatch.taskoverallmng;

import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.taskoverallmng.vo.TaskOverallMngPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.taskoverallmng.vo.TaskOverallMngSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.commanddispatch.taskoverallmng.TaskOverallMngDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.commanddispatch.taskoverallmng.TaskOverallMngMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;
/**
 * 任务统筹管理 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class TaskOverallMngServiceImpl implements TaskOverallMngService {

    @Resource
    private TaskOverallMngMapper taskOverallMngMapper;

    @Override
    public Long createTaskOverallMng(TaskOverallMngSaveReqVO createReqVO) {
        // 插入
        TaskOverallMngDO taskOverallMng = BeanUtils.toBean(createReqVO, TaskOverallMngDO.class);
        taskOverallMngMapper.insert(taskOverallMng);
        // 返回
        return taskOverallMng.getId();
    }

    @Override
    public void updateTaskOverallMng(TaskOverallMngSaveReqVO updateReqVO) {
        // 校验存在
        validateTaskOverallMngExists(updateReqVO.getId());
        // 更新
        TaskOverallMngDO updateObj = BeanUtils.toBean(updateReqVO, TaskOverallMngDO.class);
        taskOverallMngMapper.updateById(updateObj);
    }

    @Override
    public void deleteTaskOverallMng(Long id) {
        // 校验存在
        validateTaskOverallMngExists(id);
        // 删除
        taskOverallMngMapper.deleteById(id);
    }

    private void validateTaskOverallMngExists(Long id) {
        if (taskOverallMngMapper.selectById(id) == null) {
            throw exception(TASK_OVERALL_MNG_NOT_EXISTS);
        }
    }

    @Override
    public TaskOverallMngDO getTaskOverallMng(Long id) {
        return taskOverallMngMapper.selectById(id);
    }

    @Override
    public PageResult<TaskOverallMngDO> getTaskOverallMngPage(TaskOverallMngPageReqVO pageReqVO) {
        return taskOverallMngMapper.selectPage(pageReqVO);
    }

}