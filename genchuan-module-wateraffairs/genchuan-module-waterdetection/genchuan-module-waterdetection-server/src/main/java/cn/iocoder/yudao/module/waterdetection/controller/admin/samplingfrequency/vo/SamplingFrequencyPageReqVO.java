package cn.iocoder.yudao.module.waterdetection.controller.admin.samplingfrequency.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 采样频率设置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SamplingFrequencyPageReqVO extends PageParam {

    @Schema(description = "采样点编号")
    private String pointCode;

    @Schema(description = "指标名称")
    private String indicatorName;

    @Schema(description = "采样频率(次/月/季)")
    private String frequency;

    @Schema(description = "执行周期")
    private String executionCycle;

    @Schema(description = "特殊时段(如汛期)调整规则")
    private String specialPeriodRule;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}