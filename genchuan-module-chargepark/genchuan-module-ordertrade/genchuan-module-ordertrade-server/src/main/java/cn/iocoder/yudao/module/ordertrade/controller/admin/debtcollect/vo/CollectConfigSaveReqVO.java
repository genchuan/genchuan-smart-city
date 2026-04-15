package cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 追缴配置新增/修改 Request VO")
@Data
public class CollectConfigSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "欠费阈值时间（天）", requiredMode = Schema.RequiredMode.REQUIRED, example = "7")
    @NotNull(message = "阈值时间不能为空")
    private Integer thresholdTime;

    @Schema(description = "欠费阈值金额", requiredMode = Schema.RequiredMode.REQUIRED, example = "50")
    @NotNull(message = "阈值金额不能为空")
    private BigDecimal thresholdAmount;

    @Schema(description = "推送方式：miniapp/sms/phone", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "推送方式不能为空")
    private String pushWay;

    @Schema(description = "状态：inactive/active")
    private String status;

    @Schema(description = "备注")
    private String remark;
}
