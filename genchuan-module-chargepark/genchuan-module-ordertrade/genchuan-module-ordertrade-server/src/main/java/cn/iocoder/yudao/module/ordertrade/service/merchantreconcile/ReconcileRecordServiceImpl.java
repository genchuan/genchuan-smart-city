package cn.iocoder.yudao.module.ordertrade.service.merchantreconcile;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.merchantreconcile.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.merchantreconcile.ReconcileRecordDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.merchantreconcile.ReconcileRecordMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.ordertrade.enums.ErrorCodeConstants.*;

@Service
@Validated
public class ReconcileRecordServiceImpl implements ReconcileRecordService {

    @Resource
    private ReconcileRecordMapper reconcileRecordMapper;

    @Override
    public Long createReconcileRecord(ReconcileRecordSaveReqVO createReqVO) {
        ReconcileRecordDO obj = BeanUtils.toBean(createReqVO, ReconcileRecordDO.class);
        if (obj.getDiffAmount() == null && obj.getSysAmount() != null && obj.getMerchantAmount() != null) {
            obj.setDiffAmount(obj.getSysAmount().subtract(obj.getMerchantAmount()));
        }
        if (obj.getMatchResult() == null) {
            obj.setMatchResult("unmatched");
        }
        reconcileRecordMapper.insert(obj);
        return obj.getId();
    }

    @Override
    public void updateReconcileRecord(ReconcileRecordSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getId());
        ReconcileRecordDO update = BeanUtils.toBean(updateReqVO, ReconcileRecordDO.class);
        if (update.getSysAmount() != null && update.getMerchantAmount() != null) {
            update.setDiffAmount(update.getSysAmount().subtract(update.getMerchantAmount()));
        }
        reconcileRecordMapper.updateById(update);
    }

    @Override
    public void deleteReconcileRecord(Long id) {
        validateExists(id);
        reconcileRecordMapper.deleteById(id);
    }

    @Override
    public ReconcileRecordDO getReconcileRecord(Long id) {
        return reconcileRecordMapper.selectById(id);
    }

    @Override
    public PageResult<ReconcileRecordDO> getReconcileRecordPage(ReconcileRecordPageReqVO pageReqVO) {
        return reconcileRecordMapper.selectPage(pageReqVO);
    }

    @Override
    public ReconcileRecordChartRespVO getReconcileRecordChart(ReconcileRecordChartReqVO chartReqVO) {
        ReconcileRecordChartRespVO resp = new ReconcileRecordChartRespVO();
        LocalDateTime start = chartReqVO.getStartTime() != null ? chartReqVO.getStartTime() : LocalDateTime.now().minusDays(30);
        LocalDateTime end = chartReqVO.getEndTime() != null ? chartReqVO.getEndTime() : LocalDateTime.now();

        resp.setTrendData(reconcileRecordMapper.selectTrend(start, end));
        Long unmatchedCount = reconcileRecordMapper.selectUnmatchedCount();
        Long totalCount = reconcileRecordMapper.selectTotalCount();
        resp.setUnmatchedCount(unmatchedCount);
        resp.setTotalCount(totalCount);

        if (totalCount != null && totalCount > 0) {
            long matchedCount = totalCount - (unmatchedCount != null ? unmatchedCount : 0L);
            resp.setMatchRate(new BigDecimal(matchedCount).multiply(BigDecimal.valueOf(100))
                    .divide(new BigDecimal(totalCount), 1, RoundingMode.HALF_UP));
        } else {
            resp.setMatchRate(BigDecimal.ZERO);
        }
        return resp;
    }

    private void validateExists(Long id) {
        if (reconcileRecordMapper.selectById(id) == null) throw exception(RECONCILE_RECORD_NOT_EXISTS);
    }
}
