package cn.iocoder.yudao.module.park.service.park.trade.deduction;

import cn.iocoder.yudao.module.park.controller.admin.park.trade.deduction.vo.CalculateChargeAmountReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.deduction.vo.CalculateChargeAmountRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.deduction.vo.CalculateChargeDurationReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.deduction.vo.CalculateChargeDurationRespVO;

public interface DeductionService {
    CalculateChargeDurationRespVO calculateChargeDuration(CalculateChargeDurationReqVO reqVO);

    CalculateChargeAmountRespVO calculateChargeAmount(CalculateChargeAmountReqVO reqVO);
}
