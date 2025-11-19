package cn.iocoder.yudao.module.datacenter.service.evaluate.rule.evalvetorule;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalvetorule.vo.EvalVetoRulePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalvetorule.vo.EvalVetoRuleSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.rule.evalvetorule.EvalVetoRuleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 否决项规则管理 Service 接口
 *
 * @author zcq
 */
public interface EvalVetoRuleService {

    /**
     * 创建否决项规则管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalVetoRule(@Valid EvalVetoRuleSaveReqVO createReqVO);

    /**
     * 更新否决项规则管理
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalVetoRule(@Valid EvalVetoRuleSaveReqVO updateReqVO);

    /**
     * 删除否决项规则管理
     *
     * @param id 编号
     */
    void deleteEvalVetoRule(Long id);

    /**
     * 获得否决项规则管理
     *
     * @param id 编号
     * @return 否决项规则管理
     */
    EvalVetoRuleDO getEvalVetoRule(Long id);

    /**
     * 获得否决项规则管理分页
     *
     * @param pageReqVO 分页查询
     * @return 否决项规则管理分页
     */
    PageResult<EvalVetoRuleDO> getEvalVetoRulePage(EvalVetoRulePageReqVO pageReqVO);

}