package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspect.evalinspectplan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 考察计划管理新增/修改 Request VO")
@Data
public class EvalInspectPlanSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "计划ID")
    private String inspectPlanId;

    @Schema(description = "计划名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "计划名称不能为空")
    private String inspectPlanName;

    @Schema(description = "计划编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "计划编码不能为空")
    private String inspectPlanCode;

    @Schema(description = "关联任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联任务ID不能为空")
    private String evalTaskId;

    @Schema(description = "关联任务名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联任务名称不能为空")
    private String evalTaskName;

    @Schema(description = "考察对象ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "考察对象ID不能为空")
    private String evalObjectId;

    @Schema(description = "考察对象名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "考察对象名称不能为空")
    private String evalObjectName;

    @Schema(description = "考察人员ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "考察人员ID不能为空")
    private String inspectorId;

    @Schema(description = "考察人员姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "考察人员姓名不能为空")
    private String inspectorName;

    @Schema(description = "考察时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "考察时间不能为空")
    private LocalDateTime inspectTime;

    @Schema(description = "考察方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "考察方式不能为空")
    private String inspectMethod;

    @Schema(description = "考察内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "考察内容不能为空")
    private String inspectContent;

    @Schema(description = "计划状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "计划状态不能为空")
    private String planStatus;

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