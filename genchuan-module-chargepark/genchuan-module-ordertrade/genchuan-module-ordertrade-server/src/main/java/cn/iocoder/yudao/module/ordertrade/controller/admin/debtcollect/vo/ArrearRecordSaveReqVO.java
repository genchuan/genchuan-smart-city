package cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 欠费记录新增/修改 Request VO")
@Data
public class ArrearRecordSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "用户不能为空")
    private Long userId;

    @Schema(description = "总欠费金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "总欠费金额不能为空")
    private BigDecimal totalAmount;

    @Schema(description = "未结清金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "未结清金额不能为空")
    private BigDecimal unPayAmount;

    @Schema(description = "状态：unpaid/paid")
    private String status;

    @Schema(description = "备注")
    private String remark;
}
