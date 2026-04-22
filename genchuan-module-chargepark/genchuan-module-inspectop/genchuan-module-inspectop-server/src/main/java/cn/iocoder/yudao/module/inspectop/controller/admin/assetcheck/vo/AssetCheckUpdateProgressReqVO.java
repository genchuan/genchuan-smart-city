package cn.iocoder.yudao.module.inspectop.controller.admin.assetcheck.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

@Schema(description = "管理后台 - 资产盘点更新进度 Request VO")
@Data
public class AssetCheckUpdateProgressReqVO {

    @Schema(description = "盘点ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "盘点ID不能为空")
    private Long id;

    @Schema(description = "盘点进度，百分比", requiredMode = Schema.RequiredMode.REQUIRED, example = "80")
    @NotNull(message = "盘点进度不能为空")
    @Min(value = 0, message = "盘点进度不能小于0")
    @Max(value = 100, message = "盘点进度不能大于100")
    private Integer progress;
}