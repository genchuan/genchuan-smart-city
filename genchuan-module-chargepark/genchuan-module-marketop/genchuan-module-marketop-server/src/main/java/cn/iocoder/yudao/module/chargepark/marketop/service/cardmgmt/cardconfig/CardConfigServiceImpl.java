package cn.iocoder.yudao.module.chargepark.marketop.service.cardmgmt.cardconfig;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardconfig.vo.CardConfigChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardconfig.vo.CardConfigCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardconfig.vo.CardConfigPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardconfig.vo.CardConfigUpdateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.CardConfigDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.cardmgmt.CardConfigMapper;
import cn.iocoder.yudao.module.chargepark.marketop.enums.CardConfigStatusEnum;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.service.impl.DiffParseFunction;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.LogRecordConstants.CARD_CONFIG_CREATE_SUB_TYPE;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.LogRecordConstants.CARD_CONFIG_CREATE_SUCCESS;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.LogRecordConstants.CARD_CONFIG_DISABLE_SUB_TYPE;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.LogRecordConstants.CARD_CONFIG_DISABLE_SUCCESS;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.LogRecordConstants.CARD_CONFIG_ENABLE_SUB_TYPE;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.LogRecordConstants.CARD_CONFIG_ENABLE_SUCCESS;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.LogRecordConstants.CARD_CONFIG_TYPE;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.LogRecordConstants.CARD_CONFIG_UPDATE_SUB_TYPE;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.LogRecordConstants.CARD_CONFIG_UPDATE_SUCCESS;

@Service
@Validated
public class CardConfigServiceImpl implements CardConfigService {

    @Resource
    private CardConfigMapper cardConfigMapper;

    @Override
    public PageResult<CardConfigDO> getPage(CardConfigPageReqVO reqVO) {
        return cardConfigMapper.selectPage(reqVO);
    }

    @Override
    public CardConfigDO get(Long id) {
        return cardConfigMapper.selectById(id);
    }

    @Override
    @LogRecord(type = CARD_CONFIG_TYPE, subType = CARD_CONFIG_CREATE_SUB_TYPE, bizNo = "{{#cardConfig.id}}",
            success = CARD_CONFIG_CREATE_SUCCESS)
    public Long create(CardConfigCreateReqVO reqVO) {
        // 校验名称唯一
        validateNameUnique(null, reqVO.getName());
        CardConfigDO cardConfig = BeanUtils.toBean(reqVO, CardConfigDO.class);
        cardConfig.setStatus(CardConfigStatusEnum.NOT_EFFECTIVE.getValue());
        cardConfig.setSaleCount(0);
        cardConfigMapper.insert(cardConfig);

        // 记录操作日志上下文
        LogRecordContext.putVariable("cardConfig", cardConfig);
        return cardConfig.getId();
    }

    @Override
    @LogRecord(type = CARD_CONFIG_TYPE, subType = CARD_CONFIG_UPDATE_SUB_TYPE, bizNo = "{{#reqVO.id}}",
            success = CARD_CONFIG_UPDATE_SUCCESS)
    public void update(CardConfigUpdateReqVO reqVO) {
        CardConfigDO cardConfigDO = validateExists(reqVO.getId());
        CardConfigDO updateObj = BeanUtils.toBean(reqVO, CardConfigDO.class);
        cardConfigMapper.updateById(updateObj);

        // 3. 记录操作日志上下文
        LogRecordContext.putVariable(DiffParseFunction.OLD_OBJECT, BeanUtils.toBean(cardConfigDO, CardConfigUpdateReqVO.class));
        LogRecordContext.putVariable("cardConfig", updateObj);
    }

