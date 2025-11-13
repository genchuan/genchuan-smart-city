package cn.iocoder.yudao.module.datacenter.service.decisioneffecteval;

import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.decisioneffecteval.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.decisioneffecteval.DecisionEffectEvalDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 决策效果评估 Service 接口
 *
 * @author 亘川智城
 */
public interface DecisionEffectEvalService {

    /**
     * 创建决策效果评估
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDecisionEffectEval(@Valid DecisionEffectEvalSaveReqVO createReqVO);

    /**
     * 更新决策效果评估
     *
     * @param updateReqVO 更新信息
     */
    void updateDecisionEffectEval(@Valid DecisionEffectEvalSaveReqVO updateReqVO);

    /**
     * 删除决策效果评估
     *
     * @param id 编号
     */
    void deleteDecisionEffectEval(Long id);

    /**
     * 获得决策效果评估
     *
     * @param id 编号
     * @return 决策效果评估
     */
    DecisionEffectEvalDO getDecisionEffectEval(Long id);

    /**
     * 获得决策效果评估分页
     *
     * @param pageReqVO 分页查询
     * @return 决策效果评估分页
     */
    PageResult<DecisionEffectEvalDO> getDecisionEffectEvalPage(DecisionEffectEvalPageReqVO pageReqVO);

}