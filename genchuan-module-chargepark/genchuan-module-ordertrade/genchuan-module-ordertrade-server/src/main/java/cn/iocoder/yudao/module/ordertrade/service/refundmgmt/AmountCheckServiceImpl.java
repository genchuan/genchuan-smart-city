package cn.iocoder.yudao.module.ordertrade.service.refundmgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.refundmgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt.AllOrderDO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.refundmgmt.AmountCheckDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.ordermgmt.AllOrderMapper;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.refundmgmt.AmountCheckMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.ordertrade.enums.ErrorCodeConstants.AMOUNT_CHECK_NOT_EXISTS;

/**
 * 金额核算 Service 实现类
 * @author genchuan
 */
@Service
@Validated
public class AmountCheckServiceImpl implements AmountCheckService {

    @Resource private AmountCheckMapper amountCheckMapper;
    @Resource private AllOrderMapper allOrderMapper;

    @Override public Long createAmountCheck(AmountCheckSaveReqVO v) {
        AmountCheckDO o = BeanUtils.toBean(v, AmountCheckDO.class);
        amountCheckMapper.insert(o); return o.getId();
    }
    @Override public void updateAmountCheck(AmountCheckSaveReqVO v) {
        validateExists(v.getId()); amountCheckMapper.updateById(BeanUtils.toBean(v, AmountCheckDO.class));
    }
    @Override public void deleteAmountCheck(Long id) { validateExists(id); amountCheckMapper.deleteById(id); }
    @Override public void deleteAmountCheckListByIds(List<Long> ids) { amountCheckMapper.deleteByIds(ids); }
    @Override public AmountCheckDO getAmountCheck(Long id) { return amountCheckMapper.selectById(id); }
    @Override public PageResult<AmountCheckDO> getAmountCheckPage(AmountCheckPageReqVO v) { return amountCheckMapper.selectPage(v); }
    @Override public AmountCheckChartRespVO getAmountCheckChart(AmountCheckChartReqVO v)  {
        AmountCheckChartRespVO resp = new AmountCheckChartRespVO();
        LocalDateTime start = v.getStartTime() != null ? v.getStartTime() : LocalDateTime.now().minusDays(30);
        LocalDateTime end   = v.getEndTime()   != null ? v.getEndTime()   : LocalDateTime.now();
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime now = LocalDateTime.now();
        resp.setTrendData(amountCheckMapper.selectTrend(start, end));
        AmountCheckChartRespVO.CardData card = new AmountCheckChartRespVO.CardData();
        card.setTotalCheckCount(amountCheckMapper.selectCountByStatus(null).intValue());
        Long all     = amountCheckMapper.selectCountByStatus(null);
        Long passed  = amountCheckMapper.selectCountByStatus("checked");
        if (all != null && all > 0) {
            card.setCheckAccuracy(new BigDecimal(passed).multiply(BigDecimal.valueOf(100))
                    .divide(new BigDecimal(all), 1, java.math.RoundingMode.HALF_UP));
        } else { card.setCheckAccuracy(BigDecimal.ZERO); }
        resp.setCardData(card);
        return resp;
    }

    /** 核算（POST /do-check）：批量发起核算，对比订单实际金额与申请金额 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doCheckAmountCheck(IdsReqVO reqVO) {
        if (reqVO.getIds() == null || reqVO.getIds().isEmpty()) return;
        Long operatorId = SecurityFrameworkUtils.getLoginUserId();
        for (Long id : reqVO.getIds()) {
            AmountCheckDO check = amountCheckMapper.selectById(id);
            if (check == null) continue;
            AmountCheckDO update = new AmountCheckDO();
            update.setId(id);
            update.setStatus("checked");
            update.setOperatorId(operatorId);
            // 从主订单获取实际金额，与申请金额比对
            if (check.getOrderId() != null) {
                AllOrderDO order = allOrderMapper.selectById(check.getOrderId());
                if (order != null && order.getAmount() != null) {
                    boolean match = check.getApplyAmount() != null &&
                                   check.getApplyAmount().compareTo(order.getAmount()) == 0;
                    update.setCheckResult(match ? "pass" : "fail");
                    update.setCheckDetail(match
                        ? "核算通过，金额一致：" + order.getAmount()
                        : "核算差异：申请=" + check.getApplyAmount() + "，实际=" + order.getAmount());
                }
            }
            amountCheckMapper.updateById(update);
        }
    }

    /** 核算（POST /check）：单条核算，校验订单费用、优惠抵扣的准确性，生成核算结果 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkAmountCheck(IdReqVO reqVO) {
        AmountCheckDO check = amountCheckMapper.selectById(reqVO.getId());
        if (check == null) throw exception(AMOUNT_CHECK_NOT_EXISTS);
        AmountCheckDO update = new AmountCheckDO();
        update.setId(reqVO.getId());
        update.setStatus("checked");
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        if (check.getOrderId() != null) {
            AllOrderDO order = allOrderMapper.selectById(check.getOrderId());
            if (order != null && order.getAmount() != null) {
                boolean match = check.getApplyAmount() != null &&
                               check.getApplyAmount().compareTo(order.getAmount()) == 0;
                update.setCheckResult(match ? "pass" : "fail");
                update.setCheckDetail(match
                    ? "核算通过，金额一致：" + order.getAmount()
                    : "核算差异：申请=" + check.getApplyAmount() + "，实际=" + order.getAmount());
            }
        }
        if (update.getCheckResult() == null) {
            update.setCheckResult("pass");
            update.setCheckDetail("核算完成");
        }
        if (reqVO.getRemark() != null) {
            update.setCheckDetail(update.getCheckDetail() + "，备注：" + reqVO.getRemark());
        }
        amountCheckMapper.updateById(update);
    }

    /** 确认（PUT /confirm）：确认核算结果 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirmAmountCheck(IdReqVO reqVO) {
        AmountCheckDO check = amountCheckMapper.selectById(reqVO.getId());
        if (check == null) throw exception(AMOUNT_CHECK_NOT_EXISTS);
        AmountCheckDO update = new AmountCheckDO();
        update.setId(reqVO.getId());
        update.setStatus("confirmed");
        if (reqVO.getRemark() != null) update.setCheckDetail(reqVO.getRemark());
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        amountCheckMapper.updateById(update);
    }

    private void validateExists(Long id) {
        if (amountCheckMapper.selectById(id) == null) throw exception(AMOUNT_CHECK_NOT_EXISTS);
    }
}
