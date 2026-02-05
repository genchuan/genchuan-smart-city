package cn.iocoder.yudao.module.park.service.park.trade.deduction;


import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.deduction.vo.CalculateChargeAmountReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.deduction.vo.CalculateChargeAmountRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.deduction.vo.CalculateChargeDurationReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.deduction.vo.CalculateChargeDurationRespVO;
import cn.iocoder.yudao.module.park.dal.mysql.park.trade.deduction.DeductionMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.ORDER_REFUND_NOT_EXISTS;
import static java.lang.Math.ceil;

@Service
@Validated
public class DeductionServiceImpl implements DeductionService{
    @Resource
    private DeductionMapper deductionMapper;

    //计算收费时长
    @Override
    public CalculateChargeDurationRespVO calculateChargeDuration(CalculateChargeDurationReqVO reqVO) {
        //一、获取参数：
        //获取入场出场时间
        LocalDateTime entryTime = reqVO.getEntryTime();
        LocalDateTime exitTime = reqVO.getExitTime();

        if (entryTime == null || exitTime == null) {
            throw exception(new ErrorCode(500, "入场时间或出场时间不能为空"));
        }

        if (exitTime.isBefore(entryTime)) {
            throw exception(new ErrorCode(500, "出场时间不能早于入场时间"));
        }

        //TODO 获取非收费时间：通过车场Id获取车场信息，通过车场信息的策略Id获取策略信息，通过策略信息获取免费停车时长(分钟)
        Integer freeDurationMinutes = 30;

        //获取畅停卡Id（可为空）
        Long smoothParkingCardId = reqVO.getSmoothParkingCardId();

        //二、业务
        //1.TODO 如果畅停卡存在后端数据库并且是该用户的且有效时间的，免费停车，即收费时间为0（分钟）
        //2.计算停车时长
        int parkingMinutes = (int) Duration.between(entryTime, exitTime).toMinutes();

        //3.扣去非收费时间
        int chargeableMinutes = parkingMinutes - freeDurationMinutes;
        if (chargeableMinutes < 0) {
            chargeableMinutes = 0;
        }

        //三、构造返回参数
        Integer respChargeableMinutes = chargeableMinutes;
        CalculateChargeDurationRespVO respVO =new CalculateChargeDurationRespVO();
        respVO.setChargeDuration(respChargeableMinutes);

        return respVO;
    }

    //计算收费金额，包括优惠计算
    @Override
    public CalculateChargeAmountRespVO calculateChargeAmount(CalculateChargeAmountReqVO reqVO) {
        //一、获取参数
        Long feeStrategyId= reqVO.getFeeStrategyId();
        Integer chargeDuration = reqVO.getChargeDuration();

        if (reqVO.getChargeDuration() <= 0) {
            CalculateChargeAmountRespVO respVO = new CalculateChargeAmountRespVO();
            respVO.setOriginalAmount(BigDecimal.ZERO);   // 原始金额为0
            respVO.setChargeAmount(BigDecimal.ZERO);     // 实付金额为0
            respVO.setDiscountAmount(BigDecimal.ZERO);   // 优惠金额为0
            return respVO;
        }
        //二、解析收费策略 TODO 通过收费策略Id解析收费策略
        //1.解析收费策略
        //2.获取收费规则参数
        Integer chargingUnitMinute = 60; //收费单位时间（分钟），比如30为30分钟为一个收费时间单位
        Integer chargingUnitAmount = 10; //每个续费单位的价格（元）
        Integer firstChargingUnitAmount = 20; //首付单位的价格（元）

        Integer maxDailyChargeAmount = 100; //单日最高收费（元）

        //三、计算收费金额-基于收费策略的计算
        int chargingUnit = Math.max(1,
                (int) Math.ceil((double) chargeDuration / chargingUnitMinute));

        BigDecimal totalOriginalAmount= BigDecimal.valueOf(firstChargingUnitAmount*1+chargingUnitAmount*(chargingUnit-1));
        //封顶判断
        if (totalOriginalAmount.compareTo(BigDecimal.valueOf(maxDailyChargeAmount))>0){
            totalOriginalAmount= BigDecimal.valueOf(maxDailyChargeAmount);
        }
        //实际支付金额
        BigDecimal chargeAmount = totalOriginalAmount;
        //四、TODO 计算收费金额-无感支付折扣

        //五、TODO 计算收费金额-优惠券折扣

        //六、构造返回参数
        //优惠金额
        BigDecimal discountAmount= totalOriginalAmount.subtract(chargeAmount);
        //构造返参
        CalculateChargeAmountRespVO respVO=new CalculateChargeAmountRespVO();
        respVO.setOriginalAmount(totalOriginalAmount);  // 原始金额
        respVO.setChargeAmount(chargeAmount);           // 实际支付金额
        respVO.setDiscountAmount(discountAmount); // 优惠金额

        return respVO;
    }
}
