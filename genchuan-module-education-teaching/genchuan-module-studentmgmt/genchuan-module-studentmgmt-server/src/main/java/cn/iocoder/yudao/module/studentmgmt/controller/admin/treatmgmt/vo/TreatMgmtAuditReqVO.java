package cn.iocoder.yudao.module.studentmgmt.controller.admin.treatmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 就诊管理审核 Request VO")
@Data
public class TreatMgmtAuditReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5311")
    private Long[] ids;

    @Schema(description = "审核人")
    private String auditUser;

    @Schema(description = "预约时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2025-02-07 01:02:58")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime applyTime;


}