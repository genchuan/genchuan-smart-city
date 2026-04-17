package cn.iocoder.yudao.module.studentmgmt.controller.admin.dutymgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY;

@Schema(description = "管理后台 - 排班 Request VO")
@Data
public class DutyMgmtScheduleReqVO {

    @Schema(description = "值班日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "值班日期不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY)
    private LocalDate[] dutyDate;

    @Schema(description = "值班人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "值班人不能为空")
    private String dutyUser;

    @Schema(description = "备注", example = "你猜")
    private String remark;


}