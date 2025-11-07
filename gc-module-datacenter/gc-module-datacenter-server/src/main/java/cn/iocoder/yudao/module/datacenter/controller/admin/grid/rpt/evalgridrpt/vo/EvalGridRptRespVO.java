package cn.iocoder.yudao.module.datacenter.controller.admin.grid.rpt.evalgridrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 评价网格统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EvalGridRptRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "统计ID")
    @ExcelProperty("统计ID")
    private String evalStatId;

    @Schema(description = "统计周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计周期")
    private String statCycle;

    @Schema(description = "统计周期名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计周期名称")
    private String statCycleName;

    @Schema(description = "行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划代码")
    private String regionCode;

    @Schema(description = "行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划名称")
    private String regionName;

    @Schema(description = "评价网格类型(A/B/C)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评价网格类型(A/B/C)")
    private String gridType;

    @Schema(description = "关联评价指标ID")
    @ExcelProperty("关联评价指标ID")
    private String idxId;

    @Schema(description = "关联评价指标名称")
    @ExcelProperty("关联评价指标名称")
    private String idxName;

    @Schema(description = "网格总数(个)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("网格总数(个)")
    private Integer totalCount;

    @Schema(description = "总面积(m²)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("总面积(m²)")
    private Integer totalArea;

    @Schema(description = "平均所含管理数(个)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("平均所含管理数(个)")
    private BigDecimal avgMgCount;

    @Schema(description = "总所含管理网格数(个)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("总所含管理网格数(个)")
    private Integer totalMgCount;

    @Schema(description = "类型占比(%)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("类型占比(%)")
    private BigDecimal typeRatio;

    @Schema(description = "新增网格数(个)")
    @ExcelProperty("新增网格数(个)")
    private Integer newCount;

    @Schema(description = "统计人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计人")
    private String statUserId;

    @Schema(description = "统计时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计时间")
    private LocalDateTime statTime;

    @Schema(description = "报表备注", example = "你对")
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