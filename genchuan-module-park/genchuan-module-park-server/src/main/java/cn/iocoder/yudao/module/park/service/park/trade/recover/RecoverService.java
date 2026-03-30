package cn.iocoder.yudao.module.park.service.park.trade.recover;

import cn.iocoder.yudao.module.park.controller.admin.park.trade.recover.vo.*;

public interface RecoverService {
    String generateArrearsQrCode(GenerateArrearsQrCodeReqVO reqVO);

    PreDiscountAutoCalculateRespVO preDiscountAutoCalculate(PreDiscountAutoCalculateReqVO reqVO);

    RecoverWalletPayRespVO walletPay(RecoverWalletPayReqVO reqVO);

    WalletPayOrderTempRespVO walletPayOrderTemp(WalletPayOrderTempReqVO reqVO);
}
