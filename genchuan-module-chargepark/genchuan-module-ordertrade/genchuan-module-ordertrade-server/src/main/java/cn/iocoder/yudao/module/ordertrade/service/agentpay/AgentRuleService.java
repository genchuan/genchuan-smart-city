package cn.iocoder.yudao.module.ordertrade.service.agentpay;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.agentpay.AgentRuleDO;

import java.util.List;

public interface AgentRuleService {

    Long createAgentRule(AgentRuleSaveReqVO createReqVO);

    void updateAgentRule(AgentRuleSaveReqVO updateReqVO);

    void deleteAgentRule(Long id);

    AgentRuleDO getAgentRule(Long id);

    PageResult<AgentRuleDO> getAgentRulePage(AgentRulePageReqVO pageReqVO);

    void importAgentRule(List<AgentRuleImportExcelVO> importList, boolean updateSupport);

    void enableAgentRule(Long id);

    void disableAgentRule(Long id);

    AgentRuleChartRespVO getAgentRuleChart(AgentRuleChartReqVO chartReqVO);
}
