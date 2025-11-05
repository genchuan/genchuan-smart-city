package cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 监测事件统计报新增/修改 Request VO")
@Data
public class MonEvtRptSaveReqVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22321")
    private Long id;

    @Schema(description = "唯一编码，采用UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "14355")
    @NotEmpty(message = "唯一编码，采用UUID生成不能为空")
    private String statId;

    @Schema(description = "统计周期类型，格式:“YYYY”“YYYYQn”“YYYYMM”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期类型，格式:“YYYY”“YYYYQn”“YYYYMM”不能为空")
    private String statCycle;

    @Schema(description = "如“2025年09月”", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "如“2025年09月”不能为空")
    private String statCycleName;

    @Schema(description = "符合GB/T 2260，统计区域的行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "符合GB/T 2260，统计区域的行政区划代码不能为空")
    private String regionCode;

    @Schema(description = "与行政区划代码关联，自动同步名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "与行政区划代码关联，自动同步名称不能为空")
    private String regionName;

    @Schema(description = "关联监测事件分类配置表的大类ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "7195")
    @NotEmpty(message = "关联监测事件分类配置表的大类ID不能为空")
    private String evtMajorId;

    @Schema(description = "与事件大类ID关联，自动同步名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "与事件大类ID关联，自动同步名称不能为空")
    private String evtMajorName;

    @Schema(description = "关联监测事件分类配置表的小类ID（钻取时必填）", example = "11552")
    private String evtMinorId;

    @Schema(description = "与事件小类ID关联，自动同步名称", example = "王五")
    private String evtMinorName;

    @Schema(description = "事件处置部门统一社会信用代码")
    private String deptCode;

    @Schema(description = "与处置部门代码关联，自动同步名称", example = "芋艿")
    private String deptName;

    @Schema(description = "统计周期内该维度下事件上报总数", requiredMode = Schema.RequiredMode.REQUIRED, example = "26160")
    @NotNull(message = "统计周期内该维度下事件上报总数不能为空")
    private Integer totalRptCount;

    @Schema(description = "状态为“待处置”的事件数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "28863")
    @NotNull(message = "状态为“待处置”的事件数量不能为空")
    private Integer pendCount;

    @Schema(description = "状态为“处置中”的事件数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "23444")
    @NotNull(message = "状态为“处置中”的事件数量不能为空")
    private Integer handlCount;

    @Schema(description = "状态为“已办结”的事件数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "28952")
    @NotNull(message = "状态为“已办结”的事件数量不能为空")
    private Integer completedCount;

    @Schema(description = "状态为“已驳回”的事件数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "11294")
    @NotNull(message = "状态为“已驳回”的事件数量不能为空")
    private Integer rejectedCount;

    @Schema(description = "事件等级为“一级”的数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "30355")
    @NotNull(message = "事件等级为“一级”的数量不能为空")
    private Integer level1Count;

}