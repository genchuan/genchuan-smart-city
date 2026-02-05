package cn.iocoder.yudao.module.park.service.park.pricing.coupon;

import java.util.*;

import cn.iocoder.yudao.module.park.controller.admin.park.pricing.coupon.vo.CouponPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.coupon.vo.CouponSaveReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.coupon.vo.ListPayAvailableCouponReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.coupon.CouponDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 优惠券 Service 接口
 *
 * @author 亘川智城
 */
public interface CouponService {

    /**
     * 创建优惠券
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCoupon(@Valid CouponSaveReqVO createReqVO);

    /**
     * 更新优惠券
     *
     * @param updateReqVO 更新信息
     */
    void updateCoupon(@Valid CouponSaveReqVO updateReqVO);

    /**
     * 删除优惠券
     *
     * @param id 编号
     */
    void deleteCoupon(Long id);

    /**
     * 获得优惠券
     *
     * @param id 编号
     * @return 优惠券
     */
    CouponDO getCoupon(Long id);

    /**
     * 获得优惠券分页
     *
     * @param pageReqVO 分页查询
     * @return 优惠券分页
     */
    PageResult<CouponDO> getCouponPage(CouponPageReqVO pageReqVO);

    PageResult<CouponDO> listPayAvailableCoupon(ListPayAvailableCouponReqVO req);
}
