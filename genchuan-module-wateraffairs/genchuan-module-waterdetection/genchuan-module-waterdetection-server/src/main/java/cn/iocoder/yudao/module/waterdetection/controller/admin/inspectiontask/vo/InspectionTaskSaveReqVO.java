package cn.iocoder.yudao.module.waterdetection.controller.admin.inspectiontask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 巡检任务派发与执行新增/修改 Request VO")
@Data
public class InspectionTaskSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "任务ID不能为空")
    private String taskId;

    @Schema(description = "巡检人员ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "巡检人员ID不能为空")
    private String inspectorId;

    @Schema(description = "任务内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "任务内容不能为空")
    private String taskContent;

    @Schema(description = "派发时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "派发时间不能为空")
    private LocalDateTime dispatchTime;

    @Schema(description = "接收时间")
    private LocalDateTime receiveTime;

    @Schema(description = "签到时间")
    private LocalDateTime checkinTime;

    @Schema(description = "检查项结果(正常/异常)")
    private String inspectionResult;

    @Schema(description = "现场照片URL")
    private String photoUrl;

    @Schema(description = "定位信息")
    private String locationInfo;

}