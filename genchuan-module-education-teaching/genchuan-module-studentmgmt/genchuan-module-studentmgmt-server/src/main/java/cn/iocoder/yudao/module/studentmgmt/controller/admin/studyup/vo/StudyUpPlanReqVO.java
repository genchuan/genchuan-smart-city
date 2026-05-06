package cn.iocoder.yudao.module.studentmgmt.controller.admin.studyup.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 升学管理规划 Request VO")
@Data
public class StudyUpPlanReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "主键 ID不能为空")
    private Long id;

    @Schema(description = "规划内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "规划内容不能为空")
    private String planContent;

    @Schema(description = "规划时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2022-02-08 02:42:00")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @NotNull(message = "规划时间不能为空")
    private LocalDateTime planTime;


}