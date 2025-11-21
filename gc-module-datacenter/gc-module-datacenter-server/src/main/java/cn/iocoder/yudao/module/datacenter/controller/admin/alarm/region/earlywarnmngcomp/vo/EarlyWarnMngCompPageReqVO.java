package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmngcomp.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 按管理部件预警告警统计分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EarlyWarnMngCompPageReqVO extends PageParam {

    @Schema(description = "统计ID")
    private String warnMngCompStatId;

    @Schema(description = "统计周期")
    private String statCycle;

    @Schema(description = "部件大类ID")
    private String compMajorId;

    @Schema(description = "部件大类名称")
    private String compMajorName;

    @Schema(description = "部件小类ID")
    private String compMinorId;

    @Schema(description = "部件小类名称")
    private String compMinorName;

    @Schema(description = "预警总数")
    private Integer totalWarnCount;

    @Schema(description = "主要故障类型")
    private String mainFaultType;

    @Schema(description = "主要分布区域代码")
    private String mainRegionCode;

    @Schema(description = "主要分布区域名称")
    private String mainRegionName;

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

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}