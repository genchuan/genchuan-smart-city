package cn.iocoder.yudao.module.park.controller.admin.park.trade.walletflow.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 钱包流水 Response VO")
@Data
@ExcelIgnoreUnannotated
public class WalletFlowRespVO {

    @Schema(description = "[主键ID] 钱包流水记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "23287")
    @ExcelProperty("[主键ID] 钱包流水记录唯一标识")
    private Long id;

    @Schema(description = "[钱包ID] 钱包唯一标识，关联 user_wallet.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "32144")
    @ExcelProperty("[钱包ID] 钱包唯一标识，关联 user_wallet.id")
    private Long walletId;

    @Schema(description = "[用户ID] 用户唯一标识，关联 park_user.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "20923")
    @ExcelProperty("[用户ID] 用户唯一标识，关联 park_user.id")
    private Long userId;

    @Schema(description = "[业务交易号] 业务交易唯一编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[业务交易号] 业务交易唯一编号")
    private String tradeCode;

    @Schema(description = "[交易完成时间] 交易完成时间")
    @ExcelProperty("[交易完成时间] 交易完成时间")
    private LocalDateTime tradeFinishTime;

    @Schema(description = "[变动金额] 变动金额（正=收入 负=支出）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[变动金额] 变动金额（正=收入 负=支出）")
    private BigDecimal amount;

    @Schema(description = "[变动后余额] 变动后余额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[变动后余额] 变动后余额")
    private BigDecimal balanceAfter;

    @Schema(description = "[流水描述] 流水描述，如：支付临停订单，扣费XX", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[流水描述] 流水描述，如：支付临停订单，扣费XX")
    private String flowDesc;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[通用扩展字段1]")
    @ExcelProperty("[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    @ExcelProperty("[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    @ExcelProperty("[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    @ExcelProperty("[通用扩展字段4]")
    private String extCommon4;

}
