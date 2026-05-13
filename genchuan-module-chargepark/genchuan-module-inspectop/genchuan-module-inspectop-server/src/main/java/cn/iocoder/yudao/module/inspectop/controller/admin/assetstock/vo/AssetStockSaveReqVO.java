package cn.iocoder.yudao.module.inspectop.controller.admin.assetstock.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "巡查巡检 - 库存管理新增/修改 Request VO")
@Data
public class AssetStockSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "资产ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "资产ID不能为空")
    private Long assetId;

    @Schema(description = "当前库存", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "当前库存不能为空")
    private Integer currentStock;

    @Schema(description = "预警阈值", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "预警阈值不能为空")
    private Integer warnThreshold;

    @Schema(description = "库存状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "库存状态不能为空")
    private String status;

    @Schema(description = "所属场站ID")
    private Long stationId;

    @Schema(description = "补货记录")
    private String reserve1;

    @Schema(description = "调配记录")
    private String reserve2;

}