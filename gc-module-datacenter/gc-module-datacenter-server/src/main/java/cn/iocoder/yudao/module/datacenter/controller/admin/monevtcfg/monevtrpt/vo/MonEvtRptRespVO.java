package cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 监测事件统计报 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MonEvtRptRespVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22321")
    @ExcelProperty("自增主键ID")
    private Long id;

    @Schema(description = "唯一编码，采用UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "14355")
    @ExcelProperty("唯一编码，采用UUID生成")
    private String statId;

    @Schema(description = "统计周期类型，格式:“YYYY”“YYYYQn”“YYYYMM”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计周期类型，格式:“YYYY”“YYYYQn”“YYYYMM”")
    private String statCycle;

    @Schema(description = "如“2025年09月”", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("如“2025年09月”")
    private String statCycleName;

    @Schema(description = "符合GB/T 2260，统计区域的行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("符合GB/T 2260，统计区域的行政区划代码")
    private String regionCode;

    @Schema(description = "与行政区划代码关联，自动同步名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("与行政区划代码关联，自动同步名称")
    private String regionName;

    @Schema(description = "关联监测事件分类配置表的大类ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "7195")
    @ExcelProperty("关联监测事件分类配置表的大类ID")
    private String evtMajorId;

    @Schema(description = "与事件大类ID关联，自动同步名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("与事件大类ID关联，自动同步名称")
    private String evtMajorName;

    @Schema(description = "关联监测事件分类配置表的小类ID（钻取时必填）", example = "11552")
    @ExcelProperty("关联监测事件分类配置表的小类ID（钻取时必填）")
    private String evtMinorId;

    @Schema(description = "与事件小类ID关联，自动同步名称", example = "王五")
    @ExcelProperty("与事件小类ID关联，自动同步名称")
    private String evtMinorName;

    @Schema(description = "事件处置部门统一社会信用代码")
    @ExcelProperty("事件处置部门统一社会信用代码")
    private String deptCode;

    @Schema(description = "与处置部门代码关联，自动同步名称", example = "芋艿")
    @ExcelProperty("与处置部门代码关联，自动同步名称")
    private String deptName;

    @Schema(description = "统计周期内该维度下事件上报总数", requiredMode = Schema.RequiredMode.REQUIRED, example = "26160")
    @ExcelProperty("统计周期内该维度下事件上报总数")
    private Integer totalRptCount;

    @Schema(description = "状态为“待处置”的事件数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "28863")
    @ExcelProperty("状态为“待处置”的事件数量")
    private Integer pendCount;

    @Schema(description = "状态为“处置中”的事件数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "23444")
    @ExcelProperty("状态为“处置中”的事件数量")
    private Integer handlCount;

    @Schema(description = "状态为“已办结”的事件数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "28952")
    @ExcelProperty("状态为“已办结”的事件数量")
    private Integer completedCount;

    @Schema(description = "状态为“已驳回”的事件数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "11294")
    @ExcelProperty("状态为“已驳回”的事件数量")
    private Integer rejectedCount;

    @Schema(description = "事件等级为“一级”的数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "30355")
    @ExcelProperty("事件等级为“一级”的数量")
    private Integer level1Count;

}