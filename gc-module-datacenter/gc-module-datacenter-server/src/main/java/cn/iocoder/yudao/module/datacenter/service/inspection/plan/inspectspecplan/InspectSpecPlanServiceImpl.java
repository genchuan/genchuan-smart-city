package cn.iocoder.yudao.module.datacenter.service.inspection.plan.inspectspecplan;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.plan.inspectspecplan.vo.InspectSpecPlanPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.plan.inspectspecplan.vo.InspectSpecPlanSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.plan.inspectspecplan.InspectSpecPlanDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.plan.inspectspecplan.InspectSpecPlanMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 专项巡查计划信息 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectSpecPlanServiceImpl implements InspectSpecPlanService {

    @Resource
    private InspectSpecPlanMapper inspectSpecPlanMapper;

    @Override
    public Long createInspectSpecPlan(InspectSpecPlanSaveReqVO createReqVO) {
        // 插入
        InspectSpecPlanDO inspectSpecPlan = BeanUtils.toBean(createReqVO, InspectSpecPlanDO.class);
        inspectSpecPlanMapper.insert(inspectSpecPlan);
        // 返回
        return inspectSpecPlan.getId();
    }

    @Override
    public void updateInspectSpecPlan(InspectSpecPlanSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectSpecPlanExists(updateReqVO.getId());
        // 更新
        InspectSpecPlanDO updateObj = BeanUtils.toBean(updateReqVO, InspectSpecPlanDO.class);
        inspectSpecPlanMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectSpecPlan(Long id) {
        // 校验存在
        validateInspectSpecPlanExists(id);
        // 删除
        inspectSpecPlanMapper.deleteById(id);
    }

    private void validateInspectSpecPlanExists(Long id) {
        if (inspectSpecPlanMapper.selectById(id) == null) {
            throw exception(INSPECT_SPEC_PLAN_NOT_EXISTS);
        }
    }

    @Override
    public InspectSpecPlanDO getInspectSpecPlan(Long id) {
        return inspectSpecPlanMapper.selectById(id);
    }

    @Override
    public PageResult<InspectSpecPlanDO> getInspectSpecPlanPage(InspectSpecPlanPageReqVO pageReqVO) {
        return inspectSpecPlanMapper.selectPage(pageReqVO);
    }

}