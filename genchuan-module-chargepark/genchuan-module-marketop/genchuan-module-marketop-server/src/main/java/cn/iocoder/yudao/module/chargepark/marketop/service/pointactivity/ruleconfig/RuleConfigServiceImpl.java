package cn.iocoder.yudao.module.chargepark.marketop.service.pointactivity.ruleconfig;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo.RuleConfigChartReqVO;
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
import java.util.List;
import java.util.Map;

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
        ruleConfig.setStatus("0");
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
        if (!"0".equals(ruleConfig.getStatus())) {
            throw exception(RULE_CONFIG_NOT_EXISTS); // 状态不合法
        }
        ruleConfig.setStatus("1");
        ruleConfig.setAuditTime(LocalDateTime.now());
        ruleConfig.setEffectTime(LocalDateTime.now());
        ruleConfigMapper.updateById(ruleConfig);
    }

    @Override
    public void disable(Long id) {
        RuleConfigDO ruleConfig = validateExists(id);
        if (!"1".equals(ruleConfig.getStatus())) {
            throw exception(RULE_CONFIG_NOT_EXISTS);
        }
        ruleConfig.setStatus("0");
        ruleConfigMapper.updateById(ruleConfig);
    }

    @Override
    public RuleConfigChartRespVO getChart(RuleConfigChartReqVO reqVO) {
        // enableCount = status = "1" 的总数
        Long enableCount = ruleConfigMapper.selectEnableCount(reqVO);
        // matchRate = 生效规则的 gift_ratio 平均值
        BigDecimal matchRate = ruleConfigMapper.selectAvgGiftRatio(reqVO);
        if (matchRate == null) {
            matchRate = BigDecimal.ZERO;
        }
        // typeList = 按 type 分组，计算占比
        List<Map<String, Object>> typeCountList = ruleConfigMapper.selectTypeCountList(reqVO);
        long totalCount = typeCountList.stream().mapToLong(m -> ((Number) m.get("count")).longValue()).sum();
        List<RuleConfigChartRespVO.TypeRateItem> typeList = typeCountList.stream().map(m -> {
            RuleConfigChartRespVO.TypeRateItem item = new RuleConfigChartRespVO.TypeRateItem();
            item.setType((String) m.get("type"));
            long count = ((Number) m.get("count")).longValue();
            item.setRate(totalCount > 0 ? BigDecimal.valueOf(count * 100.0 / totalCount) : BigDecimal.ZERO);
            return item;
        }).toList();
        RuleConfigChartRespVO respVO = new RuleConfigChartRespVO();
        respVO.setEnableCount(enableCount.intValue());
        respVO.setMatchRate(matchRate);
        respVO.setTypeList(typeList);
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
