package cn.iocoder.yudao.module.datacenter.controller.admin.grid.rpt.unitgridrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 单元网格统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class UnitGridRptRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "统计ID")
    @ExcelProperty("统计ID")
    private String unitStatId;

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

    @Schema(description = "行政区划级别", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划级别")
    private String regionLevel;

    @Schema(description = "比例尺")
    @ExcelProperty("比例尺")
    private String scale;

    @Schema(description = "网格总数(个)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("网格总数(个)")
    private Integer totalCount;

    @Schema(description = "在用网格数(个)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("在用网格数(个)")
    private Integer inUseCount;

    @Schema(description = "停用网格数(个)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("停用网格数(个)")
    private Integer stoppedCount;

    @Schema(description = "总面积(m²)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("总面积(m²)")
    private Integer totalArea;

    @Schema(description = "平均面积(m²)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("平均面积(m²)")
    private BigDecimal avgArea;

    @Schema(description = "新增网格数(个)")
    @ExcelProperty("新增网格数(个)")
    private Integer newCount;

    @Schema(description = "更新网格数(个)")
    @ExcelProperty("更新网格数(个)")
    private Integer updateCount;

    @Schema(description = "统计人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计人")
    private String statUserId;

    @Schema(description = "统计时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计时间")
    private LocalDateTime statTime;

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