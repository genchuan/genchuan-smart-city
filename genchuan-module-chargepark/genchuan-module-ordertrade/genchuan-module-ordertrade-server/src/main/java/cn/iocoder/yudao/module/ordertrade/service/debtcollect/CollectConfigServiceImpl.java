package cn.iocoder.yudao.module.ordertrade.service.debtcollect;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.debtcollect.CollectConfigDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.debtcollect.CollectConfigMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.ordertrade.enums.ErrorCodeConstants.COLLECT_CONFIG_NOT_EXISTS;
import static cn.iocoder.yudao.module.ordertrade.enums.ErrorCodeConstants.COLLECT_CONFIG_NO_DUPLICATE;

/**
 * 追缴配置 Service 实现类
 * @author genchuan
 */
@Service
@Validated
public class CollectConfigServiceImpl implements CollectConfigService {

    @Resource private CollectConfigMapper collectConfigMapper;

    @Override public Long createCollectConfig(CollectConfigSaveReqVO v) {
        validateConfigNoUnique(v.getConfigNo(), null);
        CollectConfigDO o = BeanUtils.toBean(v, CollectConfigDO.class);
        collectConfigMapper.insert(o); return o.getId();
    }
    @Override public void updateCollectConfig(CollectConfigSaveReqVO v) {
        validateExists(v.getId());
        validateConfigNoUnique(v.getConfigNo(), v.getId());
        collectConfigMapper.updateById(BeanUtils.toBean(v, CollectConfigDO.class));
    }
    @Override public void deleteCollectConfig(Long id) { validateExists(id); collectConfigMapper.deleteById(id); }
    @Override public void deleteCollectConfigListByIds(List<Long> ids) { collectConfigMapper.deleteByIds(ids); }
    @Override public CollectConfigDO getCollectConfig(Long id) { return collectConfigMapper.selectById(id); }
    @Override public PageResult<CollectConfigDO> getCollectConfigPage(CollectConfigPageReqVO v) { return collectConfigMapper.selectPage(v); }
        @Override
    public CollectConfigChartRespVO getCollectConfigChart(CollectConfigChartReqVO v) {
        CollectConfigChartRespVO resp = new CollectConfigChartRespVO();
        resp.setTypeData(collectConfigMapper.selectGroupByCollectMethod());
        CollectConfigChartRespVO.CardData card = new CollectConfigChartRespVO.CardData();
        card.setEnableConfigCount(collectConfigMapper.selectCountByStatus("active").intValue());
        Long total  = collectConfigMapper.selectCountByStatus(null);
        Long active = collectConfigMapper.selectCountByStatus("active");
        if (total != null && total > 0) {
            card.setCollectTriggerRate(new BigDecimal(active).multiply(BigDecimal.valueOf(100))
                    .divide(new BigDecimal(total), 1, java.math.RoundingMode.HALF_UP));
        } else { card.setCollectTriggerRate(BigDecimal.ZERO); }
        resp.setCardData(card);
        return resp;
    }


    /** 生效（PUT /enable） */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enableCollectConfig(IdReqVO reqVO) {
        CollectConfigDO config = collectConfigMapper.selectById(reqVO.getId());
        if (config == null) throw exception(COLLECT_CONFIG_NOT_EXISTS);
        CollectConfigDO update = new CollectConfigDO();
        update.setId(reqVO.getId());
        update.setStatus("active");
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        collectConfigMapper.updateById(update);
    }

    /** 禁用（PUT /disable） */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disableCollectConfig(IdReqVO reqVO) {
        CollectConfigDO config = collectConfigMapper.selectById(reqVO.getId());
        if (config == null) throw exception(COLLECT_CONFIG_NOT_EXISTS);
        CollectConfigDO update = new CollectConfigDO();
        update.setId(reqVO.getId());
        update.setStatus("inactive");
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        collectConfigMapper.updateById(update);
    }

    private void validateExists(Long id) {
        if (collectConfigMapper.selectById(id) == null) throw exception(COLLECT_CONFIG_NOT_EXISTS);
    }

    private void validateConfigNoUnique(String configNo, Long excludeId) {
        CollectConfigDO existConfig = collectConfigMapper.selectByConfigNo(configNo, excludeId);
        if (existConfig != null) {
            throw exception(COLLECT_CONFIG_NO_DUPLICATE);
        }
    }
}
