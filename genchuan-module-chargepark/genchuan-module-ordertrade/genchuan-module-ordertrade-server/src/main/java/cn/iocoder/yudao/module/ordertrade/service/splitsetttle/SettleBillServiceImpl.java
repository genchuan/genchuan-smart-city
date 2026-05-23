package cn.iocoder.yudao.module.ordertrade.service.splitsetttle;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.splitsetttle.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.splitsetttle.SettleBillDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.splitsetttle.SettleBillMapper;
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
public class SettleBillServiceImpl implements SettleBillService {

    @Resource
    private SettleBillMapper settleBillMapper;

    @Override
    public Long createSettleBill(SettleBillSaveReqVO createReqVO) {
        SettleBillDO obj = BeanUtils.toBean(createReqVO, SettleBillDO.class);
        obj.setBillNo("SB" + UUID.randomUUID().toString().replace("-", "").substring(0, 16).toUpperCase());
        if (obj.getStatus() == null) {
            obj.setStatus("pending_audit");
        }
        settleBillMapper.insert(obj);
        return obj.getId();
    }

    @Override
    public void updateSettleBill(SettleBillSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getId());
        settleBillMapper.updateById(BeanUtils.toBean(updateReqVO, SettleBillDO.class));
    }

    @Override
    public void deleteSettleBill(Long id) {
        validateExists(id);
        settleBillMapper.deleteById(id);
    }

    @Override
    public SettleBillDO getSettleBill(Long id) {
        return settleBillMapper.selectByIdWithPartner(id);
    }

    @Override
    public PageResult<SettleBillDO> getSettleBillPage(SettleBillPageReqVO pageReqVO) {
        return settleBillMapper.selectPageWithPartner(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void approveSettleBill(IdReqVO reqVO) {
        SettleBillDO bill = settleBillMapper.selectById(reqVO.getId());
        if (bill == null) throw exception(SETTLE_BILL_NOT_EXISTS);
        if (!"pending_audit".equals(bill.getStatus())) throw exception(SETTLE_BILL_STATUS_CANNOT_APPROVE);
        SettleBillDO update = new SettleBillDO();
        update.setId(reqVO.getId());
        update.setStatus("pending_settle");
        update.setAuditorId(SecurityFrameworkUtils.getLoginUserId());
        update.setAuditTime(LocalDateTime.now());
        settleBillMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void rejectSettleBill(IdReqVO reqVO) {
        SettleBillDO bill = settleBillMapper.selectById(reqVO.getId());
        if (bill == null) throw exception(SETTLE_BILL_NOT_EXISTS);
        if (!"pending_audit".equals(bill.getStatus())) throw exception(SETTLE_BILL_STATUS_CANNOT_REJECT);
        SettleBillDO update = new SettleBillDO();
        update.setId(reqVO.getId());
        update.setStatus("rejected");
        update.setAuditorId(SecurityFrameworkUtils.getLoginUserId());
        update.setAuditTime(LocalDateTime.now());
        settleBillMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void settleSettleBill(IdReqVO reqVO) {
        SettleBillDO bill = settleBillMapper.selectById(reqVO.getId());
        if (bill == null) throw exception(SETTLE_BILL_NOT_EXISTS);
        if (!"pending_settle".equals(bill.getStatus())) throw exception(SETTLE_BILL_STATUS_CANNOT_SETTLE);
        SettleBillDO update = new SettleBillDO();
        update.setId(reqVO.getId());
        update.setStatus("settled");
        update.setSettleTime(LocalDateTime.now());
        settleBillMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void regenerateSettleBill(IdReqVO reqVO) {
        SettleBillDO bill = settleBillMapper.selectById(reqVO.getId());
        if (bill == null) throw exception(SETTLE_BILL_NOT_EXISTS);
        SettleBillDO update = new SettleBillDO();
        update.setId(reqVO.getId());
        update.setStatus("pending_audit");
        update.setBillNo("SB" + UUID.randomUUID().toString().replace("-", "").substring(0, 16).toUpperCase());
        settleBillMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchAuditSettleBill(List<Long> ids) {
        ids.forEach(id -> {
            SettleBillDO bill = settleBillMapper.selectById(id);
            if (bill != null && "pending_audit".equals(bill.getStatus())) {
                SettleBillDO update = new SettleBillDO();
                update.setId(id);
                update.setStatus("pending_settle");
                update.setAuditorId(SecurityFrameworkUtils.getLoginUserId());
                update.setAuditTime(LocalDateTime.now());
                settleBillMapper.updateById(update);
            }
        });
    }

    @Override
    public SettleBillChartRespVO getSettleBillChart(SettleBillChartReqVO chartReqVO) {
        SettleBillChartRespVO resp = new SettleBillChartRespVO();
        LocalDateTime start = chartReqVO.getStartTime() != null ? chartReqVO.getStartTime() : LocalDateTime.now().minusDays(30);
        LocalDateTime end = chartReqVO.getEndTime() != null ? chartReqVO.getEndTime() : LocalDateTime.now();

        resp.setTrendData(settleBillMapper.selectTrend(start, end));

        SettleBillChartRespVO.CardData card = new SettleBillChartRespVO.CardData();
        card.setTotalSettleAmount(settleBillMapper.selectTotalSettleAmount());

        Long settledCount = settleBillMapper.selectSettledCount();
        Long totalCount = settleBillMapper.selectTotalCount();
        if (totalCount != null && totalCount > 0) {
            card.setSettleCompleteRate(new BigDecimal(settledCount).multiply(BigDecimal.valueOf(100))
                    .divide(new BigDecimal(totalCount), 1, RoundingMode.HALF_UP));
        } else {
            card.setSettleCompleteRate(BigDecimal.ZERO);
        }
        resp.setCardData(card);
        return resp;
    }

    private void validateExists(Long id) {
        if (settleBillMapper.selectById(id) == null) throw exception(SETTLE_BILL_NOT_EXISTS);
    }
}
