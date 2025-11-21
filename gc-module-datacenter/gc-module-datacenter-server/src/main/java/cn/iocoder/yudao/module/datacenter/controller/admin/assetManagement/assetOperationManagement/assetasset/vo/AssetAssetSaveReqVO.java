package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetasset.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 资产关联资产新增/修改 Request VO")
@Data
public class AssetAssetSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28875")
    private Long id;

    @Schema(description = "关联ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2409")
//    @NotEmpty(message = "关联ID不能为空")
    private String assetRelAssetId;

    @Schema(description = "从资产ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25474")
    @NotEmpty(message = "从资产ID不能为空")
    private String slaveAssetId;

    @Schema(description = "从资产名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "从资产名称不能为空")
    private String slaveAssetName;

    @Schema(description = "主资产ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26859")
    @NotEmpty(message = "主资产ID不能为空")
    private String masterAssetId;

    @Schema(description = "主资产名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "主资产名称不能为空")
    private String masterAssetName;

    @Schema(description = "关联类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "关联类型不能为空")
    private String relType;

    @Schema(description = "关联时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "关联时间不能为空")
    private LocalDateTime relTime;

    @Schema(description = "操作人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "操作人不能为空")
    private String operUser;

    @Schema(description = "关联说明")
    private String relDesc;

    @Schema(description = "更新人")
    private String updateUser;

    @Schema(description = "更新时间")
    private LocalDateTime updatedTime;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}