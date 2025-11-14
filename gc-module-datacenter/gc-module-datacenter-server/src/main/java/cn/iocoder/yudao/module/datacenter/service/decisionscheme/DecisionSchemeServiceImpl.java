package cn.iocoder.yudao.module.datacenter.service.decisionscheme;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.decisionscheme.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.decisionscheme.DecisionSchemeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.decisionscheme.DecisionSchemeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 决策方案生成 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class DecisionSchemeServiceImpl implements DecisionSchemeService {

    @Resource
    private DecisionSchemeMapper decisionSchemeMapper;

    @Override
    public Long createDecisionScheme(DecisionSchemeSaveReqVO createReqVO) {
        // 插入
        DecisionSchemeDO decisionScheme = BeanUtils.toBean(createReqVO, DecisionSchemeDO.class);
        decisionSchemeMapper.insert(decisionScheme);
        // 返回
        return decisionScheme.getId();
    }

    @Override
    public void updateDecisionScheme(DecisionSchemeSaveReqVO updateReqVO) {
        // 校验存在
        validateDecisionSchemeExists(updateReqVO.getId());
        // 更新
        DecisionSchemeDO updateObj = BeanUtils.toBean(updateReqVO, DecisionSchemeDO.class);
        decisionSchemeMapper.updateById(updateObj);
    }

    @Override
    public void deleteDecisionScheme(Long id) {
        // 校验存在
        validateDecisionSchemeExists(id);
        // 删除
        decisionSchemeMapper.deleteById(id);
    }

    private void validateDecisionSchemeExists(Long id) {
        if (decisionSchemeMapper.selectById(id) == null) {
            throw exception(DECISION_SCHEME_NOT_EXISTS);
        }
    }

    @Override
    public DecisionSchemeDO getDecisionScheme(Long id) {
        return decisionSchemeMapper.selectById(id);
    }

    @Override
    public PageResult<DecisionSchemeDO> getDecisionSchemePage(DecisionSchemePageReqVO pageReqVO) {
        return decisionSchemeMapper.selectPage(pageReqVO);
    }

}