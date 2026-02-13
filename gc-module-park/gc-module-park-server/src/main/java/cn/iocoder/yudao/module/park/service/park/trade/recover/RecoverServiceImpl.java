package cn.iocoder.yudao.module.park.service.park.trade.recover;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.module.park.controller.admin.park.marketing.smoothstopcard.vo.VerifyOrderFreeReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.recover.vo.*;
import cn.iocoder.yudao.module.park.dal.dataobject.park.order.ordertemp.OrderTempDO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.coupon.CouponDO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.trade.wallet.WalletDO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.trade.walletflow.WalletFlowDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.order.ordertemp.OrderTempMapper;
import cn.iocoder.yudao.module.park.dal.mysql.park.pricing.coupon.CouponMapper;
import cn.iocoder.yudao.module.park.dal.mysql.park.trade.recover.RecoverMapper;
import cn.iocoder.yudao.module.park.dal.mysql.park.trade.wallet.WalletMapper;
import cn.iocoder.yudao.module.park.dal.mysql.park.trade.walletflow.WalletFlowMapper;
import cn.iocoder.yudao.module.park.framework.common.QrCodeUtils;
import cn.iocoder.yudao.module.park.service.park.marketing.smoothstopcard.SmoothStopCardService;
import cn.iocoder.yudao.module.park.service.park.order.ordertemp.OrderTempService;
import cn.iocoder.yudao.module.park.service.park.trade.wallet.WalletService;
import cn.iocoder.yudao.module.park.service.park.user.blackwhitelist.BlackWhiteListService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Service
public class RecoverServiceImpl implements  RecoverService{
    @Resource
    private RecoverMapper recoverMapper;

    @Resource
    private BlackWhiteListService blackWhiteListService;

    @Resource
    private WalletService walletService;

    @Resource
    private WalletMapper walletMapper;
    @Resource
    private WalletFlowMapper walletFlowMapper;
    @Resource
    private OrderTempMapper orderTempMapper;

    @Resource
    private OrderTempService orderTempService;
    @Resource
    private CouponMapper couponMapper;

    @Resource
    private SmoothStopCardService smoothStopCardService;
    @Override
    public String generateArrearsQrCode(GenerateArrearsQrCodeReqVO reqVO) {
        BigDecimal orginalAmount=reqVO.getOrginalAmount();
//        String arrearsIdListStr=reqVO.getArrearsIdListStr();
        String orderIdListStr= reqVO.getOrderIdListStr();
        String carNumber=reqVO.getCarNumber();
        Long parkLotId = reqVO.getParkLotId();

        // 对各个参数进行URL编码
//        String encodedArrearsIds = URLEncoder.encode(arrearsIdListStr, StandardCharsets.UTF_8);
        String encodedCarNumber = URLEncoder.encode(carNumber, StandardCharsets.UTF_8);
        String encodedOrderIds = orderIdListStr != null ? URLEncoder.encode(orderIdListStr, StandardCharsets.UTF_8) : "";

//        // 把金额、车牌、订单ID列表和 parkLotId 拼接到 URL
//        String payUrl = String.format(
//
//                "https://yourdomain.com/pay?orginalAmount=%s&carNumber=%s&orderIds=%s&parkLotId=%s",
//                orginalAmount.toPlainString(),
//                encodedCarNumber,
//                encodedOrderIds,
//                parkLotId.toString()
//        );
        // 把金额、车牌、订单ID列表和 parkLotId 拼接到 URL
        String payUrl = String.format(
                "http://112.47.127.21:9000/#/genchuan/pay/index?&id=%s",
//                orginalAmount.toPlainString(),
//                encodedCarNumber,
                encodedOrderIds
//                parkLotId.toString()
        );



        // 3. 生成二维码 Base64
        return QrCodeUtils.generateBase64(payUrl, 300, 300);
    }

