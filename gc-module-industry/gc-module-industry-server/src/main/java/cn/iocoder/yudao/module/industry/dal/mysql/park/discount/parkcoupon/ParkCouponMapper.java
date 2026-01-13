package cn.iocoder.yudao.module.industry.dal.mysql.park.discount.parkcoupon;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkcoupon.vo.ParkCouponPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkcoupon.ParkCouponDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkCouponMapper extends BaseMapperX<ParkCouponDO> {

    default PageResult<ParkCouponDO> selectPage(ParkCouponPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkCouponDO>()
                .eqIfPresent(ParkCouponDO::getCouponCode, reqVO.getCouponCode())
                .likeIfPresent(ParkCouponDO::getCouponName, reqVO.getCouponName())
                .eqIfPresent(ParkCouponDO::getCouponType, reqVO.getCouponType())
                .eqIfPresent(ParkCouponDO::getApplyScopeType, reqVO.getApplyScopeType())
                .eqIfPresent(ParkCouponDO::getApplyScopeValue, reqVO.getApplyScopeValue())
                .eqIfPresent(ParkCouponDO::getFaceValue, reqVO.getFaceValue())
                .eqIfPresent(ParkCouponDO::getMinConsume, reqVO.getMinConsume())
                .betweenIfPresent(ParkCouponDO::getFreeTime, reqVO.getFreeTime())
                .betweenIfPresent(ParkCouponDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(ParkCouponDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(ParkCouponDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ParkCouponDO::getUserId, reqVO.getUserId())
                .eqIfPresent(ParkCouponDO::getPromotionId, reqVO.getPromotionId())
                .betweenIfPresent(ParkCouponDO::getGetTime, reqVO.getGetTime())
                .betweenIfPresent(ParkCouponDO::getUseTime, reqVO.getUseTime())
                .eqIfPresent(ParkCouponDO::getUseOrderId, reqVO.getUseOrderId())
                .betweenIfPresent(ParkCouponDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkCouponDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ParkCouponDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkCouponDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkCouponDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkCouponDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(ParkCouponDO::getId));
    }

}
