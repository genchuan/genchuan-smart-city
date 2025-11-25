package cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 资产新增/修改 Request VO")
@Data
public class AssetSaveReqVO {

    @Schema(description = "资产ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19775")
    private String id;

    @Schema(description = "租户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10102")
    @NotEmpty(message = "租户ID不能为空")
    private String tenantId;

    @Schema(description = "客户ID", example = "4677")
    private String customerId;

    @Schema(description = "资产名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "资产1")
    @NotEmpty(message = "资产名称不能为空")
    private String name;

    @Schema(description = "资产类型", example = "building")
    private String type;

    @Schema(description = "标签")
    private String label;

    @Schema(description = "资产实体ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28197")
    @NotEmpty(message = "资产实体ID不能为空")
    private String assetProfileId;

    @Schema(description = "附加信息")
    private String additionalInfo;

    @Schema(description = "外部ID", example = "21772")
    private String externalId;

    @Schema(description = "版本")
    private Long version;

}