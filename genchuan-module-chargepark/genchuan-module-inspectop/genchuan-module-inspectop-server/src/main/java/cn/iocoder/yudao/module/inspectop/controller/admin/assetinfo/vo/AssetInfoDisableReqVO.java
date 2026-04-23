package cn.iocoder.yudao.module.inspectop.controller.admin.assetinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 资产信息禁用 Request VO")
@Data
public class AssetInfoDisableReqVO {

    @Schema(description = "资产ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "资产ID不能为空")
    private Long id;
}