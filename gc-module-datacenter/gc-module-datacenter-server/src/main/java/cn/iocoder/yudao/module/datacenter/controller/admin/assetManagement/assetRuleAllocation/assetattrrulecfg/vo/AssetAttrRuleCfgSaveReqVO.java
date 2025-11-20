package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetattrrulecfg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 资产属性规则配置新增/修改 Request VO")
@Data
public class AssetAttrRuleCfgSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11052")
    private Long id;

    @Schema(description = "属性规则ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24587")
//    @NotEmpty(message = "属性规则ID不能为空")
    private String assetAttrRuleId;

    @Schema(description = "关联资产分类ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27262")
    @NotEmpty(message = "关联资产分类ID不能为空")
    private String relAssetCatId;

    @Schema(description = "关联资产分类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "关联资产分类名称不能为空")
    private String relAssetCatName;

    @Schema(description = "属性名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "属性名称不能为空")
    private String assetAttrName;

    @Schema(description = "属性代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "属性代码不能为空")
    private String assetAttrCode;

    @Schema(description = "属性数据类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "属性数据类型不能为空")
    private String attrDataType;

    @Schema(description = "属性长度")
    private String attrLength;

    @Schema(description = "属性值域")
    private String attrValueRange;

    @Schema(description = "是否必选", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "是否必选不能为空")
    private String isRequired;

    @Schema(description = "属性说明")
    private String attrDesc;

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