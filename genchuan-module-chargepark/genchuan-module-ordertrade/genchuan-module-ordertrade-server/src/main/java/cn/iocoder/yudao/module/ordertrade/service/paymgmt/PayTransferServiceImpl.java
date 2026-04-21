package cn.iocoder.yudao.module.ordertrade.service.paymgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt.PayTransferDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.paymgmt.PayTransferMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.ordertrade.enums.ErrorCodeConstants.*;

@Service
@Validated
public class PayTransferServiceImpl implements PayTransferService {

    @Resource
    private PayTransferMapper payTransferMapper;

    @Override
    public PayTransferDO getPayTransfer(Long id) {
        return payTransferMapper.selectById(id);
    }

    @Override
    public PageResult<PayTransferDO> getPayTransferPage(PayTransferPageReqVO pageReqVO) {
        return payTransferMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void executePayTransfer(IdReqVO reqVO) {
        PayTransferDO transfer = payTransferMapper.selectById(reqVO.getId());
        if (transfer == null) throw exception(PAY_TRANSFER_NOT_EXISTS);
        if (!Integer.valueOf(0).equals(transfer.getStatus())) throw exception(PAY_TRANSFER_STATUS_CANNOT_EXECUTE);
        PayTransferDO update = new PayTransferDO();
        update.setId(reqVO.getId());
        update.setStatus(10);
        payTransferMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelPayTransfer(IdReqVO reqVO) {
        PayTransferDO transfer = payTransferMapper.selectById(reqVO.getId());
        if (transfer == null) throw exception(PAY_TRANSFER_NOT_EXISTS);
        if (!Integer.valueOf(0).equals(transfer.getStatus())) throw exception(PAY_TRANSFER_STATUS_CANNOT_CANCEL);
        PayTransferDO update = new PayTransferDO();
        update.setId(reqVO.getId());
        update.setStatus(30);
        payTransferMapper.updateById(update);
    }

    @Override
    public PayTransferChartRespVO getPayTransferChart(PayTransferChartReqVO chartReqVO) {
        PayTransferChartRespVO resp = new PayTransferChartRespVO();
        LocalDateTime start = chartReqVO.getStartTime() != null ? chartReqVO.getStartTime() : LocalDateTime.now().minusDays(30);
        LocalDateTime end = chartReqVO.getEndTime() != null ? chartReqVO.getEndTime() : LocalDateTime.now();
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime now = LocalDateTime.now();

        resp.setTrendData(payTransferMapper.selectTrend(start, end));
        resp.setTodayTransferCount(payTransferMapper.selectTodayCount(todayStart, now));
        return resp;
    }
}
