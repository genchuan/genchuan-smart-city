package cn.iocoder.yudao.module.smartcity.service.policyandregulationinput;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.policyandregulationinput.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.policyandregulationinput.PolicyAndRegulationInputDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.policyandregulationinput.PolicyAndRegulationInputMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 政策法规录入 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class PolicyAndRegulationInputServiceImpl implements PolicyAndRegulationInputService {

    @Resource
    private PolicyAndRegulationInputMapper policyAndRegulationInputMapper;

    @Override
    public Long createPolicyAndRegulationInput(PolicyAndRegulationInputSaveReqVO createReqVO) {
        // 插入
        PolicyAndRegulationInputDO policyAndRegulationInput = BeanUtils.toBean(createReqVO, PolicyAndRegulationInputDO.class);
        policyAndRegulationInputMapper.insert(policyAndRegulationInput);
        // 返回
        return policyAndRegulationInput.getId();
    }

    @Override
    public void updatePolicyAndRegulationInput(PolicyAndRegulationInputSaveReqVO updateReqVO) {
        // 校验存在
        validatePolicyAndRegulationInputExists(updateReqVO.getId());
        // 更新
        PolicyAndRegulationInputDO updateObj = BeanUtils.toBean(updateReqVO, PolicyAndRegulationInputDO.class);
        policyAndRegulationInputMapper.updateById(updateObj);
    }

    @Override
    public void deletePolicyAndRegulationInput(Long id) {
        // 校验存在
        validatePolicyAndRegulationInputExists(id);
        // 删除
        policyAndRegulationInputMapper.deleteById(id);
    }

    private void validatePolicyAndRegulationInputExists(Long id) {
        if (policyAndRegulationInputMapper.selectById(id) == null) {
            throw exception(POLICY_AND_REGULATION_INPUT_NOT_EXISTS);
        }
    }

    @Override
    public PolicyAndRegulationInputDO getPolicyAndRegulationInput(Long id) {
        return policyAndRegulationInputMapper.selectById(id);
    }

    @Override
    public PageResult<PolicyAndRegulationInputDO> getPolicyAndRegulationInputPage(PolicyAndRegulationInputPageReqVO pageReqVO) {
        return policyAndRegulationInputMapper.selectPage(pageReqVO);
    }

}