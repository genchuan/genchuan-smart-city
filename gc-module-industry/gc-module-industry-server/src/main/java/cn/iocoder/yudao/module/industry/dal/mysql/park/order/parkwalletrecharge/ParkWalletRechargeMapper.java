package cn.iocoder.yudao.module.industry.dal.mysql.park.order.parkwalletrecharge;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;

import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkwalletrecharge.vo.ParkWalletRechargePageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkwalletrecharge.ParkWalletRechargeDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 钱包充值 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkWalletRechargeMapper extends BaseMapperX<ParkWalletRechargeDO> {

    default PageResult<ParkWalletRechargeDO> selectPage(ParkWalletRechargePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkWalletRechargeDO>()
                .eqIfPresent(ParkWalletRechargeDO::getRechargeNo, reqVO.getRechargeNo())
                .eqIfPresent(ParkWalletRechargeDO::getUserId, reqVO.getUserId())
                .eqIfPresent(ParkWalletRechargeDO::getRechargeAmount, reqVO.getRechargeAmount())
                .eqIfPresent(ParkWalletRechargeDO::getActualPayAmount, reqVO.getActualPayAmount())
                .eqIfPresent(ParkWalletRechargeDO::getRechargePackageId, reqVO.getRechargePackageId())
                .eqIfPresent(ParkWalletRechargeDO::getDiscountAmount, reqVO.getDiscountAmount())
                .eqIfPresent(ParkWalletRechargeDO::getPayWay, reqVO.getPayWay())
                .eqIfPresent(ParkWalletRechargeDO::getRechargeStatus, reqVO.getRechargeStatus())
                .betweenIfPresent(ParkWalletRechargeDO::getPayTime, reqVO.getPayTime())
                .betweenIfPresent(ParkWalletRechargeDO::getRechargeTime, reqVO.getRechargeTime())
                .eqIfPresent(ParkWalletRechargeDO::getCouponId, reqVO.getCouponId())
                .eqIfPresent(ParkWalletRechargeDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ParkWalletRechargeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkWalletRechargeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkWalletRechargeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkWalletRechargeDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(ParkWalletRechargeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ParkWalletRechargeDO::getId));
    }

}
