package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalvetorule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 否决项规则管理新增/修改 Request VO")
@Data
public class EvalVetoRuleSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "否决项ID")
    private String vetoRuleId;

    @Schema(description = "否决项名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "否决项名称不能为空")
    private String vetoRuleName;

    @Schema(description = "否决项编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "否决项编码不能为空")
    private String vetoRuleCode;

    @Schema(description = "适用对象类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "适用对象类型不能为空")
    private String applyObjectType;

    @Schema(description = "否决条件", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "否决条件不能为空")
    private String vetoCondition;

    @Schema(description = "关联指标项ID")
    private String idxItemId;

    @Schema(description = "关联指标项名称")
    private String idxItemName;

    @Schema(description = "指标阈值")
    private String idxThreshold;

    @Schema(description = "生效周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "生效周期不能为空")
    private String validCycle;

    @Schema(description = "否决结果", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "否决结果不能为空")
    private String vetoResult;

    @Schema(description = "否决描述")
    private String vetoDesc;

    @Schema(description = "启用状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "启用状态不能为空")
    private String enableStatus;

    @Schema(description = "创建人(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人(业务)不能为空")
    private String createUserBiz;

    @Schema(description = "创建时间(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "创建时间(业务)不能为空")
    private LocalDateTime createTimeBiz;

    @Schema(description = "更新人(业务)")
    private String updateUserBiz;

    @Schema(description = "更新时间(业务)")
    private LocalDateTime updateTimeBiz;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}