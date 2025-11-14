package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.keyidxsummaryrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 关键指标汇总报表新增/修改 Request VO")
@Data
public class KeyIdxSummaryRptSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "报表ID")
    private String rptId;

    @Schema(description = "统计周期")
    private String statCycle;

    @Schema(description = "统计周期名称")
    private String statCycleName;

    @Schema(description = "行政区划代码")
    private String adminCode;

    @Schema(description = "行政区划名称")
    private String adminName;

    @Schema(description = "指标ID")
    private String idxId;

    @Schema(description = "指标名称")
    private String idxName;

    @Schema(description = "指标单位")
    private String idxUnit;

    @Schema(description = "指标重要等级")
    private String idxLevel;

    @Schema(description = "指标平均值")
    private BigDecimal idxAvg;

    @Schema(description = "指标最大值")
    private String idxMax;

    @Schema(description = "超标次数")
    private Integer exceedCount;

    @Schema(description = "环比改善率")
    private BigDecimal momImproveRate;

    @Schema(description = "报表生成时间")
    private LocalDateTime rptCreateTime;

    @Schema(description = "报表生成人")
    private Long rptCreateUser;

    @Schema(description = "报表备注")
    private String rptRemark;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}