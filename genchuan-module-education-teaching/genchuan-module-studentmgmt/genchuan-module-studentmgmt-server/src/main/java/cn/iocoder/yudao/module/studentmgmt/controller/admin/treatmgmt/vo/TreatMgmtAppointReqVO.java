package cn.iocoder.yudao.module.studentmgmt.controller.admin.treatmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 就诊管理预约 Request VO")
@Data
public class TreatMgmtAppointReqVO {

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19989")
    @NotNull(message = "学生 ID不能为空")
    private Long studentId;

    @Schema(description = "就诊类型：门诊/急诊/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "就诊类型：门诊/急诊/其他不能为空")
    private String treatType;

    @Schema(description = "症状描述")
    private String symptom;

    @Schema(description = "预约时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2025-02-07 01:02:58")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime applyTime;

    @Schema(description = "备注", example = "你猜")
    private String remark;

}