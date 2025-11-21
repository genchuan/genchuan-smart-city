package cn.iocoder.yudao.module.datacenter.service.inspection.plan.inspectemerplan;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.plan.inspectemerplan.vo.InspectEmerPlanPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.plan.inspectemerplan.vo.InspectEmerPlanSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.plan.inspectemerplan.InspectEmerPlanDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.plan.inspectemerplan.InspectEmerPlanMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 应急巡查计划信息 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectEmerPlanServiceImpl implements InspectEmerPlanService {

    @Resource
    private InspectEmerPlanMapper inspectEmerPlanMapper;

    @Override
    public Long createInspectEmerPlan(InspectEmerPlanSaveReqVO createReqVO) {
        // 插入
        InspectEmerPlanDO inspectEmerPlan = BeanUtils.toBean(createReqVO, InspectEmerPlanDO.class);
        inspectEmerPlanMapper.insert(inspectEmerPlan);
        // 返回
        return inspectEmerPlan.getId();
    }

    @Override
    public void updateInspectEmerPlan(InspectEmerPlanSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectEmerPlanExists(updateReqVO.getId());
        // 更新
        InspectEmerPlanDO updateObj = BeanUtils.toBean(updateReqVO, InspectEmerPlanDO.class);
        inspectEmerPlanMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectEmerPlan(Long id) {
        // 校验存在
        validateInspectEmerPlanExists(id);
        // 删除
        inspectEmerPlanMapper.deleteById(id);
    }

    private void validateInspectEmerPlanExists(Long id) {
        if (inspectEmerPlanMapper.selectById(id) == null) {
            throw exception(INSPECT_EMER_PLAN_NOT_EXISTS);
        }
    }

    @Override
    public InspectEmerPlanDO getInspectEmerPlan(Long id) {
        return inspectEmerPlanMapper.selectById(id);
    }

    @Override
    public PageResult<InspectEmerPlanDO> getInspectEmerPlanPage(InspectEmerPlanPageReqVO pageReqVO) {
        return inspectEmerPlanMapper.selectPage(pageReqVO);
    }

}