package cn.iocoder.yudao.module.studentmgmt.controller.admin.treatmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 就诊管理反馈 Request VO")
@Data
public class TreatMgmtFeedbackReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3")
    private Long id;

    @Schema(description = "家长反馈时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2025-02-07 01:02:58")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime feedbackTime;

    @Schema(description = "家长反馈内容", requiredMode = Schema.RequiredMode.REQUIRED)
    private String feedbackContent;


}