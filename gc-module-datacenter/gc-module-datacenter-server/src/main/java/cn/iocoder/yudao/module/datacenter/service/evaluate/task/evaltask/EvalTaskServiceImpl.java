package cn.iocoder.yudao.module.datacenter.service.evaluate.task.evaltask;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.task.evaltask.vo.EvalTaskPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.task.evaltask.vo.EvalTaskSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.task.evaltask.EvalTaskDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.task.evaltask.EvalTaskMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 任务管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalTaskServiceImpl implements EvalTaskService {

    @Resource
    private EvalTaskMapper evalTaskMapper;

    @Override
    public Long createEvalTask(EvalTaskSaveReqVO createReqVO) {
        // 插入
        EvalTaskDO evalTask = BeanUtils.toBean(createReqVO, EvalTaskDO.class);
        evalTaskMapper.insert(evalTask);
        // 返回
        return evalTask.getId();
    }

    @Override
    public void updateEvalTask(EvalTaskSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalTaskExists(updateReqVO.getId());
        // 更新
        EvalTaskDO updateObj = BeanUtils.toBean(updateReqVO, EvalTaskDO.class);
        evalTaskMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalTask(Long id) {
        // 校验存在
        validateEvalTaskExists(id);
        // 删除
        evalTaskMapper.deleteById(id);
    }

    private void validateEvalTaskExists(Long id) {
        if (evalTaskMapper.selectById(id) == null) {
            throw exception(EVAL_TASK_NOT_EXISTS);
        }
    }

    @Override
    public EvalTaskDO getEvalTask(Long id) {
        return evalTaskMapper.selectById(id);
    }

    @Override
    public PageResult<EvalTaskDO> getEvalTaskPage(EvalTaskPageReqVO pageReqVO) {
        return evalTaskMapper.selectPage(pageReqVO);
    }

}