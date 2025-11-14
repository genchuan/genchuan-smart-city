package cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismoncomp.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 按监测部件分析研判统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AnalysisMonCompRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5048")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "统计ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "29586")
    @ExcelProperty("统计ID，唯一编码，UUID生成")
    private String statAnalysisId;

    @Schema(description = "统计周期，格式：年/季/月，如YYYY/YYYYQn/YYYYMM", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计周期，格式：年/季/月，如YYYY/YYYYQn/YYYYMM")
    private String statCycle;

    @Schema(description = "统计周期名称，如“2025年Q3”", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("统计周期名称，如“2025年Q3”")
    private String statCycleName;

    @Schema(description = "部件大类ID，关联监测部件分类表", requiredMode = Schema.RequiredMode.REQUIRED, example = "13432")
    @ExcelProperty("部件大类ID，关联监测部件分类表")
    private String compMajorId;

    @Schema(description = "部件大类名称，与大类ID同步，关联监测部件分类表", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("部件大类名称，与大类ID同步，关联监测部件分类表")
    private String compMajorName;

    @Schema(description = "部件小类ID，关联监测部件分类表", example = "15434")
    @ExcelProperty("部件小类ID，关联监测部件分类表")
    private String compMinorId;

    @Schema(description = "部件小类名称，与小类ID同步，关联监测部件分类表", example = "赵六")
    @ExcelProperty("部件小类名称，与小类ID同步，关联监测部件分类表")
    private String compMinorName;

    @Schema(description = "部件总数量，周期内该分类监测部件总数", requiredMode = Schema.RequiredMode.REQUIRED, example = "17670")
    @ExcelProperty("部件总数量，周期内该分类监测部件总数")
    private Integer totalCompCount;

    @Schema(description = "在线部件数，周期内该分类在线监测部件数", requiredMode = Schema.RequiredMode.REQUIRED, example = "32148")
    @ExcelProperty("在线部件数，周期内该分类在线监测部件数")
    private Integer onlineCompCount;

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