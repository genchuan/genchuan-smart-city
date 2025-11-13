package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnregion.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.ExcelProperty;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 预警明细 Response VO")
@Data
public class WarningAlertRespVO {

    @Schema(description = "预警ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("预警ID")
    private Long id;

    @Schema(description = "告警编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("告警编号")
    private String alertCode;

    @Schema(description = "关联对象ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联对象ID")
    private String relatedObjectId;

    @Schema(description = "关联对象名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联对象名称")
    private String relatedObjectName;

    @Schema(description = "预警类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("预警类型")
    private String warningType;

    @Schema(description = "预警等级", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("预警等级")
    private String warningLevel;

    @Schema(description = "预警状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("预警状态")
    private String warningStatus;

    @Schema(description = "触发原因")
    @ExcelProperty("触发原因")
    private String triggerReason;

    @Schema(description = "触发时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("触发时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime triggerTime;

    @Schema(description = "要求完成时间")
    @ExcelProperty("要求完成时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime requiredCompleteTime;

    @Schema(description = "处置进展描述")
    @ExcelProperty("处置进展描述")
    private String disposalProgressDesc;

    @Schema(description = "责任人")
    @ExcelProperty("责任人")
    private String responsiblePerson;

    @Schema(description = "审核状态")
    @ExcelProperty("审核状态")
    private Integer status;
}