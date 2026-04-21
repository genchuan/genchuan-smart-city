package cn.iocoder.yudao.module.ordertrade.service.debtcollect;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.debtcollect.DebtIdentifyDO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.debtcollect.DebtRecordDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.debtcollect.DebtIdentifyMapper;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.debtcollect.DebtRecordMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.ordertrade.enums.ErrorCodeConstants.DEBT_IDENTIFY_NOT_EXISTS;

/**
 * 逃费识别 Service 实现类
 * @author genchuan
 */
@Service
@Validated
public class DebtIdentifyServiceImpl implements DebtIdentifyService {

    @Resource private DebtIdentifyMapper debtIdentifyMapper;
    @Resource private DebtRecordMapper debtRecordMapper;

    @Override public Long createDebtIdentify(DebtIdentifySaveReqVO v) {
        DebtIdentifyDO o = BeanUtils.toBean(v, DebtIdentifyDO.class);
        debtIdentifyMapper.insert(o); return o.getId();
    }
    @Override public void updateDebtIdentify(DebtIdentifySaveReqVO v) {
        validateExists(v.getId()); debtIdentifyMapper.updateById(BeanUtils.toBean(v, DebtIdentifyDO.class));
    }
    @Override public void deleteDebtIdentify(Long id) { validateExists(id); debtIdentifyMapper.deleteById(id); }
    @Override public void deleteDebtIdentifyListByIds(List<Long> ids) { debtIdentifyMapper.deleteByIds(ids); }
    @Override public DebtIdentifyDO getDebtIdentify(Long id) { return debtIdentifyMapper.selectById(id); }
    @Override public PageResult<DebtIdentifyDO> getDebtIdentifyPage(DebtIdentifyPageReqVO v) { return debtIdentifyMapper.selectPage(v); }
    @Override public DebtIdentifyChartRespVO getDebtIdentifyChart(DebtIdentifyChartReqVO v)  {
        DebtIdentifyChartRespVO resp = new DebtIdentifyChartRespVO();
        LocalDateTime start = v.getStartTime() != null ? v.getStartTime() : LocalDateTime.now().minusDays(30);
        LocalDateTime end   = v.getEndTime()   != null ? v.getEndTime()   : LocalDateTime.now();
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime now = LocalDateTime.now();
        resp.setTrendData(debtIdentifyMapper.selectTrend(start, end));
        resp.setStationData(debtIdentifyMapper.selectGroupByStatus());
        resp.setWaitIdentifyCount(debtIdentifyMapper.selectCountByStatus("pending").intValue());
        Long total = debtIdentifyMapper.selectTodayCount(todayStart, now);
        Long identified = debtIdentifyMapper.selectCountByStatus("identified");
        if (total != null && total > 0) {
            resp.setIdentifySuccessRate(new BigDecimal(identified).multiply(BigDecimal.valueOf(100))
                    .divide(new BigDecimal(total), 1, java.math.RoundingMode.HALF_UP));
        } else { resp.setIdentifySuccessRate(BigDecimal.ZERO); }
        return resp;
    }

    /** 识别：待识别 → 已识别，生成逃费记录 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void identifyDebtIdentify(IdReqVO reqVO) {
        DebtIdentifyDO identify = debtIdentifyMapper.selectById(reqVO.getId());
        if (identify == null) throw exception(DEBT_IDENTIFY_NOT_EXISTS);
        // 1. 更新识别状态
        DebtIdentifyDO update = new DebtIdentifyDO();
        update.setId(reqVO.getId());
        update.setStatus("identified");
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        debtIdentifyMapper.updateById(update);
        // 2. 生成逃费记录
        createDebtRecord(identify, 1, reqVO.getRemark());
    }

    /** 标记：非逃费，忽略该识别 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void markDebtIdentify(IdReqVO reqVO) {
        DebtIdentifyDO identify = debtIdentifyMapper.selectById(reqVO.getId());
        if (identify == null) throw exception(DEBT_IDENTIFY_NOT_EXISTS);
        DebtIdentifyDO update = new DebtIdentifyDO();
        update.setId(reqVO.getId());
        update.setStatus("marked");
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        debtIdentifyMapper.updateById(update);
    }

    /** 批量识别（POST /batch-identify） */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchIdentifyDebtIdentify(IdsReqVO reqVO) {
        if (reqVO.getIds() == null || reqVO.getIds().isEmpty()) return;
        Long operatorId = SecurityFrameworkUtils.getLoginUserId();
        for (Long id : reqVO.getIds()) {
            DebtIdentifyDO identify = debtIdentifyMapper.selectById(id);
            if (identify == null || !"pending".equals(identify.getStatus())) continue;
            DebtIdentifyDO update = new DebtIdentifyDO();
            update.setId(id);
            update.setStatus("identified");
            update.setOperatorId(operatorId);
            debtIdentifyMapper.updateById(update);
            createDebtRecord(identify, 1, reqVO.getRemark());
        }
    }

    private void createDebtRecord(DebtIdentifyDO identify, int orderCount, String remark) {
        DebtRecordDO record = new DebtRecordDO();
        record.setRecordNo("DR-" + System.currentTimeMillis());
        record.setPlateNo(identify.getPlateNo());
        record.setArrearOrderCount(orderCount);
        record.setArrearAmount(identify.getArrearAmount());
        record.setStatus("uncollected");
        record.setStationId(identify.getStationId());
        if (remark != null) record.setCollectProgress(remark);
        debtRecordMapper.insert(record);
    }

    private void validateExists(Long id) {
        if (debtIdentifyMapper.selectById(id) == null) throw exception(DEBT_IDENTIFY_NOT_EXISTS);
    }
}
