package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetrelrule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 资产关联规则配置新增/修改 Request VO")
@Data
public class AssetRelRuleSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6991")
    private Long id;

    @Schema(description = "父级编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "25172")
    @NotNull(message = "父级编号不能为空")
    private Long parentId;

    @Schema(description = "名字", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "名字不能为空")
    private String name;

    @Schema(description = "关联规则ID（UUID）", requiredMode = Schema.RequiredMode.REQUIRED, example = "19823")
    @NotEmpty(message = "关联规则ID（UUID）不能为空")
    private String relRuleId;

    @Schema(description = "资产分类ID（小类）", requiredMode = Schema.RequiredMode.REQUIRED, example = "18297")
    @NotEmpty(message = "资产分类ID（小类）不能为空")
    private String assetCategoryId;

    @Schema(description = "资产分类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "资产分类名称不能为空")
    private String assetCategoryName;

    @Schema(description = "关联对象类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "关联对象类型不能为空")
    private String relObjType;

    @Schema(description = "关联对象ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17845")
    @NotEmpty(message = "关联对象ID不能为空")
    private String relObjId;

    @Schema(description = "关联对象名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "关联对象名称不能为空")
    private String relObjName;

    @Schema(description = "关联必填标识：1必选/0可选", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotEmpty(message = "关联必填标识：1必选/0可选不能为空")
    private String isRequired;

    @Schema(description = "关联校验规则")
    private String relCheckRule;

    @Schema(description = "启用状态：1启用/0禁用", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "启用状态：1启用/0禁用不能为空")
    private String enableStatus;

    @Schema(description = "创建人（用户ID）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人（用户ID）不能为空")
    private String createUser;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "创建时间不能为空")
    private LocalDateTime createdTime;

    @Schema(description = "更新人（用户ID）")
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

}