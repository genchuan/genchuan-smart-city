package cn.iocoder.yudao.module.industry.service.park.discount.parkcoupon;

import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkcoupon.vo.ParkCouponPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkcoupon.vo.ParkCouponSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkcoupon.ParkCouponDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.discount.parkcoupon.ParkCouponMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 优惠券 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkCouponServiceImpl implements ParkCouponService {

    @Resource
    private ParkCouponMapper parkCouponMapper;

    @Override
    public Long createParkCoupon(ParkCouponSaveReqVO createReqVO) {
        // 插入
        ParkCouponDO parkCoupon = BeanUtils.toBean(createReqVO, ParkCouponDO.class);
        parkCouponMapper.insert(parkCoupon);
        // 返回
        return parkCoupon.getId();
    }

    @Override
    public void updateParkCoupon(ParkCouponSaveReqVO updateReqVO) {
        // 校验存在
        validateParkCouponExists(updateReqVO.getId());
        // 更新
        ParkCouponDO updateObj = BeanUtils.toBean(updateReqVO, ParkCouponDO.class);
        parkCouponMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkCoupon(Long id) {
        // 校验存在
        validateParkCouponExists(id);
        // 删除
        parkCouponMapper.deleteById(id);
    }

    private void validateParkCouponExists(Long id) {
        if (parkCouponMapper.selectById(id) == null) {
            throw exception(PARK_COUPON_NOT_EXISTS);
        }
    }

    @Override
    public ParkCouponDO getParkCoupon(Long id) {
        return parkCouponMapper.selectById(id);
    }

    @Override
    public PageResult<ParkCouponDO> getParkCouponPage(ParkCouponPageReqVO pageReqVO) {
        return parkCouponMapper.selectPage(pageReqVO);
    }

}