    @Override
    public PreDiscountAutoCalculateRespVO preDiscountAutoCalculate(PreDiscountAutoCalculateReqVO reqVO) {
        //0.返参
        PreDiscountAutoCalculateRespVO respVO=new PreDiscountAutoCalculateRespVO();
        //1.获取参数
        BigDecimal orginalAmount=reqVO.getOrginalAmount();
        String orderIdListStr=reqVO.getOrderIdListStr();
        String carNumber=reqVO.getCarNumber();
        Long parkLotId= reqVO.getParkLotId();

        BigDecimal preDiscountAmount=orginalAmount;
        //2.判断是否白名单，如果是，最终金额为零
        if (blackWhiteListService.verifyWhitelistByCarNumber(carNumber)){
            preDiscountAmount=BigDecimal.ZERO;
            respVO.setPreDiscountAmount(preDiscountAmount);
            String discountReasonDesc="白名单车辆免缴费用";
            respVO.setDiscountReasonDesc(discountReasonDesc);
            respVO.setIsWhiteList(true);
            return respVO;
        }

        //3.TODO 判断畅停卡是否生效，生效最终金额为零
        //规则：获取该用户所有畅停卡列表，且在有效时间内;判断是否有适合这个车场，且车牌号符合的畅停卡，有就生效
        VerifyOrderFreeReqVO verifyOrderFreeReqVO=new VerifyOrderFreeReqVO();
        verifyOrderFreeReqVO.setCarNumber(carNumber);
        verifyOrderFreeReqVO.setParkLotId(parkLotId);
        if (smoothStopCardService.verifyOrderFreeBySmoothCard(verifyOrderFreeReqVO).isVerify()){
            preDiscountAmount=BigDecimal.ZERO;
            respVO.setPreDiscountAmount(preDiscountAmount);
            String discountReasonDesc="畅停卡生效，免缴费用";
            respVO.setDiscountReasonDesc(discountReasonDesc);
            respVO.setIsSmoothStopCard(true);
            return respVO;
        }

        //4.返回最终金额
        respVO.setPreDiscountAmount(preDiscountAmount);
        String discountReasonDesc=null;
        respVO.setDiscountReasonDesc(discountReasonDesc);
        return respVO;
    }

