package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetrelrulecfg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 资产关联规则配置新增/修改 Request VO")
@Data
public class AssetRelRuleCfgSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5490")
    private Long id;

    @Schema(description = "关联规则ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "710")
//    @NotEmpty(message = "关联规则ID不能为空")
    private String assetRelRuleId;

    @Schema(description = "关联资产分类ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8418")
    @NotEmpty(message = "关联资产分类ID不能为空")
    private String relAssetCatId;

    @Schema(description = "关联资产分类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "关联资产分类名称不能为空")
    private String relAssetCatName;

    @Schema(description = "关联对象类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "关联对象类型不能为空")
    private String relObjectType;

    @Schema(description = "关联对象名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "关联对象名称不能为空")
    private String relObjectName;

    @Schema(description = "是否必选", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "是否必选不能为空")
    private String isRequired;

    @Schema(description = "关联数量限制", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "关联数量限制不能为空")
    private Integer relQuantityLimit;

    @Schema(description = "关联对象来源表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联对象来源表不能为空")
    private String relObjectSourceTable;

    @Schema(description = "关联说明")
    private String relDesc;

    @Schema(description = "创建人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人不能为空")
    private String createUser;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "创建时间不能为空")
    private LocalDateTime createdTime;

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