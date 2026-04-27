package cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantrecharge.vo;

import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.FlexibleTimestampDeserializer;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 商户充值分页 Request VO")
@Data
public class MerchantRechargePageReqVO extends PageParam {

    @Schema(description = "商户ID，关联merchant_info.id", example = "1")
    private Long merchantId;

    @TableField(exist = false)
    @Schema(description = "商户名称")
    private String merchantName;

    @Schema(description = "充值金额")
    private BigDecimal amount;

    @Schema(description = "支付渠道：微信/支付宝/银行转账/平台余额")
    private String payChannel;

    @Schema(description = "充值状态：待支付/已支付/已取消", example = "待支付")
    private String status;

    @Schema(description = "充值订单号，唯一")
    private String orderNo;

    @Schema(description = "支付时间")
    @JsonDeserialize(using = FlexibleTimestampDeserializer.class)
    private LocalDateTime[] payTime;

    @Schema(description = "确认时间")
    @JsonDeserialize(using = FlexibleTimestampDeserializer.class)
    private LocalDateTime[] confirmTime;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}