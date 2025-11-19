package cn.iocoder.yudao.module.datacenter.service.evaluate.inspect.evalinspectplan;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspect.evalinspectplan.vo.EvalInspectPlanPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspect.evalinspectplan.vo.EvalInspectPlanSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.inspect.evalinspectplan.EvalInspectPlanDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.inspect.evalinspectplan.EvalInspectPlanMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 考察计划管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalInspectPlanServiceImpl implements EvalInspectPlanService {

    @Resource
    private EvalInspectPlanMapper evalInspectPlanMapper;

    @Override
    public Long createEvalInspectPlan(EvalInspectPlanSaveReqVO createReqVO) {
        // 插入
        EvalInspectPlanDO evalInspectPlan = BeanUtils.toBean(createReqVO, EvalInspectPlanDO.class);
        evalInspectPlanMapper.insert(evalInspectPlan);
        // 返回
        return evalInspectPlan.getId();
    }

    @Override
    public void updateEvalInspectPlan(EvalInspectPlanSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalInspectPlanExists(updateReqVO.getId());
        // 更新
        EvalInspectPlanDO updateObj = BeanUtils.toBean(updateReqVO, EvalInspectPlanDO.class);
        evalInspectPlanMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalInspectPlan(Long id) {
        // 校验存在
        validateEvalInspectPlanExists(id);
        // 删除
        evalInspectPlanMapper.deleteById(id);
    }

    private void validateEvalInspectPlanExists(Long id) {
        if (evalInspectPlanMapper.selectById(id) == null) {
            throw exception(EVAL_INSPECT_PLAN_NOT_EXISTS);
        }
    }

    @Override
    public EvalInspectPlanDO getEvalInspectPlan(Long id) {
        return evalInspectPlanMapper.selectById(id);
    }

    @Override
    public PageResult<EvalInspectPlanDO> getEvalInspectPlanPage(EvalInspectPlanPageReqVO pageReqVO) {
        return evalInspectPlanMapper.selectPage(pageReqVO);
    }

}