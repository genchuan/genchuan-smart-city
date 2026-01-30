package cn.iocoder.yudao.module.park.service.park.pricing.coupon;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.coupon.vo.CouponPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.coupon.vo.CouponSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.coupon.CouponDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.pricing.coupon.CouponMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.COUPON_NOT_EXISTS;

/**
 * 优惠券 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class CouponServiceImpl implements CouponService {

    @Resource
    private CouponMapper couponMapper;

    @Override
    public Long createCoupon(CouponSaveReqVO createReqVO) {
        // 插入
        CouponDO coupon = BeanUtils.toBean(createReqVO, CouponDO.class);
        couponMapper.insert(coupon);
        // 返回
        return coupon.getId();
    }

    @Override
    public void updateCoupon(CouponSaveReqVO updateReqVO) {
        // 校验存在
        validateCouponExists(updateReqVO.getId());
        // 更新
        CouponDO updateObj = BeanUtils.toBean(updateReqVO, CouponDO.class);
        couponMapper.updateById(updateObj);
    }

    @Override
    public void deleteCoupon(Long id) {
        // 校验存在
        validateCouponExists(id);
        // 删除
        couponMapper.deleteById(id);
    }

    private void validateCouponExists(Long id) {
        if (couponMapper.selectById(id) == null) {
            throw exception(COUPON_NOT_EXISTS);
        }
    }

    @Override
    public CouponDO getCoupon(Long id) {
        return couponMapper.selectById(id);
    }

    @Override
    public PageResult<CouponDO> getCouponPage(CouponPageReqVO pageReqVO) {
        return couponMapper.selectPage(pageReqVO);
    }

}
