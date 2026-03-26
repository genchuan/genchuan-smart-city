package cn.iocoder.yudao.module.waterdetection.service.leakagecontrolplan;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.leakagecontrolplan.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.leakagecontrolplan.LeakageControlPlanDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.leakagecontrolplan.LeakageControlPlanMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 漏损控制方案建议 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class LeakageControlPlanServiceImpl implements LeakageControlPlanService {

    @Resource
    private LeakageControlPlanMapper leakageControlPlanMapper;

    @Override
    public Long createLeakageControlPlan(LeakageControlPlanSaveReqVO createReqVO) {
        // 插入
        LeakageControlPlanDO leakageControlPlan = BeanUtils.toBean(createReqVO, LeakageControlPlanDO.class);
        leakageControlPlanMapper.insert(leakageControlPlan);
        // 返回
        return leakageControlPlan.getId();
    }

    @Override
    public void updateLeakageControlPlan(LeakageControlPlanSaveReqVO updateReqVO) {
        // 校验存在
        validateLeakageControlPlanExists(updateReqVO.getId());
        // 更新
        LeakageControlPlanDO updateObj = BeanUtils.toBean(updateReqVO, LeakageControlPlanDO.class);
        leakageControlPlanMapper.updateById(updateObj);
    }

    @Override
    public void deleteLeakageControlPlan(Long id) {
        // 校验存在
        validateLeakageControlPlanExists(id);
        // 删除
        leakageControlPlanMapper.deleteById(id);
    }

    private void validateLeakageControlPlanExists(Long id) {
        if (leakageControlPlanMapper.selectById(id) == null) {
            throw exception(LEAKAGE_CONTROL_PLAN_NOT_EXISTS);
        }
    }

    @Override
    public LeakageControlPlanDO getLeakageControlPlan(Long id) {
        return leakageControlPlanMapper.selectById(id);
    }

    @Override
    public PageResult<LeakageControlPlanDO> getLeakageControlPlanPage(LeakageControlPlanPageReqVO pageReqVO) {
        return leakageControlPlanMapper.selectPage(pageReqVO);
    }

}