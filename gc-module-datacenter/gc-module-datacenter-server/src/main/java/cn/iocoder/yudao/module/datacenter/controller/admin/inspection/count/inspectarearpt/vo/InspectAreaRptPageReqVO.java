package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectarearpt.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 巡查巡检区域统计分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InspectAreaRptPageReqVO extends PageParam {

    @Schema(description = "统计ID")
    private String areaStatId;

    @Schema(description = "统计周期")
    private String statCycle;

    @Schema(description = "统计周期名称")
    private String statCycleName;

    @Schema(description = "行政区划代码")
    private String areaCode;

    @Schema(description = "行政区划名称")
    private String areaName;

    @Schema(description = "上级区域名称")
    private String parentAreaName;

    @Schema(description = "日常巡查次数")
    private Integer dailyInspectCount;

    @Schema(description = "专项巡查次数")
    private Integer specInspectCount;

    @Schema(description = "应急巡查次数")
    private Integer emerInspectCount;

    @Schema(description = "上报问题总数")
    private Integer totalProblemCount;

    @Schema(description = "整改完成数")
    private Integer rectifyCompleteCount;

    @Schema(description = "整改率")
    private BigDecimal rectifyRate;

    @Schema(description = "问题集中类型")
    private String concentratedProblemType;

    @Schema(description = "统计人")
    private String statUser;

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