package cn.iocoder.yudao.module.studentmgmt.controller.admin.accessapply.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 各班级申请次数 / 类型分布统计 Request VO")
@Data
public class AccessApplyCountReqVO {

    @Schema(description = "统计时间范围", requiredMode = Schema.RequiredMode.REQUIRED,example = "时间范围参数需要符合yyyy-MM-dd HH:mm:ss格式,如：2023-01-01 00:00:00,2027-01-31 23:59:59")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] timeRange;

}