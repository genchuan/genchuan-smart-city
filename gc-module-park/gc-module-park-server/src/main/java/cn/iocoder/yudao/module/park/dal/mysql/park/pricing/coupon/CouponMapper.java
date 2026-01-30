package cn.iocoder.yudao.module.park.dal.mysql.park.pricing.coupon;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.coupon.vo.CouponPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.coupon.CouponDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface CouponMapper extends BaseMapperX<CouponDO> {

    default PageResult<CouponDO> selectPage(CouponPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CouponDO>()
                .likeIfPresent(CouponDO::getCouponName, reqVO.getCouponName())
                .eqIfPresent(CouponDO::getCouponCode, reqVO.getCouponCode())
                .eqIfPresent(CouponDO::getCouponType, reqVO.getCouponType())
                .eqIfPresent(CouponDO::getFaceValue, reqVO.getFaceValue())
                .eqIfPresent(CouponDO::getMinConsume, reqVO.getMinConsume())
                .eqIfPresent(CouponDO::getValidDays, reqVO.getValidDays())
                .betweenIfPresent(CouponDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(CouponDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(CouponDO::getApplyScope, reqVO.getApplyScope())
                .eqIfPresent(CouponDO::getScopeIds, reqVO.getScopeIds())
                .eqIfPresent(CouponDO::getGetCount, reqVO.getGetCount())
                .eqIfPresent(CouponDO::getUseCount, reqVO.getUseCount())
                .eqIfPresent(CouponDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(CouponDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(CouponDO::getRemark, reqVO.getRemark())
                .eqIfPresent(CouponDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(CouponDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(CouponDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(CouponDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(CouponDO::getId));
    }

}
