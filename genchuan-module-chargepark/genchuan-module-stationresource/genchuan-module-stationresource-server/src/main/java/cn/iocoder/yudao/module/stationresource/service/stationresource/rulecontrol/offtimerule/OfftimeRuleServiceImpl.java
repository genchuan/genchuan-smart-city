package cn.iocoder.yudao.module.stationresource.service.stationresource.rulecontrol.offtimerule;

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
 * 错时规则 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class OfftimeRuleServiceImpl implements OfftimeRuleService {

    @Resource
    private OfftimeRuleMapper offtimeRuleMapper;

    @Override
    public Long createOfftimeRule(OfftimeRuleSaveReqVO createReqVO) {
        // 插入
        OfftimeRuleDO offtimeRule = BeanUtils.toBean(createReqVO, OfftimeRuleDO.class);
        offtimeRuleMapper.insert(offtimeRule);

        // 返回
        return offtimeRule.getId();
    }

    @Override
    public void updateOfftimeRule(OfftimeRuleSaveReqVO updateReqVO) {
        // 校验存在
        validateOfftimeRuleExists(updateReqVO.getId());
        // 更新
        OfftimeRuleDO updateObj = BeanUtils.toBean(updateReqVO, OfftimeRuleDO.class);
        offtimeRuleMapper.updateById(updateObj);
    }

    @Override
    public void deleteOfftimeRule(Long id) {
        // 校验存在
        validateOfftimeRuleExists(id);
        // 删除
        offtimeRuleMapper.deleteById(id);
    }

    @Override
        public void deleteOfftimeRuleListByIds(List<Long> ids) {
        // 删除
        offtimeRuleMapper.deleteByIds(ids);
        }


    private void validateOfftimeRuleExists(Long id) {
        if (offtimeRuleMapper.selectById(id) == null) {
            throw exception(OFFTIME_RULE_NOT_EXISTS);
        }
    }

    @Override
    public OfftimeRuleDO getOfftimeRule(Long id) {
        return offtimeRuleMapper.selectById(id);
    }

    @Override
    public PageResult<OfftimeRuleDO> getOfftimeRulePage(OfftimeRulePageReqVO pageReqVO) {
        return offtimeRuleMapper.selectPage(pageReqVO);
    }

}
