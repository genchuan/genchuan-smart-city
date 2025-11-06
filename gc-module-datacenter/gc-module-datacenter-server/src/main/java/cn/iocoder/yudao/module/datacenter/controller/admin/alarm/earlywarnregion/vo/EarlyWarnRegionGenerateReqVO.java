package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.earlywarnregion.vo;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 生成按行政区划预警告警统计 Request VO")
@Data
public class EarlyWarnRegionGenerateReqVO {

    @Schema(description = "统计周期类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "day")
    @NotEmpty(message = "统计周期类型不能为空")
    private String statCycleType;

    @Schema(description = "统计周期值", requiredMode = Schema.RequiredMode.REQUIRED, example = "202312")
    @NotEmpty(message = "统计周期值不能为空")
    private String statCycleValue;

    @Schema(description = "行政区划级别", requiredMode = Schema.RequiredMode.REQUIRED, example = "3")
    private Integer regionLevel;

    @Schema(description = "目标行政区划代码", example = "110101001000")
    private String targetRegionCode;

//    @Schema(description = "开始时间")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime startTime;
//
//    @Schema(description = "结束时间")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime endTime;

    @Schema(description = "开始时间")
    private String startTime;

    @Schema(description = "结束时间")
    private String endTime;

    @Schema(description = "是否定时任务生成", example = "false")
    private Boolean scheduledTask = false;
}
