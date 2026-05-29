package cn.iocoder.yudao.module.ordertrade.service.merchantreconcile;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.merchantreconcile.vo.*;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.merchantreconcile.ReconcileBillDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.merchantreconcile.ReconcileBillMapper;
import cn.iocoder.yudao.module.ordertrade.enums.ReconcileBillStatusEnum;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.ordertrade.enums.ErrorCodeConstants.*;

@Service
@Validated
public class ReconcileBillServiceImpl implements ReconcileBillService {

    @Resource
    private ReconcileBillMapper reconcileBillMapper;

    @Override
    public Long createReconcileBill(ReconcileBillSaveReqVO createReqVO) {
        ReconcileBillDO obj = BeanUtils.toBean(createReqVO, ReconcileBillDO.class);
        obj.setBillNo("RCB" + UUID.randomUUID().toString().replace("-", "").substring(0, 14).toUpperCase());
        if (obj.getStatus() == null) {
            obj.setStatus("pending");
        }
        reconcileBillMapper.insert(obj);
        return obj.getId();
    }

    @Override
    public void updateReconcileBill(ReconcileBillSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getId());
        ReconcileBillDO update = BeanUtils.toBean(updateReqVO, ReconcileBillDO.class);
        reconcileBillMapper.updateById(update);
    }

    @Override
    public void deleteReconcileBill(Long id) {
        validateExists(id);
        reconcileBillMapper.deleteById(id);
    }

    @Override
    public ReconcileBillDO getReconcileBill(Long id) {
        return reconcileBillMapper.selectByIdWithMerchant(id);
    }

    @Override
    public PageResult<ReconcileBillDO> getReconcileBillPage(ReconcileBillPageReqVO pageReqVO) {
        Page<ReconcileBillDO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        var result = reconcileBillMapper.selectPageWithMerchant(page, pageReqVO);
        return new PageResult<>(result.getRecords(), result.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reconcileReconcileBill(IdReqVO reqVO) {
        ReconcileBillDO bill = reconcileBillMapper.selectById(reqVO.getId());
        if (bill == null) throw exception(RECONCILE_BILL_NOT_EXISTS);
        ReconcileBillDO update = new ReconcileBillDO();
        update.setId(reqVO.getId());
        update.setStatus(ReconcileBillStatusEnum.RECONCILED.getValue());
        update.setReconcilerId(SecurityFrameworkUtils.getLoginUserId());
        reconcileBillMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchReconcileReconcileBill(List<Long> ids) {
        ids.forEach(id -> {
            ReconcileBillDO bill = reconcileBillMapper.selectById(id);
            if (bill == null) return;
            ReconcileBillDO update = new ReconcileBillDO();
            update.setId(id);
            update.setStatus(ReconcileBillStatusEnum.RECONCILED.getValue());
            update.setReconcilerId(SecurityFrameworkUtils.getLoginUserId());
            reconcileBillMapper.updateById(update);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirmReconcileBill(Long id) {
        ReconcileBillDO bill = reconcileBillMapper.selectById(id);
        if (bill == null) throw exception(RECONCILE_BILL_NOT_EXISTS);
        if (!ReconcileBillStatusEnum.RECONCILED.getValue().equals(bill.getStatus()))
            throw exception(RECONCILE_BILL_STATUS_CANNOT_CONFIRM);
        ReconcileBillDO update = new ReconcileBillDO();
        update.setId(id);
        // 状态保持为 reconciled，不改变
        update.setConfirmTime(LocalDateTime.now());
        update.setReconcilerId(SecurityFrameworkUtils.getLoginUserId());
        //update.setUpdater(getLoginUserNickname());
        reconcileBillMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void fixReconcileBill(IdReqVO reqVO) {
        ReconcileBillDO bill = reconcileBillMapper.selectById(reqVO.getId());
        if (bill == null) throw exception(RECONCILE_BILL_NOT_EXISTS);
        ReconcileBillDO update = new ReconcileBillDO();
        update.setId(reqVO.getId());
        update.setStatus(ReconcileBillStatusEnum.RECONCILED.getValue());
        update.setReserve1(reqVO.getFixReason());
        update.setReconcilerId(SecurityFrameworkUtils.getLoginUserId());
        reconcileBillMapper.updateById(update);
    }

    @Override
    public ReconcileBillChartRespVO getReconcileBillChart(ReconcileBillChartReqVO chartReqVO) {
        ReconcileBillChartRespVO resp = new ReconcileBillChartRespVO();
        // 默认查全量，注释掉30天限制
        // LocalDateTime start = chartReqVO.getStartTime() != null ? chartReqVO.getStartTime() : LocalDateTime.now().minusDays(30);
        LocalDateTime start = chartReqVO.getStartTime();
        LocalDateTime end = chartReqVO.getEndTime() != null ? chartReqVO.getEndTime() : LocalDateTime.now();

        resp.setTrendData(reconcileBillMapper.selectTrend(start, end));

        ReconcileBillChartRespVO.CardData card = new ReconcileBillChartRespVO.CardData();
        card.setPendingCount(reconcileBillMapper.selectPendingCount());
        card.setDisputedCount(reconcileBillMapper.selectDisputedCount());
        card.setConfirmedCount(reconcileBillMapper.selectConfirmedCount());

        Long confirmedCount = card.getConfirmedCount();
        Long totalCount = reconcileBillMapper.selectTotalCount();
        if (totalCount != null && totalCount > 0) {
            card.setConfirmRate(new BigDecimal(confirmedCount).multiply(BigDecimal.valueOf(100))
                    .divide(new BigDecimal(totalCount), 1, RoundingMode.HALF_UP));
        } else {
            card.setConfirmRate(BigDecimal.ZERO);
        }
        resp.setCardData(card);
        return resp;
    }

    private void validateExists(Long id) {
        if (reconcileBillMapper.selectById(id) == null) throw exception(RECONCILE_BILL_NOT_EXISTS);
    }
}
