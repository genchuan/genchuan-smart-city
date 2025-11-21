package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarngrid.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 按网格分域预警告警统计分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EarlyWarnGridPageReqVO extends PageParam {

    @Schema(description = "统计ID")
    private String warnGridStatId;

    @Schema(description = "统计周期")
    private String statCycle;

    @Schema(description = "网格类型")
    private String gridType;

    @Schema(description = "网格ID")
    private String gridId;

    @Schema(description = "网格编码")
    private String gridCode;

    @Schema(description = "网格名称")
    private String gridName;

    @Schema(description = "所属街道代码")
    private String streetCode;

    @Schema(description = "所属街道名称")
    private String streetName;

    @Schema(description = "预警总数")
    private Integer totalWarnCount;

    @Schema(description = "已办结数")
    private Integer completedCount;

    @Schema(description = "待处置数")
    private Integer pendCount;

    @Schema(description = "主要预警类型")
    private String mainWarnType;

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