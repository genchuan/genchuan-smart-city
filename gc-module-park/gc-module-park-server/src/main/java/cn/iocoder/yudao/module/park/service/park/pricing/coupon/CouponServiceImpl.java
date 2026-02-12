package cn.iocoder.yudao.module.park.service.park.pricing.coupon;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.coupon.vo.*;
import cn.iocoder.yudao.module.park.dal.dataobject.park.order.ordertemp.OrderTempDO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.coupon.CouponDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.order.ordertemp.OrderTempMapper;
import cn.iocoder.yudao.module.park.dal.mysql.park.pricing.coupon.CouponMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//import static cn.hutool.core.lang.Validator.validateNotNull;
import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.COUPON_NOT_EXISTS;
import static cn.iocoder.yudao.module.park.framework.common.BizValidator.validateNotNull;
import static cn.iocoder.yudao.module.park.framework.common.BizValidator.validateNotNullFields;

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

    @Resource
    private OrderTempMapper orderTempMapper;
    /**
     * 预览优惠券优惠金额-0211
     * <p>
     * 用于前端在选择优惠券时，展示该优惠券可减免金额及优惠后订单金额
     * 该方法只做「预览」，不会对订单或优惠券产生任何数据库修改
     *
     * @param req 预览优惠券请求参数（订单ID、优惠券ID）
     * @return 优惠金额及优惠后金额
     */
    @Override
    public PreviewCouponDiscountRespVO previewCouponDiscount(PreviewCouponDiscountReqVO req) {
        System.out.println("cs2026-02-12 17:12:03:impl");
        //一、初始化
        // 1. 获取参数
        Long couponId = req.getCouponId();
        Long orderId = req.getOrderId();

        // 2. 查询数据库的订单和优惠券
        CouponDO coupon = couponMapper.selectById(couponId);
        OrderTempDO orderTemp = orderTempMapper.selectById(orderId);

        // 3. 校验对象是否存在
        validateNotNull(
                coupon, "优惠券不存在数据库",
                orderTemp, "订单不存在数据库"
        );

        BigDecimal originalAmount = orderTemp.getOriginalAmount();

        // 二. 校验优惠券是否可用
        Map<String, Object> validateResult = validateCouponForOrder(originalAmount, coupon);
        if (!(Boolean) validateResult.get("isSuccess")) {
            throw exception(new ErrorCode(500, (String) validateResult.get("failureMessage")));
        }

        // 三. 计算优惠金额
        Map<String, Object> discountResult = calculateDiscount(originalAmount, coupon);

        // 四. 组装返回结果
        PreviewCouponDiscountRespVO respVO = new PreviewCouponDiscountRespVO();
        respVO.setDiscountAmount((BigDecimal) discountResult.get("discountAmount"));
        respVO.setAfterDiscountAmount((BigDecimal) discountResult.get("afterDiscountAmount"));

        return respVO;
    }

    /**
     * 校验优惠券是否可以用于当前订单
     *
     * <p>本方法会验证以下内容：
     * 1. 优惠券是否存在
     * 2. 优惠券状态是否为“启用”
     * 3. 优惠券的生效期（开始时间和结束时间）
     * 4. 当前订单金额是否满足优惠券的最低消费金额
     *
     * <p>示例调用：
     * Map<String,Object> result = validateCouponForOrder(orderAmount, coupon);
     * if (!(Boolean) result.get("isSuccess")) {
     *     // 处理失败情况：result.get("failureMessage")
     * }
     *
     * @param originalAmount 当前订单金额，不可为 null
     * @param coupon 要使用的优惠券对象，不可为 null
     * @return 返回 Map，其中包含：
     *         - isSuccess (Boolean)：校验是否通过
     *         - failureMessage (String)：校验失败的提示信息
     */
    public Map<String,Object> validateCouponForOrder(
            BigDecimal originalAmount,
            CouponDO coupon
    ) {
        //返参
        Map<String, Object> resultMap = new HashMap<>() {{
            put("isSuccess", false);// 默认校验未通过
            put("failureMessage", "");// 默认消息为空
        }};

        // ===== 1. 提取本方法会用到的字段 =====
        if (coupon == null) {
            resultMap.put("failureMessage", "优惠券不存在");
            return resultMap;
        }

        String couponStatus = coupon.getStatus();
        LocalDateTime couponStartTime = coupon.getStartTime();
        LocalDateTime couponEndTime = coupon.getEndTime();
        BigDecimal couponMinConsume = coupon.getMinConsume();

        validateNotNullFields(
                "couponStatus", couponStatus,
                "couponStartTime", couponStartTime,
                "couponEndTime", couponEndTime,
                "couponMinConsume", couponMinConsume,
                "originalAmount", originalAmount
        );

// 1. 状态校验
//        if (!"启用".equals(couponStatus)) {
//            System.out.println("cs2026-02-12 15:53:38:"+"优惠卷");
//            resultMap.put("failureMessage", "优惠券不是启用状态");
//            return resultMap;
//        }

// 2. 有效期校验
        LocalDateTime now = LocalDateTime.now();
        if (couponStatus != null && couponStartTime.isAfter(now)) {
            resultMap.put("failureMessage", "优惠券尚未生效");
            return resultMap;
        }
        if (couponEndTime != null && couponEndTime.isBefore(now)) {
            resultMap.put("failureMessage", "优惠券已过期");
            return resultMap;
        }

// 3. 最低消费金额校验
        if (couponMinConsume != null
                && originalAmount.compareTo(couponMinConsume) < 0) {
            resultMap.put("failureMessage", "未满足最低消费金额");
            return resultMap;
        }

// 如果全部校验通过
        resultMap.put("isSuccess", true);
        return resultMap;
    }

    /**
     * 计算优惠金额及优惠后订单金额
     *
     * <p>本方法支持以下优惠券类型：
     * 1. 满减券：直接减去面值
     * 2. 折扣券：按面值折扣计算优惠金额
     *
     * <p>示例调用：
     * Map<String,Object> result = calculateDiscount(orderAmount, coupon);
     * BigDecimal discountAmount = (BigDecimal) result.get("discountAmount");
     * BigDecimal afterDiscountAmount = (BigDecimal) result.get("afterDiscountAmount");
     *
     * @param originalAmount 当前订单金额，不可为 null
     * @param coupon 优惠券对象，不可为 null，且 couponType 和 faceValue 不可为 null
     * @return 返回 Map，其中包含：
     *         - discountAmount (BigDecimal)：计算出的优惠金额
     *         - afterDiscountAmount (BigDecimal)：优惠后的订单金额
     *
     */
    private Map<String,Object> calculateDiscount(BigDecimal originalAmount, CouponDO coupon) {
        // ===== 1. 提取字段 =====
        String couponType = coupon.getCouponType();
        String faceValue = coupon.getFaceValue();

        // ===== 2. 非空校验 =====
        validateNotNullFields(
                "originalAmount", originalAmount,
                "couponType", couponType,
                "faceValue", faceValue
        );

        // ===== 3. 初始化返参 =====
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("discountAmount", BigDecimal.ZERO);
        resultMap.put("afterDiscountAmount", originalAmount);

        // ===== 4. 计算逻辑 =====
        if ("满减券".equals(couponType)) {
            BigDecimal discountAmount = new BigDecimal(faceValue);
            BigDecimal afterDiscountAmount = originalAmount.subtract(discountAmount).max(BigDecimal.ZERO);

            resultMap.put("discountAmount", discountAmount);
            resultMap.put("afterDiscountAmount", afterDiscountAmount);
            return resultMap;
        }

        if ("折扣券".equals(couponType)) {
            BigDecimal rate = new BigDecimal(faceValue); // 0.8、0.9 之类
            BigDecimal discountAmount = originalAmount
                    .multiply(BigDecimal.ONE.subtract(rate))
                    .setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal afterDiscountAmount = originalAmount.subtract(discountAmount);

            resultMap.put("discountAmount", discountAmount);
            resultMap.put("afterDiscountAmount", afterDiscountAmount);
            return resultMap;
        }

        // 不支持的优惠券类型
        throw exception(new ErrorCode(500, "不支持的优惠券类型"));
    }

    /**
     * 0209
     * @param req
     * @return
     */
    @Override
    public PageResult<CouponDO> listPayAvailableCoupon(ListPayAvailableCouponReqVO req) {
        //0.解析参数
        BigDecimal orginalAmount=req.getOrginalAmount();
        String orderIdListStr = req.getOrderIdListStr();
        Long parkLotId = req.getParkLotId();

        //1.获取用户ID
        Long userId = getLoginUserId();
        if (userId == null) {
            throw exception(new ErrorCode(500,"用户ID为空"));
        }

        // 2. 验证参数
        if (orginalAmount == null || parkLotId == null) {
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
                wrapper.le(CouponDO::getMinConsume, orginalAmount)
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
                                .like(CouponDO::getScopeIds, parkLotId.toString()))
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

    /**
     * 计算优惠券折扣金额
     * 0209
     * <p>
     * 业务说明：
     * 1. 根据优惠券ID获取优惠券信息
     * 2. 校验优惠券是否存在、是否启用、是否在有效期内
     * 3. 校验是否满足最低消费金额
     * 4. 根据优惠券类型计算优惠金额
     * 5. 返回优惠金额和优惠后金额
     * </p>
     *

     * @return 优惠计算结果
     */
    @Override
    public CalculateDiscountRespVO calculateDiscount(CalculateDiscountReqVO req) {

        // 1. 获取优惠券信息
        CouponDO coupon = couponMapper.selectById(req.getCouponId());
        if (coupon == null) {
            throw exception(new ErrorCode(500,"优惠券不存在"));
        }

        // 2. 校验优惠券状态（必须是启用状态）
        if (!"启用".equals(coupon.getStatus())) {
            System.out.println("cs2026-02-12 15:53:38:"+"优惠卷");
//            throw exception(new ErrorCode(500, "优惠券不是启用状态"));
        }

        // 3. 校验优惠券有效期
        LocalDateTime now = LocalDateTime.now();
        if (coupon.getStartTime() != null && coupon.getStartTime().isAfter(now)) {
            throw exception(new ErrorCode(500, "优惠券尚未生效"));
        }
        if (coupon.getEndTime() != null && coupon.getEndTime().isBefore(now)) {
            throw exception(new ErrorCode(500, "优惠券已过期"));
        }

        BigDecimal originalAmount = req.getOriginalAmount();

        // 4. 校验最低消费金额
        // min_consume 为 null 表示不限制最低消费
        if (coupon.getMinConsume() != null
                && originalAmount.compareTo(coupon.getMinConsume()) < 0) {
            throw exception(new ErrorCode(500, "未满足优惠券最低消费金额"));
        }

        // 5. 根据优惠券类型计算优惠金额
        BigDecimal discountAmount = BigDecimal.ZERO;
        BigDecimal afterDiscountAmount = originalAmount;

        String couponType = coupon.getCouponType();
        String faceValue = coupon.getFaceValue();

        if ("满减券".equals(couponType)) {
            // 满减券：直接减固定金额
            BigDecimal minusAmount = new BigDecimal(faceValue);
            discountAmount = minusAmount;

        } else if ("折扣券".equals(couponType)) {
            // 折扣券：face_value 为折扣比例，例如 0.8
            BigDecimal discountRate = new BigDecimal(faceValue);
            afterDiscountAmount = originalAmount.multiply(discountRate)
                    .setScale(2, BigDecimal.ROUND_HALF_UP);
            discountAmount = originalAmount.subtract(afterDiscountAmount);

        } else {
            // 免费时长券 / 未支持的优惠券类型
            throw exception(new ErrorCode(500, "该优惠券类型不支持金额计算"));
        }

        // 6. 防止出现负数金额（最多减到 0）
        if (discountAmount.compareTo(originalAmount) > 0) {
            discountAmount = originalAmount;
        }
        afterDiscountAmount = originalAmount.subtract(discountAmount);

        // 7. 封装返回结果
        CalculateDiscountRespVO respVO = new CalculateDiscountRespVO();
        respVO.setDiscountAmount(discountAmount);
        respVO.setAfterDiscountAmount(afterDiscountAmount);

        return respVO;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createCoupon(CouponSaveReqVO createReqVO) {
        // 1. 新增时 ID 必须为空
        createReqVO.setId(null);

        // 2. 生成优惠券码
        String couponCode = "COUPON" + UUID.randomUUID().toString().replace("-", "");
        createReqVO.setCouponCode(couponCode);

        // 3. 处理状态：新建默认【启用】
        if (createReqVO.getStatus() == null) {
            createReqVO.setStatus("启用");
            // 如果你有枚举：CouponStatus.ENABLED.getValue()
        }

        // 4. 时间逻辑处理
        // 4.1 如果前端没传 startTime，默认立即生效
        if (createReqVO.getStartTime() == null) {
            createReqVO.setStartTime(LocalDateTime.now());
        }

        // 4.2 如果传了 validDays，但没传 endTime，则自动计算
        if (createReqVO.getValidDays() != null && createReqVO.getEndTime() == null) {
            createReqVO.setEndTime(
                    createReqVO.getStartTime().plusDays(createReqVO.getValidDays())
            );
        }

        // 4.3 基础校验：生效时间不能大于失效时间
        if (createReqVO.getEndTime() != null
                && createReqVO.getStartTime().isAfter(createReqVO.getEndTime())) {
            throw new IllegalArgumentException("优惠券生效时间不能晚于失效时间");
        }

        // 5. VO -> DO
        CouponDO coupon = BeanUtils.toBean(createReqVO, CouponDO.class);



        // 7. 插入
        couponMapper.insert(coupon);

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
