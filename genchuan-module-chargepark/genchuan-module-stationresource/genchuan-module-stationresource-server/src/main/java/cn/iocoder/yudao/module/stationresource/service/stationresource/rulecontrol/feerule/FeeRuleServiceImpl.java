package cn.iocoder.yudao.module.stationresource.service.stationresource.rulecontrol.feerule;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.stationresource.enums.ErrorCodeConstants.*;

/**
 * 收费规则 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class FeeRuleServiceImpl implements FeeRuleService {

    @Resource
    private FeeRuleMapper feeRuleMapper;

    @Override
    public Long createFeeRule(FeeRuleSaveReqVO createReqVO) {
        // 插入
        FeeRuleDO feeRule = BeanUtils.toBean(createReqVO, FeeRuleDO.class);
        feeRuleMapper.insert(feeRule);

        // 返回
        return feeRule.getId();
    }

    @Override
    public void updateFeeRule(FeeRuleSaveReqVO updateReqVO) {
        // 校验存在
        validateFeeRuleExists(updateReqVO.getId());
        // 更新
        FeeRuleDO updateObj = BeanUtils.toBean(updateReqVO, FeeRuleDO.class);
        feeRuleMapper.updateById(updateObj);
    }

    @Override
    public void deleteFeeRule(Long id) {
        // 校验存在
        validateFeeRuleExists(id);
        // 删除
        feeRuleMapper.deleteById(id);
    }

    @Override
        public void deleteFeeRuleListByIds(List<Long> ids) {
        // 删除
        feeRuleMapper.deleteByIds(ids);
        }


    private void validateFeeRuleExists(Long id) {
        if (feeRuleMapper.selectById(id) == null) {
            throw exception(FEE_RULE_NOT_EXISTS);
        }
    }

    @Override
    public FeeRuleDO getFeeRule(Long id) {
        return feeRuleMapper.selectById(id);
    }

    @Override
    public PageResult<FeeRuleDO> getFeeRulePage(FeeRulePageReqVO pageReqVO) {
        return feeRuleMapper.selectPage(pageReqVO);
    }

}
