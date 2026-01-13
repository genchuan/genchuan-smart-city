package cn.iocoder.yudao.module.industry.service.park.discount.parkcoupon;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkcoupon.vo.ParkCouponPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkcoupon.vo.ParkCouponSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkcoupon.ParkCouponDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 优惠券 Service 接口
 *
 * @author lxs
 */
public interface ParkCouponService {

    /**
     * 创建优惠券
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkCoupon(@Valid ParkCouponSaveReqVO createReqVO);

    /**
     * 更新优惠券
     *
     * @param updateReqVO 更新信息
     */
    void updateParkCoupon(@Valid ParkCouponSaveReqVO updateReqVO);

    /**
     * 删除优惠券
     *
     * @param id 编号
     */
    void deleteParkCoupon(Long id);

    /**
     * 获得优惠券
     *
     * @param id 编号
     * @return 优惠券
     */
    ParkCouponDO getParkCoupon(Long id);

    /**
     * 获得优惠券分页
     *
     * @param pageReqVO 分页查询
     * @return 优惠券分页
     */
    PageResult<ParkCouponDO> getParkCouponPage(ParkCouponPageReqVO pageReqVO);

}
