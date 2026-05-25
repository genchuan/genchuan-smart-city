package cn.iocoder.yudao.module.ordertrade.service.refundmgmt;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.refundmgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.refundmgmt.RefundRecordDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.refundmgmt.RefundRecordMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.ordertrade.enums.ErrorCodeConstants.REFUND_RECORD_NOT_EXISTS;

/**
 * 退款记录 Service 实现类
 * @author genchuan
 */
@Service
@Validated
public class RefundRecordServiceImpl implements RefundRecordService {

    @Resource private RefundRecordMapper refundRecordMapper;

    @Override public Long createRefundRecord(RefundRecordSaveReqVO v) {
        RefundRecordDO o = BeanUtils.toBean(v, RefundRecordDO.class);
        refundRecordMapper.insert(o); return o.getId();
    }
    @Override public void updateRefundRecord(RefundRecordSaveReqVO v) {
        validateExists(v.getId()); refundRecordMapper.updateById(BeanUtils.toBean(v, RefundRecordDO.class));
    }
    @Override public void deleteRefundRecord(Long id) { validateExists(id); refundRecordMapper.deleteById(id); }
    @Override public void deleteRefundRecordListByIds(List<Long> ids) { refundRecordMapper.deleteByIds(ids); }
    @Override public RefundRecordDO getRefundRecord(Long id) { return refundRecordMapper.selectByIdJoinApplyAndOrder(id); }
    @Override public PageResult<RefundRecordDO> getRefundRecordPage(RefundRecordPageReqVO v) {
        IPage<RefundRecordDO> pageResult = refundRecordMapper.selectPageJoinApplyAndOrder(
                new Page<>(v.getPageNo(), v.getPageSize()), v);
        return new PageResult<>(pageResult.getRecords(), pageResult.getTotal());
    }
    @Override public RefundRecordChartRespVO getRefundRecordChart(RefundRecordChartReqVO v)  {
        RefundRecordChartRespVO resp = new RefundRecordChartRespVO();
        LocalDateTime start = v.getRefundTimeStart() != null ? v.getRefundTimeStart() : LocalDateTime.now().minusDays(30);
        LocalDateTime end   = v.getRefundTimeEnd()   != null ? v.getRefundTimeEnd()   : LocalDateTime.now();
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime now = LocalDateTime.now();
        resp.setTrendData(refundRecordMapper.selectTrend(start, end));
        RefundRecordChartRespVO.CardData card = new RefundRecordChartRespVO.CardData();
        card.setTotalRefundAmount(refundRecordMapper.selectTotalRefundAmount());
        Long all     = refundRecordMapper.selectCountByStatus(null);
        Long success = refundRecordMapper.selectCountByStatus("normal");
        if (all != null && all > 0) {
            card.setRefundSuccessRate(new BigDecimal(success).multiply(BigDecimal.valueOf(100))
                    .divide(new BigDecimal(all), 1, java.math.RoundingMode.HALF_UP));
        } else { card.setRefundSuccessRate(BigDecimal.ZERO); }
        resp.setCardData(card);
        return resp;
    }

    /** 核查（PUT /check）：确认退款记录，填写核查理由 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkRefundRecord(IdReqVO reqVO) {
        RefundRecordDO record = refundRecordMapper.selectById(reqVO.getId());
        if (record == null) throw exception(REFUND_RECORD_NOT_EXISTS);
        RefundRecordDO update = new RefundRecordDO();
        update.setId(reqVO.getId());
        update.setCheckReason(reqVO.getRemark() != null ? reqVO.getRemark() : "核查通过");
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        refundRecordMapper.updateById(update);
    }

    private void validateExists(Long id) {
        if (refundRecordMapper.selectById(id) == null) throw exception(REFUND_RECORD_NOT_EXISTS);
    }
}
