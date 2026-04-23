package cn.iocoder.yudao.module.inspectop.controller.admin.assetstock.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Schema(description = "巡查巡检 - 库存告警状态更新 Request VO")
@Data
public class AssetStockAlarmReqVO {

    @Schema(description = "库存ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "库存ID不能为空")
    private Long id;

    @Schema(description = "库存状态(字典:1-正常，2-低库存，3-预警库存)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "库存状态不能为空")
    @Pattern(regexp = "^[123]$", message = "库存状态必须为1、2或3")
    private String status;
}