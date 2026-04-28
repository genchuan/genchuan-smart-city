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
        cardConfig.setStatus(CardConfigStatusEnum.EFFECTIVE.getValue());
        cardConfig.setAuditTime(LocalDateTime.now());
        cardConfig.setEffectTime(LocalDateTime.now());
        cardConfigMapper.updateById(cardConfig);
    }

    @Override
    public void disable(Long id) {
        CardConfigDO cardConfig = validateExists(id);
        cardConfig.setStatus(CardConfigStatusEnum.NOT_EFFECTIVE.getValue());
        cardConfigMapper.updateById(cardConfig);
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
        List<CardConfigChartRespVO.TypeCountItem> typeCountItems = typeCountList.stream().map(m -> {
            CardConfigChartRespVO.TypeCountItem item = new CardConfigChartRespVO.TypeCountItem();
            item.setCardType((String) m.get("type"));
            item.setCount(((Number) m.get("count")).intValue());
            return item;
        }).collect(Collectors.toList());
        respVO.setTypeCountList(typeCountItems);

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
