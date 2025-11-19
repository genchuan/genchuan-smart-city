package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.task.evaltasktemplate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 任务模板管理新增/修改 Request VO")
@Data
public class EvalTaskTemplateSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "模板ID")
    private String taskTemplateId;

    @Schema(description = "模板名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "模板名称不能为空")
    private String taskTemplateName;

    @Schema(description = "模板编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "模板编码不能为空")
    private String taskTemplateCode;

    @Schema(description = "适用对象类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "适用对象类型不能为空")
    private String applyObjectType;

    @Schema(description = "关联指标体系ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联指标体系ID不能为空")
    private String idxSystemId;

    @Schema(description = "关联指标体系名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联指标体系名称不能为空")
    private String idxSystemName;

    @Schema(description = "关联评价主体ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联评价主体ID不能为空")
    private String evalSubjectId;

    @Schema(description = "关联评价主体名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联评价主体名称不能为空")
    private String evalSubjectName;

    @Schema(description = "任务周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "任务周期不能为空")
    private String taskCycle;

    @Schema(description = "自定义周期")
    private String customCycle;

    @Schema(description = "模板描述")
    private String templateDesc;

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