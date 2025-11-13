package cn.iocoder.yudao.module.datacenter.service.alarm.traceability.riskpropagatechain;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.riskpropagatechain.vo.RiskPropagateChainPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.riskpropagatechain.vo.RiskPropagateChainSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.traceability.riskpropagatechain.RiskPropagateChainDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.traceability.riskpropagatechain.RiskPropagateChainMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 风险传播链 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class RiskPropagateChainServiceImpl implements RiskPropagateChainService {

    @Resource
    private RiskPropagateChainMapper riskPropagateChainMapper;

    @Override
    public Long createRiskPropagateChain(RiskPropagateChainSaveReqVO createReqVO) {
        // 插入
        RiskPropagateChainDO riskPropagateChain = BeanUtils.toBean(createReqVO, RiskPropagateChainDO.class);
        riskPropagateChainMapper.insert(riskPropagateChain);
        // 返回
        return riskPropagateChain.getId();
    }

    @Override
    public void updateRiskPropagateChain(RiskPropagateChainSaveReqVO updateReqVO) {
        // 校验存在
        validateRiskPropagateChainExists(updateReqVO.getId());
        // 更新
        RiskPropagateChainDO updateObj = BeanUtils.toBean(updateReqVO, RiskPropagateChainDO.class);
        riskPropagateChainMapper.updateById(updateObj);
    }

    @Override
    public void deleteRiskPropagateChain(Long id) {
        // 校验存在
        validateRiskPropagateChainExists(id);
        // 删除
        riskPropagateChainMapper.deleteById(id);
    }

    private void validateRiskPropagateChainExists(Long id) {
        if (riskPropagateChainMapper.selectById(id) == null) {
            throw exception(RISK_PROPAGATE_CHAIN_NOT_EXISTS);
        }
    }

    @Override
    public RiskPropagateChainDO getRiskPropagateChain(Long id) {
        return riskPropagateChainMapper.selectById(id);
    }

    @Override
    public PageResult<RiskPropagateChainDO> getRiskPropagateChainPage(RiskPropagateChainPageReqVO pageReqVO) {
        return riskPropagateChainMapper.selectPage(pageReqVO);
    }

}