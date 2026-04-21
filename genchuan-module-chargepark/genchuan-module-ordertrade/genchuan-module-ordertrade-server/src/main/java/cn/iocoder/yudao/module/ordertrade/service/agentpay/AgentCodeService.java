package cn.iocoder.yudao.module.ordertrade.service.agentpay;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.agentpay.AgentCodeDO;

public interface AgentCodeService {

    Long createAgentCode(AgentCodeSaveReqVO createReqVO);

    void updateAgentCode(AgentCodeSaveReqVO updateReqVO);

    void deleteAgentCode(Long id);

    AgentCodeDO getAgentCode(Long id);

    PageResult<AgentCodeDO> getAgentCodePage(AgentCodePageReqVO pageReqVO);

    void generateAgentCode(AgentCodeGenerateReqVO reqVO);

    void refreshAgentCode(Long id);

    void regenerateAgentCode(Long id);

    AgentCodeChartRespVO getAgentCodeChart(AgentCodeChartReqVO chartReqVO);
}
