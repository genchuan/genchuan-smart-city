package cn.iocoder.yudao.module.ordertrade.service.agentpay;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo.*;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.agentpay.AgentRecordDO;

public interface AgentRecordService {

    Long createAgentRecord(AgentRecordSaveReqVO createReqVO);

    void updateAgentRecord(AgentRecordSaveReqVO updateReqVO);

    void deleteAgentRecord(Long id);

    AgentRecordDO getAgentRecord(Long id);

    PageResult<AgentRecordRespVO> getAgentRecordPage(AgentRecordPageReqVO pageReqVO);

    void checkAgentRecord(IdReqVO reqVO);

    AgentRecordChartRespVO getAgentRecordChart(AgentRecordChartReqVO chartReqVO);
}
