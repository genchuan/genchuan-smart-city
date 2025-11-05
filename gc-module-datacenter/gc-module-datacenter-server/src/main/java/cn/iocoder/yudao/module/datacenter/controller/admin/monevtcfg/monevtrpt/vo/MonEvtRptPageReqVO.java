package cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtrpt.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 监测事件统计报分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MonEvtRptPageReqVO extends PageParam {

    @Schema(description = "唯一编码，采用UUID生成", example = "14355")
    private String statId;

    @Schema(description = "统计周期类型，格式:“YYYY”“YYYYQn”“YYYYMM”")
    private String statCycle;

    @Schema(description = "如“2025年09月”", example = "赵六")
    private String statCycleName;

    @Schema(description = "符合GB/T 2260，统计区域的行政区划代码")
    private String regionCode;

    @Schema(description = "与行政区划代码关联，自动同步名称", example = "李四")
    private String regionName;

    @Schema(description = "关联监测事件分类配置表的大类ID", example = "7195")
    private String evtMajorId;

    @Schema(description = "与事件大类ID关联，自动同步名称", example = "赵六")
    private String evtMajorName;

    @Schema(description = "关联监测事件分类配置表的小类ID（钻取时必填）", example = "11552")
    private String evtMinorId;

    @Schema(description = "与事件小类ID关联，自动同步名称", example = "王五")
    private String evtMinorName;

    @Schema(description = "事件处置部门统一社会信用代码")
    private String deptCode;

    @Schema(description = "与处置部门代码关联，自动同步名称", example = "芋艿")
    private String deptName;

    @Schema(description = "统计周期内该维度下事件上报总数", example = "26160")
    private Integer totalRptCount;

    @Schema(description = "状态为“待处置”的事件数量", example = "28863")
    private Integer pendCount;

    @Schema(description = "状态为“处置中”的事件数量", example = "23444")
    private Integer handlCount;

    @Schema(description = "状态为“已办结”的事件数量", example = "28952")
    private Integer completedCount;

    @Schema(description = "状态为“已驳回”的事件数量", example = "11294")
    private Integer rejectedCount;

    @Schema(description = "事件等级为“一级”的数量", example = "30355")
    private Integer level1Count;

}