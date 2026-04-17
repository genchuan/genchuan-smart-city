package cn.iocoder.yudao.module.chargepark.marketop.service.pointactivity.ruleconfig;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo.RuleConfigChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo.RuleConfigCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo.RuleConfigPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo.RuleConfigUpdateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.RuleConfigDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity.RuleConfigMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.ErrorCodeConstants.*;

@Service
@Validated
public class RuleConfigServiceImpl implements RuleConfigService {

    @Resource
    private RuleConfigMapper ruleConfigMapper;

    @Override
    public PageResult<RuleConfigDO> getPage(RuleConfigPageReqVO reqVO) {
        return ruleConfigMapper.selectPage(reqVO);
    }

    @Override
    public RuleConfigDO get(Long id) {
        return ruleConfigMapper.selectById(id);
    }

    @Override
    public Long create(RuleConfigCreateReqVO reqVO) {
        validateNameUnique(null, reqVO.getName());
        RuleConfigDO ruleConfig = BeanUtils.toBean(reqVO, RuleConfigDO.class);
        ruleConfig.setStatus("未生效");
        ruleConfig.setMatchCount(0);
        ruleConfigMapper.insert(ruleConfig);
        return ruleConfig.getId();
    }

    @Override
    public void update(RuleConfigUpdateReqVO reqVO) {
        validateExists(reqVO.getId());
        if (reqVO.getName() != null) {
            validateNameUnique(reqVO.getId(), reqVO.getName());
        }
        RuleConfigDO updateObj = BeanUtils.toBean(reqVO, RuleConfigDO.class);
        ruleConfigMapper.updateById(updateObj);
    }

    @Override
    public void enable(Long id) {
        RuleConfigDO ruleConfig = validateExists(id);
        if (!"未生效".equals(ruleConfig.getStatus())) {
            throw exception(RULE_CONFIG_NOT_EXISTS); // 状态不合法
        }
        ruleConfig.setStatus("已生效");
        ruleConfig.setAuditTime(LocalDateTime.now());
        ruleConfig.setEffectTime(LocalDateTime.now());
        ruleConfigMapper.updateById(ruleConfig);
    }

    @Override
    public void disable(Long id) {
        RuleConfigDO ruleConfig = validateExists(id);
        if (!"已生效".equals(ruleConfig.getStatus())) {
            throw exception(RULE_CONFIG_NOT_EXISTS);
        }
        ruleConfig.setStatus("未生效");
        ruleConfigMapper.updateById(ruleConfig);
    }

    @Override
    public RuleConfigChartRespVO getChart(String timeRange) {
        // TODO: 实现图表统计逻辑
        RuleConfigChartRespVO respVO = new RuleConfigChartRespVO();
        respVO.setEnableCount(0);
        respVO.setMatchRate(BigDecimal.ZERO);
        respVO.setTypeList(new ArrayList<>());
        return respVO;
    }

    private RuleConfigDO validateExists(Long id) {
        RuleConfigDO ruleConfig = ruleConfigMapper.selectById(id);
        if (ruleConfig == null) {
            throw exception(RULE_CONFIG_NOT_EXISTS);
        }
        return ruleConfig;
    }

    private void validateNameUnique(Long id, String name) {
        RuleConfigDO existing = ruleConfigMapper.selectOne(RuleConfigDO::getName, name);
        if (existing != null && !existing.getId().equals(id)) {
            throw exception(RULE_CONFIG_NAME_EXISTS);
        }
    }

}
