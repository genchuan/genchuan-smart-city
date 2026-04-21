package cn.iocoder.yudao.module.inspectop.controller.admin.inspecttask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "巡查巡检 - 巡检任务新增/修改 Request VO")
@Data
public class InspectTaskSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "关联计划ID")
    private Long planId;

    @Schema(description = "巡检人员ID")
    private Long userId;

    @Schema(description = "派发时间")
    private LocalDateTime dispatchTime;

    @Schema(description = "认领时间")
    private LocalDateTime claimTime;

    @Schema(description = "任务状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "任务状态不能为空")
    private String status;

    @Schema(description = "执行进度")
    private Integer progress;

    @Schema(description = "是否归档")
    private Boolean isArchive;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}