package cn.iocoder.yudao.module.usermerchant.dal.mysql.merchantmgmt.merchantrecharge;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.merchantmgmt.merchantrecharge.MerchantRechargeDO;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantrecharge.vo.*;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 商户充值 Mapper
 *
 * @author 亘川智城
 */
@Mapper
@DS("master")
public interface MerchantRechargeMapper extends BaseMapperX<MerchantRechargeDO> {

    default PageResult<MerchantRechargeDO> selectPage(MerchantRechargePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MerchantRechargeDO>()
                .eqIfPresent(MerchantRechargeDO::getMerchantId, reqVO.getMerchantId())
                .eqIfPresent(MerchantRechargeDO::getAmount, reqVO.getAmount())
                .eqIfPresent(MerchantRechargeDO::getPayChannel, reqVO.getPayChannel())
                .eqIfPresent(MerchantRechargeDO::getStatus, reqVO.getStatus())
                .eqIfPresent(MerchantRechargeDO::getOrderNo, reqVO.getOrderNo())
                .betweenIfPresent(MerchantRechargeDO::getPayTime, reqVO.getPayTime())
                .betweenIfPresent(MerchantRechargeDO::getConfirmTime, reqVO.getConfirmTime())
                .eqIfPresent(MerchantRechargeDO::getRemark, reqVO.getRemark())
                .orderByDesc(MerchantRechargeDO::getId));
    }

    List<MerchantRechargeChartRespVO.RechargeAmountTrendVO> selectRechargeAmountTrend(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("granularity") String granularity
    );

    BigDecimal selectTotalRechargeAmount(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    BigDecimal selectRechargeSuccessRate(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

}