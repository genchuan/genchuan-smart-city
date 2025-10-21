package cn.iocoder.yudao.module.datacenter.controller.admin.assetcategoryrule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 资产分类规则配置新增/修改 Request VO")
@Data
public class AssetCategoryRuleSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8714")
    private Long id;

    @Schema(description = "父级编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "26645")
    @NotNull(message = "父级编号不能为空")
    private Long parentId;

    @Schema(description = "名字", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "名字不能为空")
    private String name;

    @Schema(description = "分类规则ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17326")
    @NotEmpty(message = "分类规则ID不能为空")
    private String categoryRuleId;

    @Schema(description = "父类规则ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16844")
    @NotEmpty(message = "父类规则ID不能为空")
    private String parentCategoryRuleId;

    @Schema(description = "分类层级", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分类层级不能为空")
    private String categoryLevel;

    @Schema(description = "分类代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分类代码不能为空")
    private String categoryCode;

    @Schema(description = "分类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "分类名称不能为空")
    private String categoryName;

    @Schema(description = "分类说明")
    private String categoryDesc;

    @Schema(description = "启用状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "启用状态不能为空")
    private String enableStatus;

    @Schema(description = "创建人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人不能为空")
    private String createdUser;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "创建时间不能为空")
    private LocalDateTime createdTime;

    @Schema(description = "更新人")
    private String updatedUser;

    @Schema(description = "更新时间")
    private LocalDateTime updatedTime;

    @Schema(description = "扩展字段1")
    private String extCategory1;

    @Schema(description = "扩展字段2")
    private String extCategory2;

}