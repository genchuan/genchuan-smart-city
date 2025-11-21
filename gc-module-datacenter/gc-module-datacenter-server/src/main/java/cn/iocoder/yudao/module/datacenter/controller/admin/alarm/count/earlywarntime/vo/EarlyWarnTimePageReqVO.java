package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarntime.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 预警告警时间维度统计分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EarlyWarnTimePageReqVO extends PageParam {

    @Schema(description = "统计ID")
    private String timeStatId;

    @Schema(description = "统计维度")
    private String timeDimension;

    @Schema(description = "统计周期值")
    private String statCycleValue;

    @Schema(description = "统计周期名称")
    private String statCycleName;

    @Schema(description = "预警总数")
    private Integer totalWarnCount;

    @Schema(description = "主要预警类型")
    private String mainWarnType;

    @Schema(description = "主要预警类型数量")
    private Integer mainTypeCount;

    @Schema(description = "已办结数")
    private Integer completedCount;

    @Schema(description = "处置率")
    private BigDecimal handleRate;

    @Schema(description = "统计人")
    private String statUserId;

    @Schema(description = "统计时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] statTime;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "create_time")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}