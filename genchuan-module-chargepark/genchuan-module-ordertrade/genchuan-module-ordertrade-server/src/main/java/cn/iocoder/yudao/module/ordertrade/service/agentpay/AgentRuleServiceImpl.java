package cn.iocoder.yudao.module.ordertrade.service.agentpay;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.agentpay.AgentRuleDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.agentpay.AgentRuleMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserNickname;
import static cn.iocoder.yudao.module.ordertrade.enums.ErrorCodeConstants.*;

@Service
@Validated
public class AgentRuleServiceImpl implements AgentRuleService {

    @Resource
    private AgentRuleMapper agentRuleMapper;

    @Override
    public Long createAgentRule(AgentRuleSaveReqVO createReqVO) {
        validateMerchantTypeUnique(createReqVO.getMerchantId(), createReqVO.getAgentType(), null);
        AgentRuleDO obj = BeanUtils.toBean(createReqVO, AgentRuleDO.class);
        if (obj.getStatus() == null || obj.getStatus().equals("")) {
            obj.setStatus("pending");
        }
        if (obj.getUseCount() == null) {
            obj.setUseCount(0);
        }
        String loginUserNickname = getLoginUserNickname();
        obj.setCreator(loginUserNickname != null ? loginUserNickname : "");
        obj.setUpdater(obj.getCreator());
        obj.setCreateTime(LocalDateTime.now());
        obj.setUpdateTime(LocalDateTime.now());
        agentRuleMapper.insert(obj);
        return obj.getId();
    }

    @Override
    public void updateAgentRule(AgentRuleSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getId());
        validateMerchantTypeUnique(updateReqVO.getMerchantId(), updateReqVO.getAgentType(), updateReqVO.getId());
        AgentRuleDO obj = BeanUtils.toBean(updateReqVO, AgentRuleDO.class);
        obj.setLastUpdateTime(LocalDateTime.now());
        agentRuleMapper.updateById(obj);
    }

    private void validateMerchantTypeUnique(Long merchantId, String agentType, Long excludeId) {
        AgentRuleDO existRule = agentRuleMapper.selectByMerchantIdAndAgentType(merchantId, agentType, excludeId);
        if (existRule != null) {
            throw exception(AGENT_RULE_MERCHANT_TYPE_DUPLICATE);
        }
    }

    @Override
    public void deleteAgentRule(Long id) {
        validateExists(id);
        agentRuleMapper.deleteById(id);
    }

    @Override
    public AgentRuleDO getAgentRule(Long id) {
        return agentRuleMapper.selectById(id);
    }

    @Override
    public PageResult<AgentRuleRespVO> getAgentRulePage(AgentRulePageReqVO pageReqVO) {
        Page<AgentRuleRespVO> mpPage = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        var result = agentRuleMapper.selectPageWithMerchant(mpPage, pageReqVO);
        return new PageResult<>(result.getRecords(), result.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importAgentRule(List<AgentRuleImportExcelVO> importList, boolean updateSupport) {
        if (CollectionUtils.isEmpty(importList)) {
            return;
        }
        for (AgentRuleImportExcelVO vo : importList) {
            if (vo.getId() != null) {
                AgentRuleDO existDO = agentRuleMapper.selectById(vo.getId());
                if (existDO != null) {
                    if (updateSupport) {
                        AgentRuleDO updateDO = BeanUtils.toBean(vo, AgentRuleDO.class);
                        updateDO.setLastUpdateTime(LocalDateTime.now());
                        agentRuleMapper.updateById(updateDO);
                    }
                    // updateSupport=false 时跳过已存在记录
                } else {
                    // ID 不存在，忽略提供的 ID 按新增处理
                    AgentRuleDO insertDO = buildInsertDO(vo);
                    agentRuleMapper.insert(insertDO);
                }
            } else {
                AgentRuleDO insertDO = buildInsertDO(vo);
                agentRuleMapper.insert(insertDO);
            }
        }
    }

    private AgentRuleDO buildInsertDO(AgentRuleImportExcelVO vo) {
        AgentRuleDO obj = BeanUtils.toBean(vo, AgentRuleDO.class);
        obj.setId(null);
        if (obj.getStatus() == null || obj.getStatus().isEmpty()) {
            obj.setStatus("pending");
        }
        if (obj.getUseCount() == null) {
            obj.setUseCount(0);
        }
        obj.setCreateTime(LocalDateTime.now());
        return obj;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enableAgentRule(Long id) {
        AgentRuleDO rule = agentRuleMapper.selectById(id);
        if (rule == null) throw exception(AGENT_RULE_NOT_EXISTS);
        if (!"pending".equals(rule.getStatus()) && !"disabled".equals(rule.getStatus())) {
            throw exception(AGENT_RULE_STATUS_CANNOT_ENABLE);
        }
        AgentRuleDO update = new AgentRuleDO();
        update.setId(id);
        update.setStatus("enabled");
        update.setLastUpdateTime(LocalDateTime.now());
        agentRuleMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disableAgentRule(Long id) {
        AgentRuleDO rule = agentRuleMapper.selectById(id);
        if (rule == null) throw exception(AGENT_RULE_NOT_EXISTS);
        if (!"enabled".equals(rule.getStatus())) {
            throw exception(AGENT_RULE_STATUS_CANNOT_DISABLE);
        }
        AgentRuleDO update = new AgentRuleDO();
        update.setId(id);
        update.setStatus("disabled");
        update.setLastUpdateTime(LocalDateTime.now());
        agentRuleMapper.updateById(update);
    }

    @Override
    public AgentRuleChartRespVO getAgentRuleChart(AgentRuleChartReqVO chartReqVO) {
        AgentRuleChartRespVO resp = new AgentRuleChartRespVO();
        // 默认查全量，注释掉30天限制
        // LocalDateTime start = chartReqVO.getStartTime() != null ? chartReqVO.getStartTime() : LocalDateTime.now().minusDays(30);
        LocalDateTime start = chartReqVO.getStartTime();
        LocalDateTime end = chartReqVO.getEndTime() != null ? chartReqVO.getEndTime() : LocalDateTime.now();
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime now = LocalDateTime.now();

        resp.setUseDistData(agentRuleMapper.selectGroupByAgentType());
        AgentRuleChartRespVO.CardData card = new AgentRuleChartRespVO.CardData();
        card.setEnabledCount(agentRuleMapper.selectEnabledCount());
        card.setTodayOrderCount(agentRuleMapper.selectTodayCount(todayStart, now));
        resp.setCardData(card);
        return resp;
    }

    private void validateExists(Long id) {
        if (agentRuleMapper.selectById(id) == null) throw exception(AGENT_RULE_NOT_EXISTS);
    }
}
