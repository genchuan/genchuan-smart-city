package cn.iocoder.yudao.module.ordertrade.service.agentpay;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo.*;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.agentpay.AgentRecordDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.agentpay.AgentRecordMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.ordertrade.enums.ErrorCodeConstants.*;

@Service
@Validated
public class AgentRecordServiceImpl implements AgentRecordService {

    @Resource
    private AgentRecordMapper agentRecordMapper;

    @Override
    public Long createAgentRecord(AgentRecordSaveReqVO createReqVO) {
        AgentRecordDO obj = BeanUtils.toBean(createReqVO, AgentRecordDO.class);
        obj.setRecordNo("AR" + UUID.randomUUID().toString().replace("-", "").substring(0, 16).toUpperCase());
        if (obj.getStatus() == null) {
            obj.setStatus("normal");
        }
        if (obj.getTradeTime() == null) {
            obj.setTradeTime(LocalDateTime.now());
        }
        agentRecordMapper.insert(obj);
        return obj.getId();
    }

    @Override
    public void updateAgentRecord(AgentRecordSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getId());
        agentRecordMapper.updateById(BeanUtils.toBean(updateReqVO, AgentRecordDO.class));
    }

    @Override
    public void deleteAgentRecord(Long id) {
        validateExists(id);
        agentRecordMapper.deleteById(id);
    }

    @Override
    public AgentRecordDO getAgentRecord(Long id) {
        return agentRecordMapper.selectById(id);
    }

    @Override
    public PageResult<AgentRecordRespVO> getAgentRecordPage(AgentRecordPageReqVO pageReqVO) {
        Page<AgentRecordRespVO> mpPage = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        var result = agentRecordMapper.selectPageWithMerchant(mpPage, pageReqVO);
        return new PageResult<>(result.getRecords(), result.getTotal());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkAgentRecord(IdReqVO reqVO) {
        AgentRecordDO record = agentRecordMapper.selectById(reqVO.getId());
        if (record == null) throw exception(AGENT_RECORD_NOT_EXISTS);
        AgentRecordDO update = new AgentRecordDO();
        update.setId(reqVO.getId());
        update.setCheckerId(SecurityFrameworkUtils.getLoginUserId());
        update.setCheckTime(LocalDateTime.now());
        update.setCheckResult(reqVO.getRemark() != null ? reqVO.getRemark() : "核查完成");
        agentRecordMapper.updateById(update);
    }

    @Override
    public AgentRecordChartRespVO getAgentRecordChart(AgentRecordChartReqVO chartReqVO) {
        AgentRecordChartRespVO resp = new AgentRecordChartRespVO();
        LocalDateTime start = chartReqVO.getStartTime() != null ? chartReqVO.getStartTime() : LocalDateTime.now().minusDays(30);
        LocalDateTime end = chartReqVO.getEndTime() != null ? chartReqVO.getEndTime() : LocalDateTime.now();
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime now = LocalDateTime.now();

        resp.setTrendData(agentRecordMapper.selectTrend(start, end));

        AgentRecordChartRespVO.CardData card = new AgentRecordChartRespVO.CardData();
        card.setTodayCount(agentRecordMapper.selectTodayCount(todayStart, now));

        Long normalCount = agentRecordMapper.selectNormalCount();
        Long totalCount = agentRecordMapper.selectTotalCount();
        if (totalCount != null && totalCount > 0) {
            card.setSuccessRate(new BigDecimal(normalCount).multiply(BigDecimal.valueOf(100))
                    .divide(new BigDecimal(totalCount), 1, RoundingMode.HALF_UP));
        } else {
            card.setSuccessRate(BigDecimal.ZERO);
        }
        resp.setCardData(card);
        return resp;
    }

    private void validateExists(Long id) {
        if (agentRecordMapper.selectById(id) == null) throw exception(AGENT_RECORD_NOT_EXISTS);
    }
}
