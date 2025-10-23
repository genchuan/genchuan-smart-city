package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetattrrule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 资产属性规则配置新增/修改 Request VO")
@Data
public class AssetAttrRuleSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "13071")
    private Long id;

    @Schema(description = "父级编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "19931")
    @NotNull(message = "父级编号不能为空")
    private Long parentId;

    @Schema(description = "属性规则ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8208")
    @NotEmpty(message = "属性规则ID不能为空")
    private String attrRuleId;

    @Schema(description = "资产分类ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19918")
    @NotEmpty(message = "资产分类ID不能为空")
    private String assetCategoryId;

    @Schema(description = "资产分类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "资产分类名称不能为空")
    private String assetCategoryName;

    @Schema(description = "属性名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "属性名称不能为空")
    private String attrName;

    @Schema(description = "属性代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "属性代码不能为空")
    private String attrCode;

    @Schema(description = "数据类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "数据类型不能为空")
    private String dataType;

    @Schema(description = "字段长度")
    private Integer fieldLength;

    @Schema(description = "是否必选", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "是否必选不能为空")
    private String isRequired;

    @Schema(description = "计量单位")
    private String unit;

    @Schema(description = "值域范围")
    private String valueRange;

    @Schema(description = "默认值")
    private String defaultValue;

    @Schema(description = "属性说明")
    private String attrDesc;

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
    private String extCategory1;

    @Schema(description = "分类扩展字段2")
    private String extCategory2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "名字", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "名字不能为空")
    private String name;

}