    /**
     * 钱包支付追缴临停订单
     *
     * <p>
     * 业务说明：
     * <ul>
     *     <li>支持一次性对多个待支付临停订单进行钱包支付</li>
     *     <li>订单可选择使用优惠券，优惠券在支付成功后标记为已使用</li>
     *     <li>支付过程中会校验订单状态、优惠券有效性及钱包余额</li>
     *     <li>支付成功后统一更新钱包余额、订单状态、优惠券状态，并生成钱包流水</li>
     * </ul>
     *
     * <p>
     * 处理流程：
     * <ol>
     *     <li>校验当前登录用户</li>
     *     <li>校验订单合法性（存在性、待支付状态、金额完整性）</li>
     *     <li>统计本次支付的实付总金额</li>
     *     <li>校验并更新使用的优惠券状态</li>
     *     <li>校验钱包状态及余额是否充足</li>
     *     <li>扣减钱包余额并生成钱包流水</li>
     *     <li>在同一事务中完成钱包、订单、优惠券的统一更新</li>
     * </ol>
     *
     * <p>
     * 事务说明：
     * <ul>
     *     <li>方法使用事务控制，任一环节异常将整体回滚</li>
     *     <li>确保钱包扣款、订单状态、优惠券状态数据一致性</li>
     * </ul>
     *
     * @param reqVO 钱包支付请求参数，包含订单列表及对应支付信息
     * @return 钱包支付结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public RecoverWalletPayRespVO walletPay(RecoverWalletPayReqVO reqVO) {
        //先校验，最终再统一进行数据库修改

        //一.设置用于数据库修改的数据
        List<OrderTempDO> updatedOrderTempDOList = new ArrayList<>();
        List<CouponDO> updatedCouponDOList = new ArrayList<>();
        WalletDO updatedWalletDO = new WalletDO();
        WalletFlowDO insertWalletFlowDO = new WalletFlowDO();

        //二.获取和设置使用的参数
        //1.获取当前登录用户
        Long userId = getLoginUserId();
        if (userId == null) {
            throw exception(new ErrorCode(500, "用户未登录"));
        }

        //2.设置支付实付总金额
        BigDecimal allPayAmount =BigDecimal.ZERO;

        //3.获取订单列表
        List<OrderTempDO> orderTempDOList = reqVO.getOrderTempDOList();
        if (orderTempDOList == null || orderTempDOList.isEmpty()) {
            throw exception(new ErrorCode(500, "订单列表不能为空"));
        }
        // 4. 从订单列表中提取本次使用的优惠券ID（去重）
        List<Long> couponIdList = orderTempDOList.stream()
                .map(OrderTempDO::getCouponId)
                .filter(Objects::nonNull)
                .toList();

        //三、校验订单、 四、设置待更新订单
        for (OrderTempDO order : orderTempDOList) {
            //三、校验订单
            //1.校验订单完整性
            if (order == null || order.getId() == null) {
                throw exception(new ErrorCode(500, "订单信息不完整"));
            }

            // 2.校验订单是否存在数据库中
            OrderTempDO dbOrder = orderTempMapper.selectById(order.getId());
            if (dbOrder == null) {
                throw exception(new ErrorCode(500, "订单不存在，订单ID：" + order.getId()));
            }

            // 3. 订单状态校验（防止重复支付）
            if (!"待支付".equals(dbOrder.getOrderStatus())) {
                throw exception(new ErrorCode(500, "订单不是待支付状态，订单ID：" + order.getId()));
            }

            //4.校验优惠券和优惠券金额（如果优惠券ID不为null，优惠金额不能为null）
            if (order.getCouponId() != null) {
                if (order.getDiscountAmount() == null) {
                    throw exception(new ErrorCode(500, "使用优惠券时，订单优惠金额不能为空"));
                }
            }

            //5.校验实付金额不能为空
            if (order.getPayAmount() == null) {
                throw exception(new ErrorCode(500, "订单的实付金额为null"));
            }

            //四、设置待更新订单
            // ==================== 构造更新对象（差量更新） ====================
            OrderTempDO updatedOrderTempDO = new OrderTempDO();
            updatedOrderTempDO.setId(dbOrder.getId());

            // 1. 实付金额
            updatedOrderTempDO.setPayAmount(order.getPayAmount());

            //2.设置优惠券和优惠金额
            updatedOrderTempDO.setCouponId(order.getCouponId());
            updatedOrderTempDO.setDiscountAmount(order.getDiscountAmount());
            // 3. 订单状态
            updatedOrderTempDO.setOrderStatus("已支付");
            // 4. 支付状态
            updatedOrderTempDO.setPayStatus("已支付");
            // 5. 支付方式
            updatedOrderTempDO.setPayType("钱包");
            // 6. 备注
            if (order.getRemark() != null) {
                updatedOrderTempDO.setRemark(order.getRemark());
            }
            //7. 更新时间
            updatedOrderTempDO.setUpdateTime(LocalDateTime.now());

            //放入到待更新列表
            updatedOrderTempDOList.add(updatedOrderTempDO);

            //实付总金额累加
            allPayAmount=allPayAmount.add(order.getPayAmount());
        }

        //五、校验优惠券 并 加入到 待更新优惠券列表
        for (Long couponId : couponIdList){
            // ==================== 8. 校验优惠券状态 并 设置待更新优惠券 ====================
            if (couponId != null) {
                CouponDO coupon = couponMapper.selectById(couponId);
                if (coupon == null) {
                    throw exception(new ErrorCode(500, "优惠券不存在"));
                }
                coupon.setStatus("已使用");
                updatedCouponDOList.add(coupon);
            }
        }

        //六、校验钱包 并 构造待修改钱包
        // ==================== 1. 查询钱包 ====================
        WalletDO wallet = walletService.getByUserId(userId);
        if (wallet == null) {
            throw exception(new ErrorCode(500, "钱包不存在"));
        }

        if (!"正常".equals(wallet.getStatus())) {
            throw exception(new ErrorCode(500, "钱包状态异常"));
        }

        // ==================== 3. 校验支付密码 ====================
        // ==================== 4. 校验余额是否充足 ====================
        BigDecimal balance = wallet.getBalance();
        if (balance.compareTo(allPayAmount) < 0) {
            throw exception(new ErrorCode(500, "钱包余额不足"));
        }

        // ==================== 5. 扣减钱包余额 ====================
        BigDecimal newBalance = balance.subtract(allPayAmount);

        updatedWalletDO.setBalance(newBalance);
        updatedWalletDO.setId(wallet.getId());

        //七、构造钱包流水记录
        // ==================== 6. 写入钱包流水 ====================
        insertWalletFlowDO.setWalletId(wallet.getId());
        insertWalletFlowDO.setUserId(userId);
        insertWalletFlowDO.setTradeCode("TRADE"+UUID.randomUUID().toString().replace("-", ""));
        insertWalletFlowDO.setAmount(allPayAmount.negate()); // 支出为负数
        insertWalletFlowDO.setBalanceAfter(newBalance);
        insertWalletFlowDO.setFlowDesc("追缴临停订单钱包支付");


        //八、最后统一更新
        //1.更新钱包
        walletMapper.updateById(updatedWalletDO);
        //2.插入钱包流水记录
        walletFlowMapper.insert(insertWalletFlowDO);
        //3.更新订单
        for (OrderTempDO updatedOrderTempDO :updatedOrderTempDOList){
            // 执行更新（最后更新—）
            orderTempMapper.updateById(updatedOrderTempDO);
        }
        //4.更新优惠券
        for (CouponDO updatedCouponDO:updatedCouponDOList){
            couponMapper.updateById(updatedCouponDO);
        }

        // ==================== 9. 事务提交 ====================
        // 方法正常结束即提交事务

        //构造返回参数
        RecoverWalletPayRespVO respVO =new RecoverWalletPayRespVO();
        respVO.setIsSuccess(true);
        return respVO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public WalletPayOrderTempRespVO walletPayOrderTemp(WalletPayOrderTempReqVO reqVO) {
        String LOCKED = "锁定";
        //先校验，最终再统一进行数据库修改

        //一.设置用于数据库修改的数据
        List<OrderTempDO> updatedOrderTempDOList = new ArrayList<>();
        List<CouponDO> updatedCouponDOList = new ArrayList<>();
        WalletDO updatedWalletDO = new WalletDO();
        WalletFlowDO insertWalletFlowDO = new WalletFlowDO();

        //二.获取和设置使用的参数
        //1.获取当前登录用户
        Long userId = getLoginUserId();
        if (userId == null) {
            throw exception(new ErrorCode(500, "用户未登录"));
        }

        //2.设置支付实付总金额
        BigDecimal allPayAmount =BigDecimal.ZERO;

        //3.获取订单列表
        List<Long> orderIdList = reqVO.getOrderTempIdList();

        List<OrderTempDO> orderTempDOList =
                orderTempMapper.selectBatchIds(orderIdList);

        if (orderTempDOList == null || orderTempDOList.isEmpty()) {
            throw exception(new ErrorCode(500, "订单列表不能为空"));
        }
        // 4. 从订单列表中提取本次使用的优惠券ID（去重）
        List<Long> couponIdList = new ArrayList<>();
//                orderTempDOList.stream()
//                .map(OrderTempDO::getCouponId)
//                .filter(Objects::nonNull)
//                .toList();

        //三、校验订单、 四、设置待更新订单
        for (OrderTempDO order : orderTempDOList) {
            //三、校验订单
            //1.校验订单完整性
            if (order == null || order.getId() == null) {
                throw exception(new ErrorCode(500, "订单信息不完整"));
            }

            // 2.校验订单是否存在数据库中
//            OrderTempDO dbOrder = orderTempMapper.selectById(order.getId());
//            if (dbOrder == null) {
//                throw exception(new ErrorCode(500, "订单不存在，订单ID：" + order.getId()));
//            }

            // 3. 订单状态校验（防止重复支付）
            if (!"待支付".equals(order.getOrderStatus())) {
                throw exception(new ErrorCode(500, "订单不是待支付状态，订单ID：" + order.getId()));
            }

            //4.校验优惠券和优惠券金额（如果优惠券ID不为null，优惠金额不能为null）
            if (order.getCouponId() != null) {
                if (order.getDiscountAmount() == null) {
                    throw exception(new ErrorCode(500, "使用优惠券时，订单优惠金额不能为空"));
                }
                //添加优惠券id到优惠券列表
                couponIdList.add(order.getCouponId());
            }

            //5.校验实付金额不能为空
            if (order.getPayAmount() == null) {
                throw exception(new ErrorCode(500, "订单的实付金额为null"));
            }

            //四、设置待更新订单
            // ==================== 构造更新对象（差量更新） ====================
            OrderTempDO updatedOrderTempDO = new OrderTempDO();
            updatedOrderTempDO.setId(order.getId());

            // 3. 订单状态
            updatedOrderTempDO.setOrderStatus("已支付");
            // 4. 支付状态
            updatedOrderTempDO.setPayStatus("已支付");
            // 5. 支付方式
            updatedOrderTempDO.setPayType("钱包");

            //7. 更新时间
            updatedOrderTempDO.setUpdateTime(LocalDateTime.now());

            //放入到待更新列表
            updatedOrderTempDOList.add(updatedOrderTempDO);

            //实付总金额累加
            allPayAmount=allPayAmount.add(order.getPayAmount());
        }

        //五、校验优惠券 并 加入到 待更新优惠券列表
        List<Long> distinctCouponIds = couponIdList.stream()
                .distinct()
                .toList();

//        List<CouponDO> couponList = couponMapper.selectBatchIds(distinctCouponIds);
        for (Long couponId : distinctCouponIds){
            // ==================== 8. 校验优惠券状态 并 设置待更新优惠券 ====================
            if (couponId != null) {
                CouponDO coupon = couponMapper.selectById(couponId);
                if (coupon == null) {
                    throw exception(new ErrorCode(500, "优惠券不存在"));
                }
                if (!LOCKED.equals(coupon.getStatus())){
                    throw exception(500,"优惠卷不是锁定状态");
                }
//                if (!Objects.equals(coupon.getLockedOrderCode(),))
                coupon.setStatus("已使用");
                coupon.setUpdateTime(LocalDateTime.now());
                updatedCouponDOList.add(coupon);
            }
        }

        //六、校验钱包 并 构造待修改钱包
        // ==================== 1. 查询钱包 ====================
        WalletDO wallet = walletService.getByUserId(userId);
        if (wallet == null) {
            throw exception(new ErrorCode(500, "钱包不存在"));
        }

        if (!"正常".equals(wallet.getStatus())) {
            throw exception(new ErrorCode(500, "钱包状态异常"));
        }

        // ==================== 3. 校验支付密码 ====================
        // ==================== 4. 校验余额是否充足 ====================
        BigDecimal balance = wallet.getBalance();
        if (balance.compareTo(allPayAmount) < 0) {
            throw exception(new ErrorCode(500, "钱包余额不足"));
        }

        // ==================== 5. 扣减钱包余额 ====================
        BigDecimal newBalance = balance.subtract(allPayAmount);

        updatedWalletDO.setBalance(newBalance);
        updatedWalletDO.setId(wallet.getId());

        //七、构造钱包流水记录
        // ==================== 6. 写入钱包流水 ====================
        insertWalletFlowDO.setWalletId(wallet.getId());
        insertWalletFlowDO.setUserId(userId);
        insertWalletFlowDO.setTradeCode("TRADE"+UUID.randomUUID().toString().replace("-", ""));
        insertWalletFlowDO.setAmount(allPayAmount.negate()); // 支出为负数
        insertWalletFlowDO.setBalanceAfter(newBalance);
        insertWalletFlowDO.setFlowDesc("追缴临停订单钱包支付");


        //八、最后统一更新
        //1.更新钱包
        walletMapper.updateById(updatedWalletDO);
        //2.插入钱包流水记录
        walletFlowMapper.insert(insertWalletFlowDO);
        //3.更新订单
        for (OrderTempDO updatedOrderTempDO :updatedOrderTempDOList){
            // 执行更新（最后更新—）
            orderTempMapper.updateById(updatedOrderTempDO);
        }
        //4.更新优惠券
        for (CouponDO updatedCouponDO:updatedCouponDOList){
            couponMapper.updateById(updatedCouponDO);
        }

        // ==================== 9. 事务提交 ====================
        // 方法正常结束即提交事务

        //构造返回参数
        WalletPayOrderTempRespVO respVO =new WalletPayOrderTempRespVO();
        respVO.setIsSuccess(true);
        return respVO;
    }
}
