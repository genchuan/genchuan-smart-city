package cn.iocoder.yudao.module.park.service.park.pricing.coupon;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.coupon.vo.CouponPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.coupon.vo.CouponSaveReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.coupon.vo.ListPayAvailableCouponReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.coupon.CouponDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.pricing.coupon.CouponMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.*;

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

    @Override
    public PageResult<CouponDO> listPayAvailableCoupon(ListPayAvailableCouponReqVO req) {
        //0.解析参数
        BigDecimal orginalAmount=req.getOrginalAmount();
        String arrearsIdListStr = req.getArrearsIdListStr();
        Long parkLotId = req.getParkLotId();

        //1.获取用户ID
        Long userId = getLoginUserId();
        if (userId == null) {
            throw exception(new ErrorCode(500,"用户ID为空"));
        }

        // 2. 验证参数
        if (req.getOrginalAmount() == null || req.getParkLotId() == null) {
            throw exception(new ErrorCode(500,"支付原始金额和车场ID不能为空"));
        }

        // 3. 构建查询条件
        LambdaQueryWrapper<CouponDO> queryWrapper = new LambdaQueryWrapper<>();

        // 3.1 持有者为当前用户
        queryWrapper.eq(CouponDO::getHolderId, userId);

        // 3.2 优惠券状态是启用的
        queryWrapper.eq(CouponDO::getStatus, "启用");

        // 3.3 处理最低消费金额条件（包含null的情况）
        // null 表示没有最低消费限制，可以使用
        queryWrapper.and(wrapper ->
                wrapper.le(CouponDO::getMinConsume, req.getOrginalAmount())
                        .or()
                        .isNull(CouponDO::getMinConsume)
        );

        // 3.4 优惠券处于有效期内
        LocalDateTime now = LocalDateTime.now();
        queryWrapper.le(CouponDO::getStartTime, now);
        queryWrapper.ge(CouponDO::getEndTime, now);

        // 3.5 适用车场包含该车场id（包括全场通用的优惠券）（暂无区域）TODO 容易出问题的地方
        queryWrapper.and(wrapper ->
                wrapper.eq(CouponDO::getApplyScope, "全局")
                        .or(w -> w.eq(CouponDO::getApplyScope, "车场")
                                .like(CouponDO::getScopeIds, req.getParkLotId().toString()))
        );

//        // 3.6 优惠券未使用且未过期
//        queryWrapper.eq(CouponDO::getStatus, "启用");

        // 3.7 按有效性排序：快到期的优先显示
        queryWrapper.orderByAsc(CouponDO::getEndTime);

        // 4. 执行分页查询
        // 这里使用默认分页，如果前端需要传分页参数，可以修改ReqVO添加pageNum和pageSize字段
        Page<CouponDO> page = new Page<>(1, 100); // 默认返回前100条记录

        Page<CouponDO> couponPage = couponMapper.selectPage(page, queryWrapper);

        // 5. 转换为PageResult返回
        return new PageResult<>(couponPage.getRecords(), couponPage.getTotal());
    }

}
