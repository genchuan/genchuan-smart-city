package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetcatrulecfg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 资产分类规则配置新增/修改 Request VO")
@Data
public class AssetCatRuleCfgSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19245")
    private Long id;

    @Schema(description = "分类规则ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "32430")
    private String assetCatRuleId;

    @Schema(description = "规则名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "规则名称不能为空")
    private String ruleName;

    @Schema(description = "大类编码位数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "大类编码位数不能为空")
    private Integer majorCodeLength;

    @Schema(description = "中类编码位数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "中类编码位数不能为空")
    private Integer midCodeLength;

    @Schema(description = "小类编码位数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "小类编码位数不能为空")
    private Integer minorCodeLength;

    @Schema(description = "编码生成逻辑", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "编码生成逻辑不能为空")
    private String codeGenLogic;

    @Schema(description = "适用资产领域", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "适用资产领域不能为空")
    private String assetDom;

    @Schema(description = "启用状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "启用状态不能为空")
    private String enableStatus;

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