package cn.iocoder.yudao.module.park.service.park.trade.recover;

import cn.iocoder.yudao.module.park.controller.admin.park.trade.recover.vo.GenerateArrearsQrCodeReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.recover.vo.PreDiscountAutoCalculateReqVO;

import java.math.BigDecimal;

public interface RecoverService {
    String generateArrearsQrCode(GenerateArrearsQrCodeReqVO reqVO);

    BigDecimal preDiscountAutoCalculate(PreDiscountAutoCalculateReqVO reqVO);
}
