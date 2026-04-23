package cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 稽查任务新增/修改 Request VO")
@Data
public class CheckTaskSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31752")
    private Long id;

    @Schema(description = "任务类型：违规通行稽查/欠费逃费稽查/其他 关联字典inspect_task_task_type", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "任务类型：违规通行稽查/欠费逃费稽查/其他 关联字典inspect_task_task_type不能为空")
    private String taskType;

    @Schema(description = "派发时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "派发时间不能为空")
    private LocalDateTime dispatchTime;

    @Schema(description = "截止时间")
    private LocalDateTime deadlineTime;

    @Schema(description = "状态：待派发/待认领/处理中/已完成/已归档 关联字典inspect_task_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "状态：待派发/待认领/处理中/已完成/已归档 关联字典inspect_task_status不能为空")
    private String status;

    @Schema(description = "片区ID，关联片区表", example = "7751")
    private Long areaId;

    @Schema(description = "执行人ID，关联system_user用户表", example = "10872")
    private Long executeUserId;

    @Schema(description = "完成时间")
    private LocalDateTime finishTime;

    @Schema(description = "任务进度")
    private String taskProgress;

    @Schema(description = "转派理由", example = "不香")
    private String transferReason;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}