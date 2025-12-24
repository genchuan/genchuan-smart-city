package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspectionstatistics.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 环卫考核统计结果分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InspectionStatisticsPageReqVO extends PageParam {

    @Schema(description = "考核日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private String[] inspectionDate;

    @Schema(description = "场所类型")
    private String areaType;

    @Schema(description = "区域/地点名称")
    private String areaName;

    @Schema(description = "该场所总得分")
    private BigDecimal totalScore;

    @Schema(description = "该类型满分")
    private BigDecimal maxScore;

    @Schema(description = "权重")
    private BigDecimal weight;

    @Schema(description = "最终得分")
    private BigDecimal scoreWeighted;

    @Schema(description = "样本数")
    private Integer sampleCount;

    @Schema(description = "考核状态")
    private String inspectionStatus;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;
}