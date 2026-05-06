package cn.iocoder.yudao.module.usermerchant.dal.mysql.merchantmgmt.merchantsendcoupon;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.plateauth.vo.PlateAuthChartRespVO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.merchantmgmt.merchantsendcoupon.MerchantSendCouponDO;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantsendcoupon.vo.*;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 商户发券 Mapper
 *
 * @author 亘川智城
 */
@Mapper
@DS("master")
public interface MerchantSendCouponMapper extends BaseMapperX<MerchantSendCouponDO> {

    default PageResult<MerchantSendCouponDO> selectPage(MerchantSendCouponPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MerchantSendCouponDO>()
                .eqIfPresent(MerchantSendCouponDO::getMerchantId, reqVO.getMerchantId())
                .eqIfPresent(MerchantSendCouponDO::getCouponId, reqVO.getCouponId())
                .likeIfPresent(MerchantSendCouponDO::getCouponName, reqVO.getCouponName())
                .eqIfPresent(MerchantSendCouponDO::getSendCount, reqVO.getSendCount())
                .betweenIfPresent(MerchantSendCouponDO::getExecTime, reqVO.getExecTime())
                .betweenIfPresent(MerchantSendCouponDO::getFinishTime, reqVO.getFinishTime())
                .eqIfPresent(MerchantSendCouponDO::getUseCount, reqVO.getUseCount())
                .eqIfPresent(MerchantSendCouponDO::getStatus, reqVO.getStatus())
                .eqIfPresent(MerchantSendCouponDO::getRemark, reqVO.getRemark())
                .eqIfPresent(MerchantSendCouponDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(MerchantSendCouponDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(MerchantSendCouponDO::getCreator, reqVO.getCreator())
                .eqIfPresent(MerchantSendCouponDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(MerchantSendCouponDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(MerchantSendCouponDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(MerchantSendCouponDO::getId));
    }

    List<MerchantSendCouponChartRespVO.SendCountTrendVO> selectSendCountTrend(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("granularity") String granularity
    );

    Long selectSendCount(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    BigDecimal selectUseRate(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

}