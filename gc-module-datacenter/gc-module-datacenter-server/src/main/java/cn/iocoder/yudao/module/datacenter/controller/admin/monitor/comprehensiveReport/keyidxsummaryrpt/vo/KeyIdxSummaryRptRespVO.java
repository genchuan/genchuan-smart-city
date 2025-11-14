package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.keyidxsummaryrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 关键指标汇总报表 Response VO")
@Data
@ExcelIgnoreUnannotated
public class KeyIdxSummaryRptRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "报表ID")
    @ExcelProperty("报表ID")
    private String rptId;

    @Schema(description = "统计周期")
    @ExcelProperty("统计周期")
    private String statCycle;

    @Schema(description = "统计周期名称")
    @ExcelProperty("统计周期名称")
    private String statCycleName;

    @Schema(description = "行政区划代码")
    @ExcelProperty("行政区划代码")
    private String adminCode;

    @Schema(description = "行政区划名称")
    @ExcelProperty("行政区划名称")
    private String adminName;

    @Schema(description = "指标ID")
    @ExcelProperty("指标ID")
    private String idxId;

    @Schema(description = "指标名称")
    @ExcelProperty("指标名称")
    private String idxName;

    @Schema(description = "指标单位")
    @ExcelProperty("指标单位")
    private String idxUnit;

    @Schema(description = "指标重要等级")
    @ExcelProperty("指标重要等级")
    private String idxLevel;

    @Schema(description = "指标平均值")
    @ExcelProperty("指标平均值")
    private BigDecimal idxAvg;

    @Schema(description = "指标最大值")
    @ExcelProperty("指标最大值")
    private String idxMax;

    @Schema(description = "超标次数")
    @ExcelProperty("超标次数")
    private Integer exceedCount;

    @Schema(description = "环比改善率")
    @ExcelProperty("环比改善率")
    private BigDecimal momImproveRate;

    @Schema(description = "报表生成时间")
    @ExcelProperty("报表生成时间")
    private LocalDateTime rptCreateTime;

    @Schema(description = "报表生成人")
    @ExcelProperty("报表生成人")
    private Long rptCreateUser;

    @Schema(description = "报表备注")
    @ExcelProperty("报表备注")
    private String rptRemark;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}