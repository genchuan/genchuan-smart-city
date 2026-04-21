package cn.iocoder.yudao.module.ordertrade.service.splitsetttle;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.splitsetttle.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.splitsetttle.SplitRateDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.splitsetttle.SplitRateMapper;
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
        return splitRateMapper.selectById(id);
    }

    @Override
    public PageResult<SplitRateDO> getSplitRatePage(SplitRatePageReqVO pageReqVO) {
        return splitRateMapper.selectPage(pageReqVO);
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
        resp.setEnabledCount(splitRateMapper.selectEnabledCount());
        return resp;
    }

    private void validateExists(Long id) {
        if (splitRateMapper.selectById(id) == null) throw exception(SPLIT_RATE_NOT_EXISTS);
    }
}
