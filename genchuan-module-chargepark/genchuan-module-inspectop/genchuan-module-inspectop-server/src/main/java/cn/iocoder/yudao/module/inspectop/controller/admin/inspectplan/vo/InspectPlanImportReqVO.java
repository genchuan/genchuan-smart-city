package cn.iocoder.yudao.module.inspectop.controller.admin.inspectplan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "巡查巡检 - 巡检计划导入 Request VO")
@Data
public class InspectPlanImportReqVO {

    @Schema(description = "计划名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "月度机房巡检")
    @NotEmpty(message = "计划名称不能为空")
    private String name;

    @Schema(description = "巡检类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "routine")
    @NotEmpty(message = "巡检类型不能为空")
    private String type;

    @Schema(description = "巡检范围", requiredMode = Schema.RequiredMode.REQUIRED, example = "机房A")
    @NotEmpty(message = "巡检范围不能为空")
    private String scope;

    @Schema(description = "执行周期", requiredMode = Schema.RequiredMode.REQUIRED, example = "每月一次")
    @NotEmpty(message = "执行周期不能为空")
    private String cycle;

    @Schema(description = "计划描述", example = "检查机房设备运行状态")
    private String description;

    @Schema(description = "计划状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "enabled")
    @NotEmpty(message = "计划状态不能为空")
    private String status;

    @Schema(description = "执行进度", example = "0")
    private Integer progress;

    @Schema(description = "审核人ID", example = "1024")
    private Long auditUserId;

    @Schema(description = "生效时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime effectTime;

    @Schema(description = "完成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime finishTime;

    @Schema(description = "备用字段1", example = "备用1")
    private String reserve1;

    @Schema(description = "备用字段2", example = "备用2")
    private String reserve2;
}