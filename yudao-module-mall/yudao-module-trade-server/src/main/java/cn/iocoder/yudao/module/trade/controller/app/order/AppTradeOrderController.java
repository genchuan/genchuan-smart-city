package cn.iocoder.yudao.module.trade.controller.app.order;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.tenant.core.aop.TenantIgnore;
import cn.iocoder.yudao.module.pay.api.notify.dto.PayOrderNotifyReqDTO;
import cn.iocoder.yudao.module.trade.controller.app.order.vo.*;
import cn.iocoder.yudao.module.trade.controller.app.order.vo.item.AppTradeOrderItemCommentCreateReqVO;
import cn.iocoder.yudao.module.trade.controller.app.order.vo.item.AppTradeOrderItemRespVO;
import cn.iocoder.yudao.module.trade.convert.order.TradeOrderConvert;
import cn.iocoder.yudao.module.trade.dal.dataobject.delivery.DeliveryExpressDO;
import cn.iocoder.yudao.module.trade.dal.dataobject.order.TradeOrderDO;
import cn.iocoder.yudao.module.trade.dal.dataobject.order.TradeOrderItemDO;
import cn.iocoder.yudao.module.trade.enums.order.TradeOrderStatusEnum;
import cn.iocoder.yudao.module.trade.framework.order.config.TradeOrderProperties;
import cn.iocoder.yudao.module.trade.service.aftersale.AfterSaleService;
import cn.iocoder.yudao.module.trade.service.delivery.DeliveryExpressService;
import cn.iocoder.yudao.module.trade.service.order.TradeOrderQueryService;
import cn.iocoder.yudao.module.trade.service.order.TradeOrderUpdateService;
import cn.iocoder.yudao.module.trade.service.price.TradePriceService;
import com.google.common.collect.Maps;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertSet;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "用户 App - 交易订单")
@RestController
@RequestMapping("/trade/order")
@Validated
@Slf4j
public class AppTradeOrderController {

    @Resource
    private TradeOrderUpdateService tradeOrderUpdateService;
    @Resource
    private TradeOrderQueryService tradeOrderQueryService;
    @Resource
    private DeliveryExpressService deliveryExpressService;
    @Resource
    private AfterSaleService afterSaleService;
    @Resource
    private TradePriceService priceService;

    @Resource
    private TradeOrderProperties tradeOrderProperties;

    @GetMapping("/settlement")
    @Operation(summary = "获得订单结算信息")
    public CommonResult<AppTradeOrderSettlementRespVO> settlementOrder(@Valid AppTradeOrderSettlementReqVO settlementReqVO) {
        return success(tradeOrderUpdateService.settlementOrder(getLoginUserId(), settlementReqVO));
    }

    @GetMapping("/settlement-product")
    @Operation(summary = "获得商品结算信息", description = "用于商品列表、商品详情，获得参与活动后的价格信息")
    @Parameter(name = "spuIds", description = "商品 SPU 编号数组")
    @PermitAll
    public CommonResult<List<AppTradeProductSettlementRespVO>> settlementProduct(@RequestParam("spuIds") List<Long> spuIds) {
        return success(priceService.calculateProductPrice(getLoginUserId(), spuIds));
    }

    @PostMapping("/create")
    @Operation(summary = "创建订单")
    public CommonResult<AppTradeOrderCreateRespVO> createOrder(@Valid @RequestBody AppTradeOrderCreateReqVO createReqVO) {
        TradeOrderDO order = tradeOrderUpdateService.createOrder(getLoginUserId(), createReqVO);
        return success(new AppTradeOrderCreateRespVO().setId(order.getId()).setPayOrderId(order.getPayOrderId()));
    }

    @PostMapping("/update-paid")
    @Operation(summary = "更新订单为已支付") // 由 pay-module 支付服务，进行回调，可见 PayNotifyJob
    @PermitAll
    @TenantIgnore
    public CommonResult<Boolean> updateOrderPaid(@RequestBody PayOrderNotifyReqDTO notifyReqDTO) {

        log.info("[支付回调] 收到支付回调通知，商户订单号={}, 支付订单号={}, 完整数据={}",
                notifyReqDTO.getMerchantOrderId(), notifyReqDTO.getPayOrderId(), notifyReqDTO);

        tradeOrderUpdateService.updateOrderPaid(Long.valueOf(notifyReqDTO.getMerchantOrderId()),
                notifyReqDTO.getPayOrderId());

        log.info("[支付回调] 订单支付状态更新完成");
        return success(true);
    }

