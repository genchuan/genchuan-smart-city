package cn.iocoder.yudao.module.ordertrade.service.merchantreconcile;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.merchantreconcile.vo.*;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
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
        if (obj.getStatus() == null) {
            obj.setStatus("normal");
        }
        reconcileRecordMapper.insert(obj);
        return obj.getId();
    }

    @Override
    public void updateReconcileRecord(ReconcileRecordSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getId());
        ReconcileRecordDO update = BeanUtils.toBean(updateReqVO, ReconcileRecordDO.class);
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
        // 默认查全量，注释掉30天限制
        // LocalDateTime start = chartReqVO.getStartTime() != null ? chartReqVO.getStartTime() : LocalDateTime.now().minusDays(30);
        LocalDateTime start = chartReqVO.getStartTime();
        LocalDateTime end = chartReqVO.getEndTime() != null ? chartReqVO.getEndTime() : LocalDateTime.now();

        resp.setTrendData(reconcileRecordMapper.selectTrend(start, end));

        ReconcileRecordChartRespVO.CardData card = new ReconcileRecordChartRespVO.CardData();
        Long unmatchedCount = reconcileRecordMapper.selectAbnormalCount();
        Long totalCount = reconcileRecordMapper.selectTotalCount();
        card.setUnmatchedCount(unmatchedCount);
        card.setTotalCount(totalCount);

        if (totalCount != null && totalCount > 0) {
            long matchedCount = totalCount - (unmatchedCount != null ? unmatchedCount : 0L);
            card.setMatchRate(new BigDecimal(matchedCount).multiply(BigDecimal.valueOf(100))
                    .divide(new BigDecimal(totalCount), 1, RoundingMode.HALF_UP));
        } else {
            card.setMatchRate(BigDecimal.ZERO);
        }
        resp.setCardData(card);
        return resp;
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(rollbackFor = Exception.class)
    public void checkReconcileRecord(IdReqVO reqVO) {
        ReconcileRecordDO record = reconcileRecordMapper.selectById(reqVO.getId());
        if (record == null) throw exception(RECONCILE_RECORD_NOT_EXISTS);
        ReconcileRecordDO update = new ReconcileRecordDO();
        update.setId(reqVO.getId());
        update.setStatus("normal");
        update.setCheckerId(cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId());
        update.setCheckTime(LocalDateTime.now());
        reconcileRecordMapper.updateById(update);
    }

    private void validateExists(Long id) {
        if (reconcileRecordMapper.selectById(id) == null) throw exception(RECONCILE_RECORD_NOT_EXISTS);
    }
}
