package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectproblemrpt.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 巡查巡检问题统计分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InspectProblemRptPageReqVO extends PageParam {

    @Schema(description = "统计ID")
    private String problemStatId;

    @Schema(description = "统计周期")
    private String statCycle;

    @Schema(description = "统计周期名称")
    private String statCycleName;

    @Schema(description = "所属区域代码")
    private String areaCode;

    @Schema(description = "所属区域名称")
    private String areaName;

    @Schema(description = "问题类型ID")
    private String problemTypeId;

    @Schema(description = "问题类型名称")
    private String problemTypeName;

    @Schema(description = "上报问题数")
    private Integer rptProblemCount;

    @Schema(description = "一级问题数")
    private Integer level1ProblemCount;

    @Schema(description = "整改完成数")
    private Integer rectifyCompleteCount;

    @Schema(description = "整改率")
    private BigDecimal rectifyRate;

    @Schema(description = "未整改原因")
    private String unrectifiedReason;

    @Schema(description = "问题集中区域")
    private String concentratedArea;

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