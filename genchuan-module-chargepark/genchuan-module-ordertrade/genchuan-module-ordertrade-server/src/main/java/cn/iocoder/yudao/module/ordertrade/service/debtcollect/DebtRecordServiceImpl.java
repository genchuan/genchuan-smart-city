package cn.iocoder.yudao.module.ordertrade.service.debtcollect;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.debtcollect.CollectTrackDO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.debtcollect.DebtRecordDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.debtcollect.CollectTrackMapper;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.debtcollect.DebtRecordMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.ordertrade.enums.ErrorCodeConstants.DEBT_RECORD_NOT_EXISTS;

/**
 * 逃费记录 Service 实现类
 * @author genchuan
 */
@Service
@Validated
public class DebtRecordServiceImpl implements DebtRecordService {

    @Resource private DebtRecordMapper debtRecordMapper;
    @Resource private CollectTrackMapper collectTrackMapper;

    @Override public Long createDebtRecord(DebtRecordSaveReqVO v) {
        DebtRecordDO o = BeanUtils.toBean(v, DebtRecordDO.class);
        debtRecordMapper.insert(o); return o.getId();
    }
    @Override public void updateDebtRecord(DebtRecordSaveReqVO v) {
        validateExists(v.getId()); debtRecordMapper.updateById(BeanUtils.toBean(v, DebtRecordDO.class));
    }
    @Override public void deleteDebtRecord(Long id) { validateExists(id); debtRecordMapper.deleteById(id); }
    @Override public void deleteDebtRecordListByIds(List<Long> ids) { debtRecordMapper.deleteByIds(ids); }
    @Override public DebtRecordDO getDebtRecord(Long id) { return debtRecordMapper.selectById(id); }
    @Override public PageResult<DebtRecordDO> getDebtRecordPage(DebtRecordPageReqVO v) {
        Page<DebtRecordDO> page = new Page<>(v.getPageNo(), v.getPageSize());
        var result = debtRecordMapper.selectPageJoinStation(page, v);
        return new PageResult<>(result.getRecords(), result.getTotal());
    }
    @Override public DebtRecordChartRespVO getDebtRecordChart(DebtRecordChartReqVO v)  {
        DebtRecordChartRespVO resp = new DebtRecordChartRespVO();
        LocalDateTime start = v.getStartTime() != null ? v.getStartTime() : LocalDateTime.now().minusDays(30);
        LocalDateTime end   = v.getEndTime()   != null ? v.getEndTime()   : LocalDateTime.now();
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime now = LocalDateTime.now();
        resp.setTrendData(debtRecordMapper.selectTrend(start, end));
        DebtRecordChartRespVO.CardData card = new DebtRecordChartRespVO.CardData();
        card.setTotalArrearAmount(debtRecordMapper.selectTotalArrearAmount());
        Long total = debtRecordMapper.selectTodayCount(todayStart, now);
        Long completed = debtRecordMapper.selectCountByStatus("completed");
        Long all = debtRecordMapper.selectCountByStatus(null);
        if (all != null && all > 0) {
            card.setCollectCompleteRate(new BigDecimal(completed).multiply(BigDecimal.valueOf(100))
                    .divide(new BigDecimal(all), 1, java.math.RoundingMode.HALF_UP));
        } else { card.setCollectCompleteRate(BigDecimal.ZERO); }
        resp.setCardData(card);
        return resp;
    }

    /** 发起追缴：未追缴 → 追缴中，创建追缴跟踪记录 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void startCollectDebtRecord(IdReqVO reqVO) {
        DebtRecordDO record = debtRecordMapper.selectById(reqVO.getId());
        if (record == null) throw exception(DEBT_RECORD_NOT_EXISTS);
        // 1. 更新状态为追缴中
        DebtRecordDO update = new DebtRecordDO();
        update.setId(reqVO.getId());
        update.setStatus("collecting");
        update.setCollectProgress("已发起追缴");
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        debtRecordMapper.updateById(update);
        // 2. 创建追缴跟踪记录（短信方式）
        CollectTrackDO track = new CollectTrackDO();
        track.setTrackNo("CT-" + System.currentTimeMillis());
        track.setPlateNo(record.getPlateNo());
        track.setCollectMethod("sms");
        track.setCollectTime(LocalDateTime.now());
        track.setStatus("pending");
        track.setAreaId(1L); // 默认片区，实际由业务配置
        if (reqVO.getRemark() != null) track.setCollectProgress(reqVO.getRemark());
        collectTrackMapper.insert(track);
    }

    /** 更新追缴进度 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProgressDebtRecord(IdReqVO reqVO) {
        DebtRecordDO record = debtRecordMapper.selectById(reqVO.getId());
        if (record == null) throw exception(DEBT_RECORD_NOT_EXISTS);
        DebtRecordDO update = new DebtRecordDO();
        update.setId(reqVO.getId());
        update.setStatus("completed");
        update.setCollectProgress(reqVO.getRemark());
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        debtRecordMapper.updateById(update);
    }

    private void validateExists(Long id) {
        if (debtRecordMapper.selectById(id) == null) throw exception(DEBT_RECORD_NOT_EXISTS);
    }
}
