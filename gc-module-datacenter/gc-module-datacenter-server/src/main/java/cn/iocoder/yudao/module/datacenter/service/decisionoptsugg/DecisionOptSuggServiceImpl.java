package cn.iocoder.yudao.module.datacenter.service.decisionoptsugg;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.decisionoptsugg.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.decisionoptsugg.DecisionOptSuggDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.decisionoptsugg.DecisionOptSuggMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 决策优化建议 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class DecisionOptSuggServiceImpl implements DecisionOptSuggService {

    @Resource
    private DecisionOptSuggMapper decisionOptSuggMapper;

    @Override
    public Long createDecisionOptSugg(DecisionOptSuggSaveReqVO createReqVO) {
        // 插入
        DecisionOptSuggDO decisionOptSugg = BeanUtils.toBean(createReqVO, DecisionOptSuggDO.class);
        decisionOptSuggMapper.insert(decisionOptSugg);
        // 返回
        return decisionOptSugg.getId();
    }

    @Override
    public void updateDecisionOptSugg(DecisionOptSuggSaveReqVO updateReqVO) {
        // 校验存在
        validateDecisionOptSuggExists(updateReqVO.getId());
        // 更新
        DecisionOptSuggDO updateObj = BeanUtils.toBean(updateReqVO, DecisionOptSuggDO.class);
        decisionOptSuggMapper.updateById(updateObj);
    }

    @Override
    public void deleteDecisionOptSugg(Long id) {
        // 校验存在
        validateDecisionOptSuggExists(id);
        // 删除
        decisionOptSuggMapper.deleteById(id);
    }

    private void validateDecisionOptSuggExists(Long id) {
        if (decisionOptSuggMapper.selectById(id) == null) {
            throw exception(DECISION_OPT_SUGG_NOT_EXISTS);
        }
    }

    @Override
    public DecisionOptSuggDO getDecisionOptSugg(Long id) {
        return decisionOptSuggMapper.selectById(id);
    }

    @Override
    public PageResult<DecisionOptSuggDO> getDecisionOptSuggPage(DecisionOptSuggPageReqVO pageReqVO) {
        return decisionOptSuggMapper.selectPage(pageReqVO);
    }

}