package cn.iocoder.yudao.module.studentmgmt.controller.admin.classassign.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 确认 Request VO")
@Data
public class ClassAssignConfigReqVO {

    @Schema(description = "分班任务 ID 列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分班任务 ID 列表不能为空")
    private Long[] ids;
    @Schema(description = "确认人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "确认人不能为空")
    private String confirmUser;
    @Schema(description = "确认时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2022-07-01 00:00:00")
    @NotEmpty(message = "确认时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime confirmTime;
}