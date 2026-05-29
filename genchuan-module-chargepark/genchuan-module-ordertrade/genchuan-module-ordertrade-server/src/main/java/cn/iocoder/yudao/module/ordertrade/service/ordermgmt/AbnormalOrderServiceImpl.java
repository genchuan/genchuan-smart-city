package cn.iocoder.yudao.module.ordertrade.service.ordermgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt.AbnormalOrderDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.ordermgmt.AbnormalOrderMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.ordertrade.enums.ErrorCodeConstants.ABNORMAL_ORDER_NOT_EXISTS;

/**
 * 异常订单 Service 实现类
 * @author genchuan
 */
@Service
@Validated
public class AbnormalOrderServiceImpl implements AbnormalOrderService {

    @Resource private AbnormalOrderMapper abnormalOrderMapper;

    @Override public Long createAbnormalOrder(AbnormalOrderSaveReqVO v) {
        AbnormalOrderDO o = BeanUtils.toBean(v, AbnormalOrderDO.class);
        if (o.getStatus() == null) {
            o.setStatus("unhandled");
        }
        abnormalOrderMapper.insert(o); return o.getId();
    }
    @Override public void updateAbnormalOrder(AbnormalOrderSaveReqVO v) {
        validateExists(v.getId()); abnormalOrderMapper.updateById(BeanUtils.toBean(v, AbnormalOrderDO.class));
    }
    @Override public void deleteAbnormalOrder(Long id) { validateExists(id); abnormalOrderMapper.deleteById(id); }
    @Override public void deleteAbnormalOrderListByIds(List<Long> ids) { abnormalOrderMapper.deleteByIds(ids); }
    @Override public AbnormalOrderDO getAbnormalOrder(Long id) { return abnormalOrderMapper.selectByIdJoinStation(id); }
    @Override public PageResult<AbnormalOrderDO> getAbnormalOrderPage(AbnormalOrderPageReqVO v) {
        Page<AbnormalOrderDO> page = new Page<>(v.getPageNo(), v.getPageSize());
        var result = abnormalOrderMapper.selectPageJoinStation(page, v);
        return new PageResult<>(result.getRecords(), result.getTotal());
    }
        @Override
    public AbnormalOrderChartRespVO getAbnormalOrderChart(AbnormalOrderChartReqVO v) {
        AbnormalOrderChartRespVO resp = new AbnormalOrderChartRespVO();
        // 默认查全量，注释掉30天限制
        // LocalDateTime start = v.getStartTime() != null ? v.getStartTime() : LocalDateTime.now().minusDays(30);
        LocalDateTime start = v.getStartTime();
        LocalDateTime end   = v.getEndTime()   != null ? v.getEndTime()   : LocalDateTime.now();
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime now = LocalDateTime.now();
        resp.setTrendData(abnormalOrderMapper.selectTrend(start, end));
        resp.setTypeData(abnormalOrderMapper.selectGroupByType(start, end));
        AbnormalOrderChartRespVO.CardData card = new AbnormalOrderChartRespVO.CardData();
        card.setWaitProcessCount(abnormalOrderMapper.selectCountByStatus("unhandled").intValue());
        Long all    = abnormalOrderMapper.selectCountByStatus(null);
        Long closed = abnormalOrderMapper.selectCountByStatus("closed");
        if (all != null && all > 0) {
            card.setProcessCompleteRate(new BigDecimal(closed).multiply(BigDecimal.valueOf(100))
                    .divide(new BigDecimal(all), 1, java.math.RoundingMode.HALF_UP));
        } else { card.setProcessCompleteRate(BigDecimal.ZERO); }
        resp.setCardData(card);
        return resp;
    }


    // ==================== 业务操作 ====================

    /** 核查：未处理 → 处理中 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkAbnormalOrder(IdReqVO reqVO) {
        AbnormalOrderDO order = abnormalOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(ABNORMAL_ORDER_NOT_EXISTS);
        AbnormalOrderDO update = new AbnormalOrderDO();
        update.setId(reqVO.getId());
        update.setStatus("handling");
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        if (reqVO.getRemark() != null) update.setProcessProgress(reqVO.getRemark());
        abnormalOrderMapper.updateById(update);
    }

    /** 忽略：标记为已关闭，记录忽略理由 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void ignoreAbnormalOrder(IdReqVO reqVO) {
        AbnormalOrderDO order = abnormalOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(ABNORMAL_ORDER_NOT_EXISTS);
        AbnormalOrderDO update = new AbnormalOrderDO();
        update.setId(reqVO.getId());
        update.setStatus("closed");
        update.setIgnoreReason(reqVO.getRemark() != null ? reqVO.getRemark() : "已忽略，无需处理");
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        abnormalOrderMapper.updateById(update);
    }

    /** 更新处置进度 → 关闭 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProgressAbnormalOrder(IdReqVO reqVO) {
        AbnormalOrderDO order = abnormalOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(ABNORMAL_ORDER_NOT_EXISTS);
        AbnormalOrderDO update = new AbnormalOrderDO();
        update.setId(reqVO.getId());
        update.setStatus("closed");
        update.setProcessProgress(reqVO.getRemark());
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        abnormalOrderMapper.updateById(update);
    }

    /** 批量处置（POST /batch-process） */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchProcessAbnormalOrder(IdsReqVO reqVO) {
        if (reqVO.getIds() == null || reqVO.getIds().isEmpty()) return;
        Long operatorId = SecurityFrameworkUtils.getLoginUserId();
        for (Long id : reqVO.getIds()) {
            AbnormalOrderDO order = abnormalOrderMapper.selectById(id);
            if (order == null) continue;
            AbnormalOrderDO update = new AbnormalOrderDO();
            update.setId(id);
            update.setStatus("closed");
            update.setProcessProgress("批量处置：" + (reqVO.getRemark() != null ? reqVO.getRemark() : "已处理"));
            update.setOperatorId(operatorId);
            abnormalOrderMapper.updateById(update);
        }
    }

    private void validateExists(Long id) {
        if (abnormalOrderMapper.selectById(id) == null) throw exception(ABNORMAL_ORDER_NOT_EXISTS);
    }
}
