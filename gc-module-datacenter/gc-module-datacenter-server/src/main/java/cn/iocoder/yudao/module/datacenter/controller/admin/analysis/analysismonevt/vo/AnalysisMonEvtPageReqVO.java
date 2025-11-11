package cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismonevt.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 按监测事件分析研判统计分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AnalysisMonEvtPageReqVO extends PageParam {

    @Schema(description = "统计ID，唯一编码，UUID生成", example = "19985")
    private String statAnalysisId;

    @Schema(description = "统计周期，格式：年/季/月，如YYYY/YYYYQn/YYYYMM")
    private String statCycle;

    @Schema(description = "统计周期名称，如“2025年Q3”", example = "张三")
    private String statCycleName;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

    @Schema(description = "分类扩展字段1，预留")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留")
    private String extCommon2;

}