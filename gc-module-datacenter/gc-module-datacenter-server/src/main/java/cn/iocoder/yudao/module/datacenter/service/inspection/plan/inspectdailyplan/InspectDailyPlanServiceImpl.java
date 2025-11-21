package cn.iocoder.yudao.module.datacenter.service.inspection.plan.inspectdailyplan;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.plan.inspectdailyplan.vo.InspectDailyPlanPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.plan.inspectdailyplan.vo.InspectDailyPlanSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.plan.inspectdailyplan.InspectDailyPlanDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.plan.inspectdailyplan.InspectDailyPlanMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 日常巡查计划信息 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectDailyPlanServiceImpl implements InspectDailyPlanService {

    @Resource
    private InspectDailyPlanMapper inspectDailyPlanMapper;

    @Override
    public Long createInspectDailyPlan(InspectDailyPlanSaveReqVO createReqVO) {
        // 插入
        InspectDailyPlanDO inspectDailyPlan = BeanUtils.toBean(createReqVO, InspectDailyPlanDO.class);
        inspectDailyPlanMapper.insert(inspectDailyPlan);
        // 返回
        return inspectDailyPlan.getId();
    }

    @Override
    public void updateInspectDailyPlan(InspectDailyPlanSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectDailyPlanExists(updateReqVO.getId());
        // 更新
        InspectDailyPlanDO updateObj = BeanUtils.toBean(updateReqVO, InspectDailyPlanDO.class);
        inspectDailyPlanMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectDailyPlan(Long id) {
        // 校验存在
        validateInspectDailyPlanExists(id);
        // 删除
        inspectDailyPlanMapper.deleteById(id);
    }

    private void validateInspectDailyPlanExists(Long id) {
        if (inspectDailyPlanMapper.selectById(id) == null) {
            throw exception(INSPECT_DAILY_PLAN_NOT_EXISTS);
        }
    }

    @Override
    public InspectDailyPlanDO getInspectDailyPlan(Long id) {
        return inspectDailyPlanMapper.selectById(id);
    }

    @Override
    public PageResult<InspectDailyPlanDO> getInspectDailyPlanPage(InspectDailyPlanPageReqVO pageReqVO) {
        return inspectDailyPlanMapper.selectPage(pageReqVO);
    }

}