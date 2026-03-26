package cn.iocoder.yudao.module.waterdetection.controller.admin.consumablemanagement.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 耗材库存与更换管理新增/修改 Request VO")
@Data
public class ConsumableManagementSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "耗材ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "耗材ID不能为空")
    private String consumableId;

    @Schema(description = "耗材类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "耗材类型不能为空")
    private String consumableType;

    @Schema(description = "库存余量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "库存余量不能为空")
    private Double stockQuantity;

    @Schema(description = "预警阈值", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "预警阈值不能为空")
    private Double warningThreshold;

    @Schema(description = "上次更换日期")
    private LocalDateTime lastReplacementDate;

    @Schema(description = "预计下次更换日期")
    private LocalDateTime nextReplacementDate;

    @Schema(description = "更换数量")
    private Double replacementQuantity;

    @Schema(description = "关联设备ID")
    private String relatedEquipmentId;

}