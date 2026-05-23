package cn.iocoder.yudao.module.ordertrade.service.splitsetttle;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.splitsetttle.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.splitsetttle.SettleStatusDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.splitsetttle.SettleStatusMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.ordertrade.enums.ErrorCodeConstants.*;

@Service
@Validated
public class SettleStatusServiceImpl implements SettleStatusService {

    @Resource
    private SettleStatusMapper settleStatusMapper;

    @Override
    public Long createSettleStatus(SettleStatusSaveReqVO createReqVO) {
        SettleStatusDO obj = BeanUtils.toBean(createReqVO, SettleStatusDO.class);
        if (obj.getStatus() == null) {
            obj.setStatus("normal");
        }
        settleStatusMapper.insert(obj);
        return obj.getId();
    }

    @Override
    public void updateSettleStatus(SettleStatusSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getId());
        settleStatusMapper.updateById(BeanUtils.toBean(updateReqVO, SettleStatusDO.class));
    }

    @Override
    public void deleteSettleStatus(Long id) {
        validateExists(id);
        settleStatusMapper.deleteById(id);
    }

    @Override
    public SettleStatusDO getSettleStatus(Long id) {
        return settleStatusMapper.selectByIdWithBill(id);
    }

    @Override
    public PageResult<SettleStatusDO> getSettleStatusPage(SettleStatusPageReqVO pageReqVO) {
        return settleStatusMapper.selectPageWithBill(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkSettleStatus(IdReqVO reqVO) {
        SettleStatusDO status = settleStatusMapper.selectById(reqVO.getId());
        if (status == null) throw exception(SETTLE_STATUS_NOT_EXISTS);
        SettleStatusDO update = new SettleStatusDO();
        update.setId(reqVO.getId());
        update.setCheckerId(SecurityFrameworkUtils.getLoginUserId());
        update.setCheckTime(LocalDateTime.now());
        settleStatusMapper.updateById(update);
    }

    @Override
    public SettleStatusChartRespVO getSettleStatusChart(SettleStatusChartReqVO chartReqVO) {
        SettleStatusChartRespVO resp = new SettleStatusChartRespVO();
        resp.setStatusData(settleStatusMapper.selectGroupByStatus());

        SettleStatusChartRespVO.CardData card = new SettleStatusChartRespVO.CardData();
        Long normalCount = settleStatusMapper.selectNormalCount();
        Long totalCount = settleStatusMapper.selectTotalCount();
        Long abnormalCount = settleStatusMapper.selectAbnormalCount();

        if (totalCount != null && totalCount > 0) {
            card.setCompleteRate(new BigDecimal(normalCount).multiply(BigDecimal.valueOf(100))
                    .divide(new BigDecimal(totalCount), 1, RoundingMode.HALF_UP));
            card.setAbnormalRate(new BigDecimal(abnormalCount).multiply(BigDecimal.valueOf(100))
                    .divide(new BigDecimal(totalCount), 1, RoundingMode.HALF_UP));
        } else {
            card.setCompleteRate(BigDecimal.ZERO);
            card.setAbnormalRate(BigDecimal.ZERO);
        }
        resp.setCardData(card);
        return resp;
    }

    private void validateExists(Long id) {
        if (settleStatusMapper.selectById(id) == null) throw exception(SETTLE_STATUS_NOT_EXISTS);
    }
}
