package cn.iocoder.yudao.module.studentmgmt.controller.admin.bedmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 分配 Request VO")
@Data
public class BedMgmtAdjustReqVO {

    @Schema(description = "原床位 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "原床位不能为空")
    private String oldBedId;

    @Schema(description = "新床位 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "新床位不能为空")
    private String newBedId;

    @Schema(description = "学生信息", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "学生信息不能为空")
    private String studentId;

    @Schema(description = "调整时间", example = "2025-05-21 00:00:00")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime adjustTime;

}