    @GetMapping("/get-detail")
    @Operation(summary = "获得交易订单")
    @Parameters({
            @Parameter(name = "id", description = "交易订单编号"),
            @Parameter(name = "sync", description = "是否同步支付状态", example = "true")
    })
    public CommonResult<AppTradeOrderDetailRespVO> getOrderDetail(@RequestParam("id") Long id,
                                                                  @RequestParam(value = "sync", required = false) Boolean sync) {
        // 1.1 查询订单
        TradeOrderDO order = tradeOrderQueryService.getOrder(getLoginUserId(), id);
        if (order == null) {
            return success(null);
        }
        // 1.2 sync 仅在等待支付
        if (Boolean.TRUE.equals(sync)
                && TradeOrderStatusEnum.isUnpaid(order.getStatus()) && !order.getPayStatus()) {
            tradeOrderUpdateService.syncOrderPayStatusQuietly(order.getId(), order.getPayOrderId());
            // 重新查询，因为同步后，可能会有变化
            order = tradeOrderQueryService.getOrder(id);
        }

        // 2.1 查询订单项
        List<TradeOrderItemDO> orderItems = tradeOrderQueryService.getOrderItemListByOrderId(order.getId());
        // 2.2 查询物流公司
        DeliveryExpressDO express = order.getLogisticsId() != null && order.getLogisticsId() > 0 ?
                deliveryExpressService.getDeliveryExpress(order.getLogisticsId()) : null;
        // 2.3 最终组合
        return success(TradeOrderConvert.INSTANCE.convert02(order, orderItems, tradeOrderProperties, express));
    }

    @GetMapping("/get-express-track-list")
    @Operation(summary = "获得交易订单的物流轨迹")
    @Parameter(name = "id", description = "交易订单编号")
    public CommonResult<List<AppOrderExpressTrackRespDTO>> getOrderExpressTrackList(@RequestParam("id") Long id) {
        return success(TradeOrderConvert.INSTANCE.convertList02(
                tradeOrderQueryService.getExpressTrackList(id, getLoginUserId())));
    }

    @GetMapping("/page")
    @Operation(summary = "获得交易订单分页")
    public CommonResult<PageResult<AppTradeOrderPageItemRespVO>> getOrderPage(AppTradeOrderPageReqVO reqVO) {
        // 查询订单
        PageResult<TradeOrderDO> pageResult = tradeOrderQueryService.getOrderPage(getLoginUserId(), reqVO);
        // 查询订单项
        List<TradeOrderItemDO> orderItems = tradeOrderQueryService.getOrderItemListByOrderId(
                convertSet(pageResult.getList(), TradeOrderDO::getId));
        // 最终组合
        return success(TradeOrderConvert.INSTANCE.convertPage02(pageResult, orderItems));
    }

    @GetMapping("/get-count")
    @Operation(summary = "获得交易订单数量")
    public CommonResult<Map<String, Long>> getOrderCount() {
        Map<String, Long> orderCount = Maps.newLinkedHashMapWithExpectedSize(5);
        // 全部
        orderCount.put("allCount", tradeOrderQueryService.getOrderCount(getLoginUserId(), null, null));
        // 待付款（未支付）
        orderCount.put("unpaidCount", tradeOrderQueryService.getOrderCount(getLoginUserId(),
                TradeOrderStatusEnum.UNPAID.getStatus(), null));
        // 待发货
        orderCount.put("undeliveredCount", tradeOrderQueryService.getOrderCount(getLoginUserId(),
                TradeOrderStatusEnum.UNDELIVERED.getStatus(), null));
        // 待收货
        orderCount.put("deliveredCount", tradeOrderQueryService.getOrderCount(getLoginUserId(),
                TradeOrderStatusEnum.DELIVERED.getStatus(), null));
        // 待评价
        orderCount.put("uncommentedCount", tradeOrderQueryService.getOrderCount(getLoginUserId(),
                TradeOrderStatusEnum.COMPLETED.getStatus(), false));
        // 售后数量
        orderCount.put("afterSaleCount", afterSaleService.getApplyingAfterSaleCount(getLoginUserId()));
        return success(orderCount);
    }

