package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.task.evaltask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 任务管理新增/修改 Request VO")
@Data
public class EvalTaskSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "任务ID")
    private String evalTaskId;

    @Schema(description = "任务名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "任务名称不能为空")
    private String evalTaskName;

    @Schema(description = "任务编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "任务编码不能为空")
    private String evalTaskCode;

    @Schema(description = "关联模板ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联模板ID不能为空")
    private String taskTemplateId;

    @Schema(description = "关联模板名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联模板名称不能为空")
    private String taskTemplateName;

    @Schema(description = "评价对象范围", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "评价对象范围不能为空")
    private String objectScope;

    @Schema(description = "关联对象IDs")
    private String relateObjectIds;

    @Schema(description = "关联对象Names")
    private String relateObjectNames;

    @Schema(description = "任务开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "任务开始时间不能为空")
    private LocalDateTime taskStartTime;

    @Schema(description = "任务结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "任务结束时间不能为空")
    private LocalDateTime taskEndTime;

    @Schema(description = "任务状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "任务状态不能为空")
    private String taskStatus;

    @Schema(description = "数据采集方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "数据采集方式不能为空")
    private String dataCollectMethod;

    @Schema(description = "任务描述")
    private String taskDesc;

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