package cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisappscene.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 按应用场景分析研判统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AnalysisAppSceneRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21988")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "统计ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "2171")
    @ExcelProperty("统计ID，唯一编码，UUID生成")
    private String statAnalysisId;

    @Schema(description = "统计周期，格式：年/季/月，如YYYY/YYYYQn/YYYYMM", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计周期，格式：年/季/月，如YYYY/YYYYQn/YYYYMM")
    private String statCycle;

    @Schema(description = "统计周期名称，如“2025年Q3”", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("统计周期名称，如“2025年Q3”")
    private String statCycleName;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

    @Schema(description = "分类扩展字段1，预留")
    @ExcelProperty("分类扩展字段1，预留")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留")
    @ExcelProperty("分类扩展字段2，预留")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留")
    @ExcelProperty("通用扩展字段1，预留")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留")
    @ExcelProperty("通用扩展字段2，预留")
    private String extCommon2;

}