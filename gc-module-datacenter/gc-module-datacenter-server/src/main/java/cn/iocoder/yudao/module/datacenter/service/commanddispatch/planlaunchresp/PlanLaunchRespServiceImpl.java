package cn.iocoder.yudao.module.datacenter.service.commanddispatch.planlaunchresp;


import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.planlaunchresp.vo.PlanLaunchRespPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.planlaunchresp.vo.PlanLaunchRespSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.commanddispatch.planlaunchresp.PlanLaunchRespDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.commanddispatch.planlaunchresp.PlanLaunchRespMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 预案启动响应 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class PlanLaunchRespServiceImpl implements PlanLaunchRespService {

    @Resource
    private PlanLaunchRespMapper planLaunchRespMapper;

    @Override
    public Long createPlanLaunchResp(PlanLaunchRespSaveReqVO createReqVO) {
        // 插入
        PlanLaunchRespDO planLaunchResp = BeanUtils.toBean(createReqVO, PlanLaunchRespDO.class);
        planLaunchRespMapper.insert(planLaunchResp);
        // 返回
        return planLaunchResp.getId();
    }

    @Override
    public void updatePlanLaunchResp(PlanLaunchRespSaveReqVO updateReqVO) {
        // 校验存在
        validatePlanLaunchRespExists(updateReqVO.getId());
        // 更新
        PlanLaunchRespDO updateObj = BeanUtils.toBean(updateReqVO, PlanLaunchRespDO.class);
        planLaunchRespMapper.updateById(updateObj);
    }

    @Override
    public void deletePlanLaunchResp(Long id) {
        // 校验存在
        validatePlanLaunchRespExists(id);
        // 删除
        planLaunchRespMapper.deleteById(id);
    }

    private void validatePlanLaunchRespExists(Long id) {
        if (planLaunchRespMapper.selectById(id) == null) {
            throw exception(PLAN_LAUNCH_RESP_NOT_EXISTS);
        }
    }

    @Override
    public PlanLaunchRespDO getPlanLaunchResp(Long id) {
        return planLaunchRespMapper.selectById(id);
    }

    @Override
    public PageResult<PlanLaunchRespDO> getPlanLaunchRespPage(PlanLaunchRespPageReqVO pageReqVO) {
        return planLaunchRespMapper.selectPage(pageReqVO);
    }

}