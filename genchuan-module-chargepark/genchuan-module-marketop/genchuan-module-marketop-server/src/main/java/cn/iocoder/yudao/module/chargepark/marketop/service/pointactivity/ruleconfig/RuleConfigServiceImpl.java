package cn.iocoder.yudao.module.chargepark.marketop.service.pointactivity.ruleconfig;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo.RuleConfigChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo.RuleConfigCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo.RuleConfigPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo.RuleConfigUpdateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.RuleConfigDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity.RuleConfigMapper;
import cn.iocoder.yudao.module.chargepark.marketop.enums.RuleConfigStatusEnum;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.service.impl.DiffParseFunction;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.LogRecordConstants.*;

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
    @LogRecord(type = RULE_CONFIG_TYPE, subType = RULE_CONFIG_CREATE_SUB_TYPE, bizNo = "{{#ruleConfig.id}}",
            success = RULE_CONFIG_CREATE_SUCCESS)
    public Long create(RuleConfigCreateReqVO reqVO) {
        validateNameUnique(null, reqVO.getName());
        RuleConfigDO ruleConfig = BeanUtils.toBean(reqVO, RuleConfigDO.class);
        ruleConfig.setStatus(RuleConfigStatusEnum.NOT_EFFECTIVE.getValue());
        ruleConfig.setMatchCount(0);
        ruleConfigMapper.insert(ruleConfig);
        // 记录操作日志上下文
        LogRecordContext.putVariable("ruleConfig", ruleConfig);
        return ruleConfig.getId();
    }

    @Override
    @LogRecord(type = RULE_CONFIG_TYPE, subType = RULE_CONFIG_UPDATE_SUB_TYPE, bizNo = "{{#reqVO.id}}",
            success = RULE_CONFIG_UPDATE_SUCCESS)
    public void update(RuleConfigUpdateReqVO reqVO) {
        RuleConfigDO ruleConfigDO = validateExists(reqVO.getId());
        if (reqVO.getName() != null) {
            validateNameUnique(reqVO.getId(), reqVO.getName());
        }
        RuleConfigDO updateObj = BeanUtils.toBean(reqVO, RuleConfigDO.class);
        ruleConfigMapper.updateById(updateObj);
        // 记录操作日志上下文
        LogRecordContext.putVariable(DiffParseFunction.OLD_OBJECT, BeanUtils.toBean(ruleConfigDO, RuleConfigUpdateReqVO.class));
        LogRecordContext.putVariable("ruleConfig", updateObj);
    }

    @Override
    @LogRecord(type = RULE_CONFIG_TYPE, subType = RULE_CONFIG_ENABLE_SUB_TYPE, bizNo = "{{#id}}",
            success = RULE_CONFIG_ENABLE_SUCCESS)
    public void enable(Long id) {
        RuleConfigDO ruleConfig = validateExists(id);
        if (!Objects.equals(RuleConfigStatusEnum.NOT_EFFECTIVE.getValue(), ruleConfig.getStatus())) {
            throw exception(RULE_CONFIG_NOT_EXISTS); // 状态不合法
        }
        ruleConfig.setStatus(RuleConfigStatusEnum.EFFECTIVE.getValue());
        ruleConfig.setAuditTime(LocalDateTime.now());
        ruleConfig.setEffectTime(LocalDateTime.now());
        ruleConfigMapper.updateById(ruleConfig);
        // 记录操作日志上下文
        LogRecordContext.putVariable("ruleConfigName", ruleConfig.getName());
    }

    @Override
    @LogRecord(type = RULE_CONFIG_TYPE, subType = RULE_CONFIG_DISABLE_SUB_TYPE, bizNo = "{{#id}}",
            success = RULE_CONFIG_DISABLE_SUCCESS)
    public void disable(Long id) {
        RuleConfigDO ruleConfig = validateExists(id);
        if (!Objects.equals(RuleConfigStatusEnum.EFFECTIVE.getValue(), ruleConfig.getStatus())) {
            throw exception(RULE_CONFIG_NOT_EXISTS);
        }
        ruleConfig.setStatus(RuleConfigStatusEnum.NOT_EFFECTIVE.getValue());
        ruleConfigMapper.updateById(ruleConfig);
        // 记录操作日志上下文
        LogRecordContext.putVariable("ruleConfigName", ruleConfig.getName());
    }

    @Override
    public RuleConfigChartRespVO getChart() {
        // enableCount = status = "1" 的总数
        Long enableCount = ruleConfigMapper.selectEnableCount();
        // matchRate = 生效规则的 gift_ratio 平均值
        BigDecimal matchRate = ruleConfigMapper.selectAvgGiftRatio();
        if (matchRate == null) {
            matchRate = BigDecimal.ZERO;
        }
        // typeList = 按 type 分组，计算占比
        List<Map<String, Object>> typeCountList = ruleConfigMapper.selectTypeCountList();
        long totalCount = typeCountList.stream().mapToLong(m -> ((Number) m.get("count")).longValue()).sum();
        List<RuleConfigChartRespVO.TypeRateItem> typeList = typeCountList.stream().map(m -> {
            RuleConfigChartRespVO.TypeRateItem item = new RuleConfigChartRespVO.TypeRateItem();
            item.setType((String) m.get("type"));
            long count = ((Number) m.get("count")).longValue();
            item.setRate(totalCount > 0 ? BigDecimal.valueOf(count * 100.0 / totalCount) : BigDecimal.ZERO);
            return item;
        }).toList();
        // typeCountList = 按 type 分组统计数量
        List<RuleConfigChartRespVO.TypeCountItem> typeCountItems = typeCountList.stream().map(m -> {
            RuleConfigChartRespVO.TypeCountItem item = new RuleConfigChartRespVO.TypeCountItem();
            item.setType((String) m.get("type"));
            item.setCount(((Number) m.get("count")).intValue());
            return item;
        }).toList();

        // sceneCountList = 按 scene 分组统计数量
        List<Map<String, Object>> sceneCountListData = ruleConfigMapper.selectSceneCountList();
        List<RuleConfigChartRespVO.SceneCountItem> sceneCountItems = sceneCountListData.stream().map(m -> {
            RuleConfigChartRespVO.SceneCountItem item = new RuleConfigChartRespVO.SceneCountItem();
            item.setScene((String) m.get("scene"));
            item.setCount(((Number) m.get("count")).intValue());
            return item;
        }).toList();

        RuleConfigChartRespVO respVO = new RuleConfigChartRespVO();
        respVO.setEnableCount(enableCount.intValue());
        respVO.setMatchRate(matchRate);
        respVO.setTypeList(typeList);
        respVO.setTypeCountList(typeCountItems);
        respVO.setSceneCountList(sceneCountItems);
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
