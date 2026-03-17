package cn.iocoder.yudao.module.evaluate.service.plan;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.inspection.plan.vo.PlanPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.inspection.plan.vo.PlanSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.plan.PlanDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.plan.PlanMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.PLAN_NOT_EXISTS;

/**
 * 考察计划 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PlanServiceImpl implements PlanService {

    @Resource
    private PlanMapper planMapper;

    @Override
    public Long createPlan(PlanSaveReqVO createReqVO) {
        // 插入
        PlanDO plan = BeanUtils.toBean(createReqVO, PlanDO.class);
        planMapper.insert(plan);

        // 返回
        return plan.getId();
    }

    @Override
    public void updatePlan(PlanSaveReqVO updateReqVO) {
        // 校验存在
        validatePlanExists(updateReqVO.getId());
        // 更新
        PlanDO updateObj = BeanUtils.toBean(updateReqVO, PlanDO.class);
        planMapper.updateById(updateObj);
    }

    @Override
    public void deletePlan(Long id) {
        // 校验存在
        validatePlanExists(id);
        // 删除
        planMapper.deleteById(id);
    }

    @Override
        public void deletePlanListByIds(List<Long> ids) {
        // 删除
        planMapper.deleteByIds(ids);
        }


    private void validatePlanExists(Long id) {
        if (planMapper.selectById(id) == null) {
            throw exception(PLAN_NOT_EXISTS);
        }
    }

    @Override
    public PlanDO getPlan(Long id) {
        return planMapper.selectById(id);
    }

    @Override
    public PageResult<PlanDO> getPlanPage(PlanPageReqVO pageReqVO) {
        return planMapper.selectPage(pageReqVO);
    }

}