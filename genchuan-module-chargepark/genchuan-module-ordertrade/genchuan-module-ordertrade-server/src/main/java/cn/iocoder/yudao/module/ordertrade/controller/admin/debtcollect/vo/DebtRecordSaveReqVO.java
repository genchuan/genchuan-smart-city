package cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 逃费记录新增/修改 Request VO")
@Data
public class DebtRecordSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "车牌号码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "车牌号码不能为空")
    private String carNo;

    @Schema(description = "欠费金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "欠费金额不能为空")
    private BigDecimal debtAmount;

    @Schema(description = "状态：uncollected/collecting/completed")
    private String status;

    @Schema(description = "追缴完成时间")
    private LocalDateTime collectTime;

    @Schema(description = "备注")
    private String remark;
}
