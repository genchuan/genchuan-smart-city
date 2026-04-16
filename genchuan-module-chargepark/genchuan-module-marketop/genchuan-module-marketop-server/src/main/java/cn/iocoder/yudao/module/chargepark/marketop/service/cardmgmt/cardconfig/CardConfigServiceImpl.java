package cn.iocoder.yudao.module.chargepark.marketop.service.cardmgmt.cardconfig;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardconfig.vo.CardConfigChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardconfig.vo.CardConfigCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardconfig.vo.CardConfigPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardconfig.vo.CardConfigUpdateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.CardConfigDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.cardmgmt.CardConfigMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.ArrayList;

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
        cardConfig.setStatus("未生效");
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
        if (!"未生效".equals(cardConfig.getStatus())) {
            throw exception(CARD_CONFIG_NOT_EXISTS);
        }
        cardConfig.setStatus("已生效");
        cardConfig.setAuditTime(LocalDateTime.now());
        cardConfig.setEffectTime(LocalDateTime.now());
        cardConfigMapper.updateById(cardConfig);
    }

    @Override
    public void disable(Long id) {
        CardConfigDO cardConfig = validateExists(id);
        if (!"已生效".equals(cardConfig.getStatus())) {
            throw exception(CARD_CONFIG_NOT_EXISTS);
        }
        cardConfig.setStatus("未生效");
        cardConfigMapper.updateById(cardConfig);
    }

    @Override
    public CardConfigChartRespVO getChart(String timeRange) {
        // TODO: 实现图表统计逻辑，暂时返回空数据
        CardConfigChartRespVO respVO = new CardConfigChartRespVO();
        respVO.setEnableCount(0);
        respVO.setSalesCount(0);
        respVO.setTypeList(new ArrayList<>());
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
