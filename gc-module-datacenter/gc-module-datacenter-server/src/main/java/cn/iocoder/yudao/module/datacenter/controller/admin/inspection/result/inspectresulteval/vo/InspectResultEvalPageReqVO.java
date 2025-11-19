package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.result.inspectresulteval.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 巡查巡检结果评估分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InspectResultEvalPageReqVO extends PageParam {

    @Schema(description = "评估ID")
    private String evalId;

    @Schema(description = "评估编码")
    private String evalCode;

    @Schema(description = "评估周期")
    private String evalCycle;

    @Schema(description = "评估周期名称")
    private String evalCycleName;

    @Schema(description = "所属区域代码")
    private String areaCode;

    @Schema(description = "所属区域名称")
    private String areaName;

    @Schema(description = "日常巡查合格率")
    private BigDecimal dailyQualifiedRate;

    @Schema(description = "专项巡查整改率")
    private BigDecimal specRectifyRate;

    @Schema(description = "一级问题处置及时率")
    private BigDecimal level1TimelyRate;

    @Schema(description = "问题集中区域")
    private String problemConcentratedArea;

    @Schema(description = "主要问题类型")
    private String mainProblemType;

    @Schema(description = "评估意见")
    private String evalOpinion;

    @Schema(description = "评估人")
    private String evalUser;

    @Schema(description = "评估时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] evalTime;

    @Schema(description = "评估报告URL")
    private String evalRptUrl;

    @Schema(description = "创建人")
    private String createUser;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] bizCreateTime;

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