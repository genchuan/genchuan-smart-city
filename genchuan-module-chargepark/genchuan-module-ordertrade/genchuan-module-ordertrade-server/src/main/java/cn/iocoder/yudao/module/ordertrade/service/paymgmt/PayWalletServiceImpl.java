package cn.iocoder.yudao.module.ordertrade.service.paymgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt.PayWalletDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.paymgmt.PayWalletMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.ordertrade.enums.ErrorCodeConstants.*;

@Service
@Validated
public class PayWalletServiceImpl implements PayWalletService {

    @Resource
    private PayWalletMapper payWalletMapper;

    @Override
    public PayWalletDO getPayWallet(Long id) {
        return payWalletMapper.selectById(id);
    }

    @Override
    public PageResult<PayWalletDO> getPayWalletPage(PayWalletPageReqVO pageReqVO) {
        return payWalletMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void rechargePayWallet(IdReqVO reqVO) {
        PayWalletDO wallet = payWalletMapper.selectById(reqVO.getId());
        if (wallet == null) throw exception(PAY_WALLET_NOT_EXISTS);
        if (wallet.getFreezePrice() != null && wallet.getFreezePrice() > 0) throw exception(PAY_WALLET_STATUS_CANNOT_RECHARGE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void withdrawPayWallet(IdReqVO reqVO) {
        PayWalletDO wallet = payWalletMapper.selectById(reqVO.getId());
        if (wallet == null) throw exception(PAY_WALLET_NOT_EXISTS);
        if (wallet.getFreezePrice() != null && wallet.getFreezePrice() > 0) throw exception(PAY_WALLET_STATUS_CANNOT_WITHDRAW);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void unfreezePayWallet(IdReqVO reqVO) {
        PayWalletDO wallet = payWalletMapper.selectById(reqVO.getId());
        if (wallet == null) throw exception(PAY_WALLET_NOT_EXISTS);
        PayWalletDO update = new PayWalletDO();
        update.setId(reqVO.getId());
        update.setFreezePrice(0);
        payWalletMapper.updateById(update);
    }

    @Override
    public PayWalletChartRespVO getPayWalletChart(PayWalletChartReqVO chartReqVO) {
        PayWalletChartRespVO resp = new PayWalletChartRespVO();
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime now = LocalDateTime.now();

        resp.setTotalBalance(payWalletMapper.selectTotalBalance());
        resp.setTodayRechargeAmount(payWalletMapper.selectRechargeAmount(todayStart, now));
        return resp;
    }
}
