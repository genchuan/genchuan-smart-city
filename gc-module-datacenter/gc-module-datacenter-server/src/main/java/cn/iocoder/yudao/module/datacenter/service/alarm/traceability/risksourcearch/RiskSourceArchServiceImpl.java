package cn.iocoder.yudao.module.datacenter.service.alarm.traceability.risksourcearch;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.risksourcearch.vo.RiskSourceArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.risksourcearch.vo.RiskSourceArchSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.traceability.risksourcearch.RiskSourceArchDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.traceability.risksourcearch.RiskSourceArchMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 风险源档案 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class RiskSourceArchServiceImpl implements RiskSourceArchService {

    @Resource
    private RiskSourceArchMapper riskSourceArchMapper;

    @Override
    public Long createRiskSourceArch(RiskSourceArchSaveReqVO createReqVO) {
        // 插入
        RiskSourceArchDO riskSourceArch = BeanUtils.toBean(createReqVO, RiskSourceArchDO.class);
        riskSourceArchMapper.insert(riskSourceArch);
        // 返回
        return riskSourceArch.getId();
    }

    @Override
    public void updateRiskSourceArch(RiskSourceArchSaveReqVO updateReqVO) {
        // 校验存在
        validateRiskSourceArchExists(updateReqVO.getId());
        // 更新
        RiskSourceArchDO updateObj = BeanUtils.toBean(updateReqVO, RiskSourceArchDO.class);
        riskSourceArchMapper.updateById(updateObj);
    }

    @Override
    public void deleteRiskSourceArch(Long id) {
        // 校验存在
        validateRiskSourceArchExists(id);
        // 删除
        riskSourceArchMapper.deleteById(id);
    }

    private void validateRiskSourceArchExists(Long id) {
        if (riskSourceArchMapper.selectById(id) == null) {
            throw exception(RISK_SOURCE_ARCH_NOT_EXISTS);
        }
    }

    @Override
    public RiskSourceArchDO getRiskSourceArch(Long id) {
        return riskSourceArchMapper.selectById(id);
    }

    @Override
    public PageResult<RiskSourceArchDO> getRiskSourceArchPage(RiskSourceArchPageReqVO pageReqVO) {
        return riskSourceArchMapper.selectPage(pageReqVO);
    }

}