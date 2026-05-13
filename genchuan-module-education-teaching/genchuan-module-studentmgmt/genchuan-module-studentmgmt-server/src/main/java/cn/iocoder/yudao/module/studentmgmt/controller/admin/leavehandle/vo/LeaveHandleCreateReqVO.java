package cn.iocoder.yudao.module.studentmgmt.controller.admin.leavehandle.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 离校办理新增 Request VO")
@Data
public class LeaveHandleCreateReqVO {

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25257")
    @NotNull(message = "学生 ID不能为空")
    private Long studentId;

    @Schema(description = "离校时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2022-02-07 01:01:01")
    @NotNull(message = "离校时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime leaveTime;

    @Schema(description = "离校去处")
    private String leaveAddress;

    @Schema(description = "备注", example = "你说的对")
    private String remark;


}