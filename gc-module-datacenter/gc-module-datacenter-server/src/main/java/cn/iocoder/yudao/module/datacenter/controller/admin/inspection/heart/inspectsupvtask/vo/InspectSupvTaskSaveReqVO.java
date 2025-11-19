package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvtask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 巡查巡检重点督办任务新增/修改 Request VO")
@Data
public class InspectSupvTaskSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "督办任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "督办任务ID不能为空")
    private String supvTaskId;

    @Schema(description = "督办任务编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "督办任务编码不能为空")
    private String supvTaskCode;

    @Schema(description = "关联整改任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联整改任务ID不能为空")
    private String rectifyTaskId;

    @Schema(description = "关联整改任务编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联整改任务编码不能为空")
    private String rectifyTaskCode;

    @Schema(description = "关联问题ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联问题ID不能为空")
    private String problemId;

    @Schema(description = "关联问题名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联问题名称不能为空")
    private String problemName;

    @Schema(description = "督办人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "督办人ID不能为空")
    private String supvUserId;

    @Schema(description = "督办人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "督办人姓名不能为空")
    private String supvUserName;

    @Schema(description = "督办频次", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "督办频次不能为空")
    private String supvFrequency;

    @Schema(description = "督办截止时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "督办截止时间不能为空")
    private LocalDateTime supvDeadlineTime;

    @Schema(description = "督办要求", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "督办要求不能为空")
    private String supvRequirement;

    @Schema(description = "督办任务状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "督办任务状态不能为空")
    private String supvStatus;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}