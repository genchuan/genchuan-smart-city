package cn.iocoder.yudao.module.inspectop.controller.admin.assetinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 资产信息报废 Request VO")
@Data
public class AssetInfoScrapReqVO {

    @Schema(description = "资产ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "资产ID不能为空")
    private Long id;

    @Schema(description = "报废理由", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "报废理由不能为空")
    private String scrapRemark;
}