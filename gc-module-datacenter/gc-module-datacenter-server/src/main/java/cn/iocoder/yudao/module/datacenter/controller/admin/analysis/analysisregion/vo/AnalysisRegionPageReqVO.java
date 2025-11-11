package cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisregion.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 按行政区划分析研判统计分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AnalysisRegionPageReqVO extends PageParam {

    @Schema(description = "统计ID，唯一编码，UUID生成", example = "18816")
    private String statAnalysisId;

    @Schema(description = "统计周期，格式：年/季/月，如YYYY/YYYYQn/YYYYMM")
    private String statCycle;

    @Schema(description = "统计周期名称，中文描述，如“2025年Q3”", example = "王五")
    private String statCycleName;

    @Schema(description = "行政区划代码，符合GB/T 2260，关联行政区划表")
    private String regionCode;

    @Schema(description = "行政区划名称，与代码同步，关联行政区划表", example = "李四")
    private String regionName;

    @Schema(description = "行政区划级别，标识行政层级，如省级/市级/县级/街道级/社区级")
    private String regionLevel;

    @Schema(description = "正常监测部件数，周期内该区域正常监测部件数量", example = "2073")
    private Integer normalMonCompCount;

    @Schema(description = "异常监测部件数，周期内该区域异常监测部件数量", example = "12707")
    private Integer abnMonCompCount;

    @Schema(description = "监测事件办结率，周期内该区域监测事件办结率，0.00-100.00")
    private BigDecimal monEvtCompleteRate;

    @Schema(description = "统计人，生成报表的用户ID，关联用户信息表")
    private String statUser;

    @Schema(description = "统计时间，格式：yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] statTime;

    @Schema(description = "报表备注，如“统计范围:该区域建成区”", example = "随便")
    private String rptRemark;

    @Schema(description = "分类扩展字段1，预留，如“统计范围”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“统计范围”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储额外统计维度")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储额外统计维度")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}