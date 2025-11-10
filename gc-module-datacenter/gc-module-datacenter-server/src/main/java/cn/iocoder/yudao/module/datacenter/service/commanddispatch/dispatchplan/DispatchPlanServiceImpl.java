package cn.iocoder.yudao.module.datacenter.service.commanddispatch.dispatchplan;

import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatchplan.vo.DispatchPlanPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatchplan.vo.DispatchPlanSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.commanddispatch.dispatchplan.DispatchPlanDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.commanddispatch.dispatchplan.DispatchPlanMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;
/**
 * 调度方案 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class DispatchPlanServiceImpl implements DispatchPlanService {

    @Resource
    private DispatchPlanMapper dispatchPlanMapper;

    @Override
    public Long createDispatchPlan(DispatchPlanSaveReqVO createReqVO) {
        // 插入
        DispatchPlanDO dispatchPlan = BeanUtils.toBean(createReqVO, DispatchPlanDO.class);
        dispatchPlanMapper.insert(dispatchPlan);
        // 返回
        return dispatchPlan.getId();
    }

    @Override
    public void updateDispatchPlan(DispatchPlanSaveReqVO updateReqVO) {
        // 校验存在
        validateDispatchPlanExists(updateReqVO.getId());
        // 更新
        DispatchPlanDO updateObj = BeanUtils.toBean(updateReqVO, DispatchPlanDO.class);
        dispatchPlanMapper.updateById(updateObj);
    }

    @Override
    public void deleteDispatchPlan(Long id) {
        // 校验存在
        validateDispatchPlanExists(id);
        // 删除
        dispatchPlanMapper.deleteById(id);
    }

    private void validateDispatchPlanExists(Long id) {
        if (dispatchPlanMapper.selectById(id) == null) {
            throw exception(DISPATCH_PLAN_NOT_EXISTS);
        }
    }

    @Override
    public DispatchPlanDO getDispatchPlan(Long id) {
        return dispatchPlanMapper.selectById(id);
    }

    @Override
    public PageResult<DispatchPlanDO> getDispatchPlanPage(DispatchPlanPageReqVO pageReqVO) {
        return dispatchPlanMapper.selectPage(pageReqVO);
    }

}