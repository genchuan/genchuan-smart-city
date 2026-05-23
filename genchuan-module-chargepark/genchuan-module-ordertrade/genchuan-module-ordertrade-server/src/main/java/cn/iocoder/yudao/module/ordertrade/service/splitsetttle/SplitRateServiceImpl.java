package cn.iocoder.yudao.module.ordertrade.service.splitsetttle;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.splitsetttle.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.splitsetttle.SplitRateDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.splitsetttle.SplitRateMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.ordertrade.enums.ErrorCodeConstants.*;

@Service
@Validated
public class SplitRateServiceImpl implements SplitRateService {

    @Resource
    private SplitRateMapper splitRateMapper;

    @Override
    public Long createSplitRate(SplitRateSaveReqVO createReqVO) {
        if (createReqVO.getPartnerId() != null && splitRateMapper.existsByPartnerId(createReqVO.getPartnerId())) {
            throw exception(SPLIT_RATE_PARTNER_ID_DUPLICATE);
        }
        SplitRateDO obj = BeanUtils.toBean(createReqVO, SplitRateDO.class);
        if (obj.getStatus() == null) {
            obj.setStatus("pending");
        }
        splitRateMapper.insert(obj);
        return obj.getId();
    }

    @Override
    public void updateSplitRate(SplitRateSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getId());
        splitRateMapper.updateById(BeanUtils.toBean(updateReqVO, SplitRateDO.class));
    }

    @Override
    public void deleteSplitRate(Long id) {
        validateExists(id);
        splitRateMapper.deleteById(id);
    }

    @Override
    public SplitRateDO getSplitRate(Long id) {
        return splitRateMapper.selectByIdWithPartner(id);
    }

    @Override
    public PageResult<SplitRateDO> getSplitRatePage(SplitRatePageReqVO pageReqVO) {
        Page<SplitRateDO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        var result = splitRateMapper.selectPageWithPartner(page, pageReqVO);
        return new PageResult<>(result.getRecords(), result.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enableSplitRate(Long id) {
        SplitRateDO rate = splitRateMapper.selectById(id);
        if (rate == null) throw exception(SPLIT_RATE_NOT_EXISTS);
        if (!"pending".equals(rate.getStatus()) && !"disabled".equals(rate.getStatus())) {
            throw exception(SPLIT_RATE_STATUS_CANNOT_ENABLE);
        }
        SplitRateDO update = new SplitRateDO();
        update.setId(id);
        update.setStatus("enabled");
        splitRateMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disableSplitRate(Long id) {
        SplitRateDO rate = splitRateMapper.selectById(id);
        if (rate == null) throw exception(SPLIT_RATE_NOT_EXISTS);
        if (!"enabled".equals(rate.getStatus())) throw exception(SPLIT_RATE_STATUS_CANNOT_DISABLE);
        SplitRateDO update = new SplitRateDO();
        update.setId(id);
        update.setStatus("disabled");
        splitRateMapper.updateById(update);
    }

    @Override
    public SplitRateChartRespVO getSplitRateChart(SplitRateChartReqVO chartReqVO) {
        SplitRateChartRespVO resp = new SplitRateChartRespVO();
        resp.setSplitModeData(splitRateMapper.selectGroupBySplitMode());
        SplitRateChartRespVO.CardData card = new SplitRateChartRespVO.CardData();
        card.setEnabledCount(splitRateMapper.selectEnabledCount());
        resp.setCardData(card);
        return resp;
    }

    private void validateExists(Long id) {
        if (splitRateMapper.selectById(id) == null) throw exception(SPLIT_RATE_NOT_EXISTS);
    }
}
