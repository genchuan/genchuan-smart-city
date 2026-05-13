package cn.iocoder.yudao.module.studentmgmt.controller.admin.treatmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 就诊管理登记 Request VO")
@Data
public class TreatMgmtRegisterReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5311")
    private Long[] ids;

    @Schema(description = "就诊登记时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2025-02-07 01:02:58")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @NotNull(message = "就诊登记时间不能为空")
    private LocalDateTime registerTime;

    @Schema(description = "就诊内容")
    private String treatContent;


}