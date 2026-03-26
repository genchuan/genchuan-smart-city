package cn.iocoder.yudao.module.waterdetection.controller.admin.taskdispatch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 任务派发新增/修改 Request VO")
@Data
public class TaskDispatchSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "任务编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "任务编号不能为空")
    private String taskCode;

    @Schema(description = "任务类型(常规/应急)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "任务类型(常规/应急)不能为空")
    private String taskType;

    @Schema(description = "检测点清单", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "检测点清单不能为空")
    private String testPoints;

    @Schema(description = "指标清单", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "指标清单不能为空")
    private String indicators;

    @Schema(description = "截止日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "截止日期不能为空")
    private LocalDateTime deadline;

    @Schema(description = "派发部门", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "派发部门不能为空")
    private String dispatchDept;

}