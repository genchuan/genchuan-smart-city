package cn.iocoder.yudao.module.chargepark.marketop.service.cardmgmt.cardconfig;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardconfig.vo.CardConfigChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardconfig.vo.CardConfigCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardconfig.vo.CardConfigPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardconfig.vo.CardConfigUpdateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.CardConfigDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.cardmgmt.CardConfigMapper;
import cn.iocoder.yudao.module.chargepark.marketop.enums.CardConfigStatusEnum;
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
    public Long create(CardConfigCreateReqVO reqVO) {
        // 校验名称唯一
        validateNameUnique(null, reqVO.getName());
        CardConfigDO cardConfig = BeanUtils.toBean(reqVO, CardConfigDO.class);
        cardConfig.setStatus(CardConfigStatusEnum.NOT_EFFECTIVE.getValue());
        cardConfig.setSaleCount(0);
        cardConfigMapper.insert(cardConfig);
        return cardConfig.getId();
    }

    @Override
    public void update(CardConfigUpdateReqVO reqVO) {
        validateExists(reqVO.getId());
        CardConfigDO updateObj = BeanUtils.toBean(reqVO, CardConfigDO.class);
        cardConfigMapper.updateById(updateObj);
    }

    @Override
    public void enable(Long id) {
        CardConfigDO cardConfig = validateExists(id);
        if (!Objects.equals(CardConfigStatusEnum.NOT_EFFECTIVE.getValue(), cardConfig.getStatus())) {
            throw exception(CARD_CONFIG_NOT_EXISTS);
        }
        cardConfig.setStatus(CardConfigStatusEnum.EFFECTIVE.getValue());
        cardConfig.setAuditTime(LocalDateTime.now());
        cardConfig.setEffectTime(LocalDateTime.now());
        cardConfigMapper.updateById(cardConfig);
    }

    @Override
    public void disable(Long id) {
        CardConfigDO cardConfig = validateExists(id);
        if (!Objects.equals(CardConfigStatusEnum.EFFECTIVE.getValue(), cardConfig.getStatus())) {
            throw exception(CARD_CONFIG_NOT_EXISTS);
        }
        cardConfig.setStatus(CardConfigStatusEnum.NOT_EFFECTIVE.getValue());
        cardConfigMapper.updateById(cardConfig);
    }

    @Override
    public CardConfigChartRespVO getChart(Long startTime, Long endTime) {
        LocalDateTime startDateTime = startTime != null
                ? LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(startTime), ZoneId.systemDefault())
                : null;
        LocalDateTime endDateTime = endTime != null
                ? LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(endTime), ZoneId.systemDefault())
                : null;

        List<CardConfigDO> records = cardConfigMapper.selectListByTimeRange(startDateTime, endDateTime);

        CardConfigChartRespVO respVO = new CardConfigChartRespVO();
        // 生效卡种数 = 状态为1的记录数
        int enableCount = (int) records.stream().filter(r -> "1".equals(r.getStatus())).count();
        respVO.setEnableCount(enableCount);

        // 总数
        int saleCount = records.size();
        respVO.setSalesCount(saleCount);

        // type分组统计
        Map<String, Long> typeCountMap = records.stream()
                .filter(r -> r.getType() != null)
                .collect(Collectors.groupingBy(CardConfigDO::getType, Collectors.counting()));

        List<CardConfigChartRespVO.TypeRateItem> typeRatio = new ArrayList<>();
        typeCountMap.forEach((type, count) -> {
            CardConfigChartRespVO.TypeRateItem item = new CardConfigChartRespVO.TypeRateItem();
            item.setType(type);
            item.setRate(saleCount > 0
                    ? BigDecimal.valueOf(count).divide(BigDecimal.valueOf(saleCount), 4, RoundingMode.HALF_UP)
                    : BigDecimal.ZERO);
            typeRatio.add(item);
        });
        respVO.setTypeRatio(typeRatio);

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
