package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetappscene.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 资产关联应用场景新增/修改 Request VO")
@Data
public class AssetAppSceneSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8255")
    private Long id;

    @Schema(description = "关联ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26199")
//    @NotEmpty(message = "关联ID不能为空")
    private String assetRelAppSceneId;

    @Schema(description = "关联资产ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18038")
    @NotEmpty(message = "关联资产ID不能为空")
    private String relAssetId;

    @Schema(description = "关联资产名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "关联资产名称不能为空")
    private String relAssetName;

    @Schema(description = "应用场景ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10359")
    @NotEmpty(message = "应用场景ID不能为空")
    private String appSceneId;

    @Schema(description = "应用场景编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "应用场景编码不能为空")
    private String appSceneCode;

    @Schema(description = "应用场景名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "应用场景名称不能为空")
    private String appSceneName;

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

    @Schema(description = "分类扩展字段1）")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}