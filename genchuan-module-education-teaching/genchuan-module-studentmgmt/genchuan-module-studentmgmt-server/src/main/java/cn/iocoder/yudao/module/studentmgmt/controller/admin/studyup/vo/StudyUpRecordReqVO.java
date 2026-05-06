package cn.iocoder.yudao.module.studentmgmt.controller.admin.studyup.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 升学管理记录 Request VO")
@Data
public class StudyUpRecordReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "主键 ID不能为空")
    private Long id;

    @Schema(description = "跟踪备注")
    private String remark;

    @Schema(description = "跟踪记录时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2022-02-08 02:42:00")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @NotNull(message = "跟踪记录时间不能为空")
    private LocalDateTime recordTime;


}