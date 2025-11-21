package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifytrack.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 巡查巡检整改进度跟踪视图新增/修改 Request VO")
@Data
public class InspectRectifyTrackSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "整改任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "整改任务ID不能为空")
    private String rectifyTaskId;

    @Schema(description = "整改任务编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "整改任务编码不能为空")
    private String rectifyTaskCode;

    @Schema(description = "关联问题名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联问题名称不能为空")
    private String problemName;

    @Schema(description = "责任部门名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "责任部门名称不能为空")
    private String deptName;

    @Schema(description = "整改负责人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "整改负责人姓名不能为空")
    private String leaderUserName;

    @Schema(description = "整改任务状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "整改任务状态不能为空")
    private String rectifyStatus;

    @Schema(description = "整改时限（小时）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "整改时限（小时）不能为空")
    private Integer rectifyDeadlineHour;

    @Schema(description = "整改截止时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "整改截止时间不能为空")
    private LocalDateTime rectifyDeadlineTime;

    @Schema(description = "剩余时间（分钟）")
    private Integer remainTimeMin;

    @Schema(description = "是否超期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否超期不能为空")
    private Boolean isOverdue;

    @Schema(description = "最新进展时间")
    private LocalDateTime latestProgressTime;

    @Schema(description = "最新进展描述")
    private String latestProgressDesc;

    @Schema(description = "最新进展照片URL")
    private String latestProgressPhoto;

    @Schema(description = "催办次数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "催办次数不能为空")
    private Integer remindCount;

    @Schema(description = "最后催办时间")
    private LocalDateTime lastRemindTime;

    @Schema(description = "派发时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "派发时间不能为空")
    private LocalDateTime dispatchTime;

}