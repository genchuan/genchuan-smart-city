package cn.iocoder.yudao.module.park.controller.admin.park.trade.walletflow.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 钱包流水分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WalletFlowPageReqVO extends PageParam {

    @Schema(description = "[钱包ID] 钱包唯一标识，关联 user_wallet.id", example = "32144")
    private Long walletId;

    @Schema(description = "[用户ID] 用户唯一标识，关联 park_user.id", example = "20923")
    private Long userId;

    @Schema(description = "[业务交易号] 业务交易唯一编号")
    private String tradeCode;

    @Schema(description = "[交易完成时间] 交易完成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] tradeFinishTime;

    @Schema(description = "[变动金额] 变动金额（正=收入 负=支出）")
    private BigDecimal amount;

    @Schema(description = "[变动后余额] 变动后余额")
    private BigDecimal balanceAfter;

    @Schema(description = "[流水描述] 流水描述，如：支付临停订单，扣费XX")
    private String flowDesc;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

}
