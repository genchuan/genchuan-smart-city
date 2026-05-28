package cn.iocoder.yudao.module.ordertrade.service.agentpay;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo.*;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.agentpay.AgentOrderDO;

import java.util.List;

public interface AgentOrderService {

    Long createAgentOrder(AgentOrderSaveReqVO createReqVO);

    void updateAgentOrder(AgentOrderSaveReqVO updateReqVO);

    void deleteAgentOrder(Long id);

    AgentOrderDO getAgentOrder(Long id);

    PageResult<AgentOrderDO> getAgentOrderPage(AgentOrderPageReqVO pageReqVO);

    List<AgentOrderDO> getAgentOrderByIds(List<Long> ids);

    void payAgentOrder(IdReqVO reqVO);

    void invoiceAgentOrder(AgentOrderInvoiceReqVO reqVO);

    void cancelAgentOrder(IdReqVO reqVO);

    AgentOrderChartRespVO getAgentOrderChart(AgentOrderChartReqVO chartReqVO);
}
