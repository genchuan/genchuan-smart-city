package cn.iocoder.yudao.module.inspectop.controller.admin.inspectreport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "巡查巡检 - 巡检上报新增/修改 Request VO")
@Data
public class InspectReportSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "关联任务ID")
    private Long taskId;

    @Schema(description = "问题类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "问题类型不能为空")
    private String type;

    @Schema(description = "上报时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "上报时间不能为空")
    private LocalDateTime reportTime;

    @Schema(description = "上报状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "上报状态不能为空")
    private String status;

    @Schema(description = "审核人ID")
    private Long auditUserId;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "处置人ID")
    private Long processUserId;

    @Schema(description = "处置时间")
    private LocalDateTime processTime;

    @Schema(description = "上报内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "上报内容不能为空")
    private String content;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}