    @Override
    @LogRecord(type = CARD_CONFIG_TYPE, subType = CARD_CONFIG_ENABLE_SUB_TYPE, bizNo = "{{#id}}",
            success = CARD_CONFIG_ENABLE_SUCCESS)
    public void enable(Long id) {
        CardConfigDO cardConfig = validateExists(id);
        cardConfig.setStatus(CardConfigStatusEnum.EFFECTIVE.getValue());
        cardConfig.setAuditTime(LocalDateTime.now());
        cardConfig.setEffectTime(LocalDateTime.now());
        cardConfigMapper.updateById(cardConfig);
        // 记录操作日志上下文
        LogRecordContext.putVariable("cardConfigName", cardConfig.getName());
    }

    @Override
    @LogRecord(type = CARD_CONFIG_TYPE, subType = CARD_CONFIG_DISABLE_SUB_TYPE, bizNo = "{{#id}}",
            success = CARD_CONFIG_DISABLE_SUCCESS)
    public void disable(Long id) {
        CardConfigDO cardConfig = validateExists(id);
        cardConfig.setStatus(CardConfigStatusEnum.NOT_EFFECTIVE.getValue());
        cardConfigMapper.updateById(cardConfig);
        // 记录操作日志上下文
        LogRecordContext.putVariable("cardConfigName", cardConfig.getName());
    }

    @Override
    public List<CardConfigDO> getSimpleList() {
        return cardConfigMapper.selectList();
    }

    @Override
    public CardConfigChartRespVO getChart() {
        CardConfigChartRespVO respVO = new CardConfigChartRespVO();

        // 生效卡种数 = 状态为1的记录数
        Long enableCount = cardConfigMapper.selectCount(new LambdaQueryWrapperX<CardConfigDO>()
                .eq(CardConfigDO::getStatus, "1"));
        respVO.setEnableCount(enableCount != null ? enableCount.intValue() : 0);

        // 总数
        Long totalCount = cardConfigMapper.selectCount(new LambdaQueryWrapperX<>());
        respVO.setSalesCount(totalCount != null ? totalCount.intValue() : 0);

        // type分组统计
        List<Map<String, Object>> typeCountList = cardConfigMapper.selectTypeCountList();
        long sum = typeCountList.stream().mapToLong(m -> ((Number) m.get("count")).longValue()).sum();

        List<CardConfigChartRespVO.TypeRateItem> typeRatio = typeCountList.stream().map(m -> {
            CardConfigChartRespVO.TypeRateItem item = new CardConfigChartRespVO.TypeRateItem();
            item.setType((String) m.get("type"));
            long count = ((Number) m.get("count")).longValue();
            item.setRate(sum > 0
                    ? BigDecimal.valueOf(count).divide(BigDecimal.valueOf(sum), 4, RoundingMode.HALF_UP)
                    : BigDecimal.ZERO);
            return item;
        }).collect(Collectors.toList());
        respVO.setTypeRatio(typeRatio);

        // typeCountList: 按type分组统计数量
        List<Map<String, Object>> scopeCountList = cardConfigMapper.selectScopeCountList();
        List<CardConfigChartRespVO.ScopeCountItem> typeCountItems = scopeCountList.stream().map(m -> {
            CardConfigChartRespVO.ScopeCountItem item = new CardConfigChartRespVO.ScopeCountItem();
            item.setScope((String) m.get("scope"));
            item.setCount(((Number) m.get("count")).intValue());
            return item;
        }).collect(Collectors.toList());
        respVO.setScopeCountList(typeCountItems);

        return respVO;
    }

    private CardConfigDO validateExists(Long id) {
        CardConfigDO cardConfig = cardConfigMapper.selectById(id);
        if (cardConfig == null) {
            throw exception(CARD_CONFIG_NOT_EXISTS);
        }
        return cardConfig;
    }

    private void validateNameUnique(Long id, String name) {
        CardConfigDO existing = cardConfigMapper.selectOne(CardConfigDO::getName, name);
        if (existing != null && !existing.getId().equals(id)) {
            throw exception(CARD_CONFIG_NAME_EXISTS);
        }
    }

}
