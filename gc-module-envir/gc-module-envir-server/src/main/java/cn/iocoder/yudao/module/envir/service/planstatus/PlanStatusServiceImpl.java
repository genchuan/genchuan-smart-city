package cn.iocoder.yudao.module.envir.service.planstatus;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.envir.controller.admin.planstatus.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.planstatus.PlanStatusDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envir.dal.mysql.planstatus.PlanStatusMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envir.enums.ErrorCodeConstants.*;

/**
 * 计划状态字典 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PlanStatusServiceImpl implements PlanStatusService {

    @Resource
    private PlanStatusMapper planStatusMapper;

    @Override
    public Long createPlanStatus(PlanStatusSaveReqVO createReqVO) {
        // 插入
        PlanStatusDO planStatus = BeanUtils.toBean(createReqVO, PlanStatusDO.class);
        planStatusMapper.insert(planStatus);
        // 返回
        return planStatus.getId();
    }

    @Override
    public void updatePlanStatus(PlanStatusSaveReqVO updateReqVO) {
        // 校验存在
        validatePlanStatusExists(updateReqVO.getId());
        // 更新
        PlanStatusDO updateObj = BeanUtils.toBean(updateReqVO, PlanStatusDO.class);
        planStatusMapper.updateById(updateObj);
    }

    @Override
    public void deletePlanStatus(Long id) {
        // 校验存在
        validatePlanStatusExists(id);
        // 删除
        planStatusMapper.deleteById(id);
    }

    private void validatePlanStatusExists(Long id) {
        if (planStatusMapper.selectById(id) == null) {
            throw exception(PLAN_STATUS_NOT_EXISTS);
        }
    }

    @Override
    public PlanStatusDO getPlanStatus(Long id) {
        return planStatusMapper.selectById(id);
    }

    @Override
    public PageResult<PlanStatusDO> getPlanStatusPage(PlanStatusPageReqVO pageReqVO) {
        return planStatusMapper.selectPage(pageReqVO);
    }

}