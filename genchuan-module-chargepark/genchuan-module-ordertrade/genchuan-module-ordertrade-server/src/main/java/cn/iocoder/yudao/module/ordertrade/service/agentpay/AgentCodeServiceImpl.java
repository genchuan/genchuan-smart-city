package cn.iocoder.yudao.module.ordertrade.service.agentpay;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.agentpay.AgentCodeDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.agentpay.AgentCodeMapper;
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
public class AgentCodeServiceImpl implements AgentCodeService {

    @Resource
    private AgentCodeMapper agentCodeMapper;

    @Override
    public Long createAgentCode(AgentCodeSaveReqVO createReqVO) {
        AgentCodeDO obj = BeanUtils.toBean(createReqVO, AgentCodeDO.class);
        obj.setCode(generateCode());
        if (obj.getStatus() == null) {
            obj.setStatus("unused");
        }
        agentCodeMapper.insert(obj);
        return obj.getId();
    }

    @Override
    public void updateAgentCode(AgentCodeSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getId());
        agentCodeMapper.updateById(BeanUtils.toBean(updateReqVO, AgentCodeDO.class));
    }

    @Override
    public void deleteAgentCode(Long id) {
        validateExists(id);
        agentCodeMapper.deleteById(id);
    }

    @Override
    public AgentCodeDO getAgentCode(Long id) {
        return agentCodeMapper.selectById(id);
    }

    @Override
    public PageResult<AgentCodeRespVO> getAgentCodePage(AgentCodePageReqVO pageReqVO) {
        Page<AgentCodeRespVO> mpPage = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        var result = agentCodeMapper.selectPageWithMerchant(mpPage, pageReqVO);
        return new PageResult<>(result.getRecords(), result.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void generateAgentCode(AgentCodeGenerateReqVO reqVO) {
        for (int i = 0; i < reqVO.getCount(); i++) {
            AgentCodeDO obj = new AgentCodeDO();
            obj.setMerchantId(reqVO.getMerchantId());
            obj.setRuleId(reqVO.getRuleId());
            obj.setExpireTime(reqVO.getExpireTime());
            obj.setRemark(reqVO.getRemark());
            obj.setCode(generateCode());
            obj.setStatus("unused");
            agentCodeMapper.insert(obj);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refreshAgentCode(Long id) {
        AgentCodeDO code = agentCodeMapper.selectById(id);
        if (code == null) throw exception(AGENT_CODE_NOT_EXISTS);
        AgentCodeDO update = new AgentCodeDO();
        update.setId(id);
        update.setExpireTime(LocalDateTime.now().plusDays(30));
        agentCodeMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void regenerateAgentCode(Long id) {
        AgentCodeDO code = agentCodeMapper.selectById(id);
        if (code == null) throw exception(AGENT_CODE_NOT_EXISTS);
        AgentCodeDO update = new AgentCodeDO();
        update.setId(id);
        update.setCode(generateCode());
        update.setStatus("unused");
        update.setExpireTime(LocalDateTime.now().plusDays(30));
        agentCodeMapper.updateById(update);
    }

    @Override
    public AgentCodeChartRespVO getAgentCodeChart(AgentCodeChartReqVO chartReqVO) {
        AgentCodeChartRespVO resp = new AgentCodeChartRespVO();
        // 默认查全量，注释掉30天限制
        // LocalDateTime start = chartReqVO.getStartTime() != null ? chartReqVO.getStartTime() : LocalDateTime.now().minusDays(30);
        LocalDateTime start = chartReqVO.getStartTime();
        LocalDateTime end = chartReqVO.getEndTime() != null ? chartReqVO.getEndTime() : LocalDateTime.now();
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime now = LocalDateTime.now();

        resp.setTrendData(agentCodeMapper.selectTrend(start, end));

        AgentCodeChartRespVO.CardData card = new AgentCodeChartRespVO.CardData();
        card.setTodayGeneratedCount(agentCodeMapper.selectGeneratedCount(todayStart, now));

        Long usedCount = agentCodeMapper.selectUsedCount();
        Long totalCount = agentCodeMapper.selectTotalCount();
        if (totalCount != null && totalCount > 0) {
            card.setUseRate(new BigDecimal(usedCount).multiply(BigDecimal.valueOf(100))
                    .divide(new BigDecimal(totalCount), 1, RoundingMode.HALF_UP));
        } else {
            card.setUseRate(BigDecimal.ZERO);
        }
        resp.setCardData(card);
        return resp;
    }

    private void validateExists(Long id) {
        if (agentCodeMapper.selectById(id) == null) throw exception(AGENT_CODE_NOT_EXISTS);
    }

    private String generateCode() {
        return "AC" + UUID.randomUUID().toString().replace("-", "").substring(0, 16).toUpperCase();
    }
}
