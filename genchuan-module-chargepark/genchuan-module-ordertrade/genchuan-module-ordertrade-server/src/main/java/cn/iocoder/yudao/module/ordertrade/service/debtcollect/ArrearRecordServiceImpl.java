package cn.iocoder.yudao.module.ordertrade.service.debtcollect;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.debtcollect.ArrearRecordDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.debtcollect.ArrearRecordMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.ordertrade.enums.ErrorCodeConstants.ARREAR_RECORD_NOT_EXISTS;

/**
 * 欠费记录 Service 实现类
 * @author genchuan
 */
@Service
@Validated
public class ArrearRecordServiceImpl implements ArrearRecordService {

    @Resource private ArrearRecordMapper arrearRecordMapper;

    @Override public Long createArrearRecord(ArrearRecordSaveReqVO v) {
        ArrearRecordDO o = BeanUtils.toBean(v, ArrearRecordDO.class);
        arrearRecordMapper.insert(o); return o.getId();
    }
    @Override public void updateArrearRecord(ArrearRecordSaveReqVO v) {
        validateExists(v.getId()); arrearRecordMapper.updateById(BeanUtils.toBean(v, ArrearRecordDO.class));
    }
    @Override public void deleteArrearRecord(Long id) { validateExists(id); arrearRecordMapper.deleteById(id); }
    @Override public void deleteArrearRecordListByIds(List<Long> ids) { arrearRecordMapper.deleteByIds(ids); }
    @Override public ArrearRecordDO getArrearRecord(Long id) { return arrearRecordMapper.selectById(id); }
    @Override public PageResult<ArrearRecordDO> getArrearRecordPage(ArrearRecordPageReqVO v) {
        Page<ArrearRecordDO> page = new Page<>(v.getPageNo(), v.getPageSize());
        var result = arrearRecordMapper.selectPageJoinStation(page, v);
        return new PageResult<>(result.getRecords(), result.getTotal());
    }
    @Override public ArrearRecordChartRespVO getArrearRecordChart(ArrearRecordChartReqVO v)  {
        ArrearRecordChartRespVO resp = new ArrearRecordChartRespVO();
        LocalDateTime start = v.getStartTime() != null ? v.getStartTime() : LocalDateTime.now().minusDays(30);
        LocalDateTime end   = v.getEndTime()   != null ? v.getEndTime()   : LocalDateTime.now();
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime now = LocalDateTime.now();
        resp.setTrendData(arrearRecordMapper.selectTrend(start, end));
        ArrearRecordChartRespVO.CardData card = new ArrearRecordChartRespVO.CardData();
        card.setTotalArrearAmount(arrearRecordMapper.selectTotalArrearAmount());
        Long all     = arrearRecordMapper.selectCountByStatus(null);
        Long cleared = arrearRecordMapper.selectCountByStatus("cleared");
        if (all != null && all > 0) {
            card.setClearRate(new BigDecimal(cleared).multiply(BigDecimal.valueOf(100))
                    .divide(new BigDecimal(all), 1, java.math.RoundingMode.HALF_UP));
        } else { card.setClearRate(BigDecimal.ZERO); }
        resp.setCardData(card);
        return resp;
    }

    /** 催缴（PUT /remind）：发送催缴通知 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remindArrearRecord(IdReqVO reqVO) {
        ArrearRecordDO record = arrearRecordMapper.selectById(reqVO.getId());
        if (record == null) throw exception(ARREAR_RECORD_NOT_EXISTS);
        // 催缴操作：实际调用消息服务，此处记录操作人
        ArrearRecordDO update = new ArrearRecordDO();
        update.setId(reqVO.getId());
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        arrearRecordMapper.updateById(update);
    }

    private void validateExists(Long id) {
        if (arrearRecordMapper.selectById(id) == null) throw exception(ARREAR_RECORD_NOT_EXISTS);
    }
}
