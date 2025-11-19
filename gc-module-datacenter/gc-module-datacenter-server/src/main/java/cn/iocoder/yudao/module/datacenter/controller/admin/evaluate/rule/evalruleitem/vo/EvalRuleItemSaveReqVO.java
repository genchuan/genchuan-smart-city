package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalruleitem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 规则项管理新增/修改 Request VO")
@Data
public class EvalRuleItemSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "规则项ID")
    private String ruleItemId;

    @Schema(description = "规则项名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "规则项名称不能为空")
    private String ruleItemName;

    @Schema(description = "规则项编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "规则项编码不能为空")
    private String ruleItemCode;

    @Schema(description = "所属规则分类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所属规则分类ID不能为空")
    private String ruleCatId;

    @Schema(description = "所属规则分类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所属规则分类名称不能为空")
    private String ruleCatName;

    @Schema(description = "关联指标项ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联指标项ID不能为空")
    private String idxItemId;

    @Schema(description = "关联指标项名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联指标项名称不能为空")
    private String idxItemName;

    @Schema(description = "评分逻辑", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "评分逻辑不能为空")
    private String scoreLogic;

    @Schema(description = "满分值", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "满分值不能为空")
    private BigDecimal fullScore;

    @Schema(description = "规则类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "规则类型不能为空")
    private String ruleType;

    @Schema(description = "规则描述")
    private String ruleDesc;

    @Schema(description = "启用状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "启用状态不能为空")
    private String enableStatus;

    @Schema(description = "创建人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人不能为空")
    private String createUser;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "创建时间不能为空")
    private LocalDateTime createTimeSys;

    @Schema(description = "更新人")
    private String updateUser;

    @Schema(description = "更新时间")
    private LocalDateTime updateTimeSys;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}