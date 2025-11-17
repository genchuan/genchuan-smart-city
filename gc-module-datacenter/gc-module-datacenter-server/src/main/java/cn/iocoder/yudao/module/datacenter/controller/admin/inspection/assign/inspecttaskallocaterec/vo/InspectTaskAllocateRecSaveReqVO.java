package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.assign.inspecttaskallocaterec.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 巡查巡检任务分配记录新增/修改 Request VO")
@Data
public class InspectTaskAllocateRecSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "分配ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分配ID不能为空")
    private String allocateId;

    @Schema(description = "任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "任务ID不能为空")
    private String taskId;

    @Schema(description = "任务编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "任务编码不能为空")
    private String taskCode;

    @Schema(description = "原负责人员ID")
    private String oldPersonId;

    @Schema(description = "原负责人员姓名")
    private String oldPersonName;

    @Schema(description = "新负责人员ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "新负责人员ID不能为空")
    private String newPersonId;

    @Schema(description = "新负责人员姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "新负责人员姓名不能为空")
    private String newPersonName;

    @Schema(description = "分配原因", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分配原因不能为空")
    private String allocateReason;

    @Schema(description = "分配时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "分配时间不能为空")
    private LocalDateTime allocateTime;

    @Schema(description = "分配人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分配人不能为空")
    private String allocateUser;

    @Schema(description = "任务状态变更", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "任务状态变更不能为空")
    private String taskStatusChange;

    @Schema(description = "任务类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "任务类型不能为空")
    private String taskType;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}