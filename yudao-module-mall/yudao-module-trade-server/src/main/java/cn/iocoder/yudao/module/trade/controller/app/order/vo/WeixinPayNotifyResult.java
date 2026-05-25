package cn.iocoder.yudao.module.trade.controller.app.order.vo;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 微信支付通知结果 DTO
 */
@Data
public class WeixinPayNotifyResult {

    @Schema(description = "返回状态码", example = "SUCCESS")
    private String returnCode;

    @Schema(description = "返回信息", example = "OK")
    private String returnMsg;

    @Schema(description = "业务结果", example = "SUCCESS")
    private String resultCode;

    @Schema(description = "错误代码", example = "")
    private String errCode;

    @Schema(description = "错误代码描述", example = "")
    private String errCodeDes;

    @Schema(description = "小程序/公众号ID", example = "wxd678efh567hg6787")
    private String appid;

    @Schema(description = "商户号", example = "1230000109")
    private String mchId;

    @Schema(description = "设备号", example = "")
    private String deviceInfo;

    @Schema(description = "随机字符串", example = "5K8264ILTKCH16CQ2502SI8ZNMTM67VS")
    private String nonceStr;

    @Schema(description = "签名", example = "C380BEC2BFD727A4B6845133519F3AD6")
    private String sign;

    @Schema(description = "签名类型", example = "MD5")
    private String signType;

    @Schema(description = "用户标识", example = "oUpF8uMuAJO_M2pxb1Q9zNjWeS6o")
    private String openid;

    @Schema(description = "是否关注公众账号", example = "Y")
    private String isSubscribe;

    @Schema(description = "交易类型", example = "JSAPI")
    private String tradeType;

    @Schema(description = "付款银行", example = "CFT")
    private String bankType;

    @Schema(description = "订单金额（分）", example = "100")
    private String totalFee;

    @Schema(description = "应结订单金额（分）", example = "100")
    private String settlementTotalFee;

    @Schema(description = "货币种类", example = "CNY")
    private String feeType;

    @Schema(description = "现金支付金额（分）", example = "100")
    private String cashFee;

    @Schema(description = "现金支付货币类型", example = "CNY")
    private String cashFeeType;

    @Schema(description = "代金券金额（分）", example = "0")
    private String couponFee;

    @Schema(description = "代金券使用数量", example = "0")
    private String couponCount;

    @Schema(description = "微信支付订单号", example = "1004400740201409030005092168")
    private String transactionId;

    @Schema(description = "商户订单号", example = "202508101012345678")
    private String outTradeNo;

    @Schema(description = "附加数据", example = "")
    private String attach;

    @Schema(description = "支付完成时间", example = "20140903131540")
    private String timeEnd;
}