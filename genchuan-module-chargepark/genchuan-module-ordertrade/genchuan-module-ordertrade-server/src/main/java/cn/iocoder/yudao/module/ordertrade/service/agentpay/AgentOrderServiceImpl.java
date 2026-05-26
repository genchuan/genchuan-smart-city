package cn.iocoder.yudao.module.ordertrade.service.agentpay;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo.*;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.agentpay.AgentOrderDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.agentpay.AgentOrderMapper;
import cn.iocoder.yudao.module.ordertrade.framework.tool.OrderUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.ordertrade.enums.ErrorCodeConstants.*;

@Service
@Validated
public class AgentOrderServiceImpl implements AgentOrderService {

    @Resource
    private AgentOrderMapper agentOrderMapper;

    @Override
    public Long createAgentOrder(AgentOrderSaveReqVO createReqVO) {
        AgentOrderDO obj = BeanUtils.toBean(createReqVO, AgentOrderDO.class);
        obj.setOrderNo(OrderUtils.generateOrderNo());
        if (obj.getStatus() == null) {
            obj.setStatus("pending_pay");
        }
        agentOrderMapper.insert(obj);
        return obj.getId();
    }

    @Override
    public void updateAgentOrder(AgentOrderSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getId());
        agentOrderMapper.updateById(BeanUtils.toBean(updateReqVO, AgentOrderDO.class));
    }

    @Override
    public void deleteAgentOrder(Long id) {
        validateExists(id);
        agentOrderMapper.deleteById(id);
    }

    @Override
    public AgentOrderDO getAgentOrder(Long id) {
        return agentOrderMapper.selectByIdWithMerchant(id);
    }

    @Override
    public PageResult<AgentOrderDO> getAgentOrderPage(AgentOrderPageReqVO pageReqVO) {
        Page<AgentOrderDO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        IPage<AgentOrderDO> result = agentOrderMapper.selectPageWithMerchant(page, pageReqVO);
        return new PageResult<>(result.getRecords(), result.getTotal());
    }

    @Override
    public List<AgentOrderDO> getAgentOrderByIds(List<Long> ids) {
        return agentOrderMapper.selectBatchIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void payAgentOrder(IdReqVO reqVO) {
        AgentOrderDO order = agentOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(AGENT_ORDER_NOT_EXISTS);
        if (!"pending_pay".equals(order.getStatus())) throw exception(AGENT_ORDER_STATUS_CANNOT_PAY);
        AgentOrderDO update = new AgentOrderDO();
        update.setId(reqVO.getId());
        update.setStatus("paid");
        update.setPayTime(LocalDateTime.now());
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        agentOrderMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void invoiceAgentOrder(IdReqVO reqVO) {
        AgentOrderDO order = agentOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(AGENT_ORDER_NOT_EXISTS);
        if (!"paid".equals(order.getStatus()) && !"completed".equals(order.getStatus())) {
            throw exception(AGENT_ORDER_STATUS_CANNOT_INVOICE);
        }
        // 开票操作，实际由发票模块处理
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelAgentOrder(IdReqVO reqVO) {
        AgentOrderDO order = agentOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(AGENT_ORDER_NOT_EXISTS);
        if (!"pending_pay".equals(order.getStatus())) throw exception(AGENT_ORDER_STATUS_CANNOT_CANCEL);
        AgentOrderDO update = new AgentOrderDO();
        update.setId(reqVO.getId());
        update.setStatus("cancelled");
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        agentOrderMapper.updateById(update);
    }

    @Override
    public AgentOrderChartRespVO getAgentOrderChart(AgentOrderChartReqVO chartReqVO) {
        AgentOrderChartRespVO resp = new AgentOrderChartRespVO();
        LocalDateTime start = chartReqVO.getStartTime() != null ? chartReqVO.getStartTime() : LocalDateTime.now().minusDays(30);
        LocalDateTime end = chartReqVO.getEndTime() != null ? chartReqVO.getEndTime() : LocalDateTime.now();
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime now = LocalDateTime.now();

        resp.setTrendData(agentOrderMapper.selectTrend(start, end));
        AgentOrderChartRespVO.CardData card = new AgentOrderChartRespVO.CardData();
        card.setTodayOrderCount(agentOrderMapper.selectTodayCount(todayStart, now));
        card.setTodayAmount(agentOrderMapper.selectTodayAmount(todayStart, now));
        resp.setCardData(card);
        return resp;
    }

    private void validateExists(Long id) {
        if (agentOrderMapper.selectById(id) == null) throw exception(AGENT_ORDER_NOT_EXISTS);
    }
}
