package cn.iocoder.yudao.module.inspectop.controller.admin.assetcheck.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Schema(description = "巡查巡检 - 资产盘点执行 Request VO")
@Data
public class AssetCheckExecuteReqVO {

    @Schema(description = "盘点ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "盘点ID不能为空")
    private Long id;
}