package cn.iocoder.yudao.module.smartcity.controller.admin.maintenancetasks.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 养护任务新增/修改 Request VO")
@Data
public class MaintenanceTasksSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "14467")
    private Long id;

    @Schema(description = "任务编号")
    private String taskNumber;

    @Schema(description = "任务名称")
    private String task;

    @Schema(description = "养护地块")
    private String maintainTheLandParcel;

    @Schema(description = "养护人员")
    private String maintenancePersonnel;

    @Schema(description = "任务开始时间")
    private LocalDateTime taskStartTime;

    @Schema(description = "任务结束时间")
    private LocalDateTime taskEndTime;

    @Schema(description = "任务内容")
    private String taskContent;

    @Schema(description = "所需工具")
    private String requiredTools;

    @Schema(description = "所需材料")
    private String requiredMaterials;

    @Schema(description = "任务优先级")
    private String taskPriority;

    @Schema(description = "任务状态", example = "1")
    private String status;

    @Schema(description = "完成情况说明", example = "你说的对")
    private String completionStatusDescription;

}