    @PutMapping("/receive")
    @Operation(summary = "确认交易订单收货")
    @Parameter(name = "id", description = "交易订单编号")
    public CommonResult<Boolean> receiveOrder(@RequestParam("id") Long id) {
        tradeOrderUpdateService.receiveOrderByMember(getLoginUserId(), id);
        return success(true);
    }

    @DeleteMapping("/cancel")
    @Operation(summary = "取消交易订单")
    @Parameter(name = "id", description = "交易订单编号")
    public CommonResult<Boolean> cancelOrder(@RequestParam("id") Long id) {
        tradeOrderUpdateService.cancelOrderByMember(getLoginUserId(), id);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除交易订单")
    @Parameter(name = "id", description = "交易订单编号")
    public CommonResult<Boolean> deleteOrder(@RequestParam("id") Long id) {
        tradeOrderUpdateService.deleteOrder(getLoginUserId(), id);
        return success(true);
    }

    // ========== 订单项 ==========

    @GetMapping("/item/get")
    @Operation(summary = "获得交易订单项")
    @Parameter(name = "id", description = "交易订单项编号")
    public CommonResult<AppTradeOrderItemRespVO> getOrderItem(@RequestParam("id") Long id) {
        TradeOrderItemDO item = tradeOrderQueryService.getOrderItem(getLoginUserId(), id);
        return success(TradeOrderConvert.INSTANCE.convert03(item));
    }

    @PostMapping("/item/create-comment")
    @Operation(summary = "创建交易订单项的评价")
    public CommonResult<Long> createOrderItemComment(@RequestBody AppTradeOrderItemCommentCreateReqVO createReqVO) {
        return success(tradeOrderUpdateService.createOrderItemCommentByMember(getLoginUserId(), createReqVO));
    }

    @PostMapping(value = "/update-paid/{channelId}")
    @Operation(summary = "微信支付【支付】回调")
    @PermitAll
    @TenantIgnore
    public String notifyOrderWeixin(@PathVariable("channelId") Long channelId,
                                    @RequestBody(required = false) String body,
                                    @RequestHeader Map<String, String> headers) {
        log.info("[notifyOrderWeixin][channelId({}) 收到微信支付回调，数据: {}]", channelId,
                StrUtil.sub(body, 0, 500)); // 只记录前500字符避免日志过长

        try {
            // 1. 解析微信支付通知数据
            WeixinPayNotifyResult notifyResult = parseWeixinPayNotify(body);

            // 2. 校验必要参数
            if (StrUtil.isEmpty(notifyResult.getOutTradeNo())) {
                log.error("[notifyOrderWeixin][微信支付回调数据缺少商户订单号: {}]", body);
                return createWeixinErrorResponse("FAIL", "缺少商户订单号");
            }

            // 3. 调用订单支付更新逻辑
            // 注意：微信的 out_trade_no 对应我们的 merchantOrderId
            Long orderId = Long.valueOf(notifyResult.getOutTradeNo());
            Long payOrderId = StrUtil.isNotEmpty(notifyResult.getTransactionId())
                    ? Long.valueOf(notifyResult.getTransactionId()) : 0L;

            tradeOrderUpdateService.updateOrderPaid(orderId, payOrderId);

            // 4. 返回成功响应（微信要求返回特定的XML格式）
            return createWeixinSuccessResponse();

        } catch (NumberFormatException e) {
            log.error("[notifyOrderWeixin][订单号格式错误: {}]", body, e);
            return createWeixinErrorResponse("FAIL", "订单号格式错误");
        } catch (Exception e) {
            log.error("[notifyOrderWeixin][处理微信支付回调异常]", e);
            return createWeixinErrorResponse("FAIL", "系统异常");
        }
    }

    /**
     * 解析微信支付通知数据
     * 微信支付通知是XML格式，例如：
     * <xml>
     *   <appid><![CDATA[wx1234567890]]></appid>
     *   <bank_type><![CDATA[CFT]]></bank_type>
     *   <cash_fee><![CDATA[100]]></cash_fee>
     *   <fee_type><![CDATA[CNY]]></fee_type>
     *   <is_subscribe><![CDATA[N]]></is_subscribe>
     *   <mch_id><![CDATA[1230000109]]></mch_id>
     *   <nonce_str><![CDATA[5K8264ILTKCH16CQ2502SI8ZNMTM67VS]]></nonce_str>
     *   <openid><![CDATA[oUpF8uMuAJO_M2pxb1Q9zNjWeS6o]]></openid>
     *   <out_trade_no><![CDATA[202508101012345678]]></out_trade_no>
     *   <result_code><![CDATA[SUCCESS]]></result_code>
     *   <return_code><![CDATA[SUCCESS]]></return_code>
     *   <sign><![CDATA[C380BEC2BFD727A4B6845133519F3AD6]]></sign>
     *   <time_end><![CDATA[20140903131540]]></time_end>
     *   <total_fee>100</total_fee>
     *   <trade_type><![CDATA[JSAPI]]></trade_type>
     *   <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>
     * </xml>
     */
    private WeixinPayNotifyResult parseWeixinPayNotify(String xmlData) {
        if (StrUtil.isEmpty(xmlData)) {
            throw new IllegalArgumentException("微信支付回调数据为空");
        }

        try {
            // 使用 Hutool 的 XmlUtil 解析XML
            Map<String, Object> resultMap = XmlUtil.xmlToMap(xmlData);

            WeixinPayNotifyResult result = new WeixinPayNotifyResult();
            result.setReturnCode((String) resultMap.get("return_code"));
            result.setReturnMsg((String) resultMap.get("return_msg"));
            result.setResultCode((String) resultMap.get("result_code"));
            result.setErrCode((String) resultMap.get("err_code"));
            result.setErrCodeDes((String) resultMap.get("err_code_des"));
            result.setAppid((String) resultMap.get("appid"));
            result.setMchId((String) resultMap.get("mch_id"));
            result.setDeviceInfo((String) resultMap.get("device_info"));
            result.setNonceStr((String) resultMap.get("nonce_str"));
            result.setSign((String) resultMap.get("sign"));
            result.setSignType((String) resultMap.get("sign_type"));
            result.setOpenid((String) resultMap.get("openid"));
            result.setIsSubscribe((String) resultMap.get("is_subscribe"));
            result.setTradeType((String) resultMap.get("trade_type"));
            result.setBankType((String) resultMap.get("bank_type"));
            result.setTotalFee((String) resultMap.get("total_fee"));
            result.setSettlementTotalFee((String) resultMap.get("settlement_total_fee"));
            result.setFeeType((String) resultMap.get("fee_type"));
            result.setCashFee((String) resultMap.get("cash_fee"));
            result.setCashFeeType((String) resultMap.get("cash_fee_type"));
            result.setCouponFee((String) resultMap.get("coupon_fee"));
            result.setCouponCount((String) resultMap.get("coupon_count"));
            result.setTransactionId((String) resultMap.get("transaction_id"));
            result.setOutTradeNo((String) resultMap.get("out_trade_no"));
            result.setAttach((String) resultMap.get("attach"));
            result.setTimeEnd((String) resultMap.get("time_end"));

            // 校验必要字段
            if (!"SUCCESS".equals(result.getReturnCode())) {
                log.warn("[parseWeixinPayNotify][微信支付返回失败: {}]", result.getReturnMsg());
            }

            if (!"SUCCESS".equals(result.getResultCode())) {
                log.warn("[parseWeixinPayNotify][微信支付业务失败: {} - {}]",
                        result.getErrCode(), result.getErrCodeDes());
            }

            return result;
        } catch (Exception e) {
            log.error("[parseWeixinPayNotify][解析微信支付XML失败: {}]", xmlData, e);
            throw new RuntimeException("解析微信支付通知数据失败", e);
        }
    }

    /**
     * 创建微信支付成功响应
     */
    private String createWeixinSuccessResponse() {
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("return_code", "SUCCESS");
        responseMap.put("return_msg", "OK");
        return XmlUtil.mapToXmlStr(responseMap, "xml");
    }

    /**
     * 创建微信支付错误响应
     */
    private String createWeixinErrorResponse(String returnCode, String returnMsg) {
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("return_code", returnCode);
        responseMap.put("return_msg", returnMsg);
        return XmlUtil.mapToXmlStr(responseMap, "xml");
    }

}
