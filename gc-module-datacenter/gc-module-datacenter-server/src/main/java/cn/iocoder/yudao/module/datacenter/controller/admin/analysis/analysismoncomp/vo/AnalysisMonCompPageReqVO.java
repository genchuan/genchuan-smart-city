package cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismoncomp.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;


@Schema(description = "管理后台 - 按监测部件分析研判统计分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AnalysisMonCompPageReqVO extends PageParam {

    @Schema(description = "统计ID，唯一编码，UUID生成", example = "29586")
    private String statAnalysisId;

    @Schema(description = "统计周期，格式：年/季/月，如YYYY/YYYYQn/YYYYMM")
    private String statCycle;

    @Schema(description = "统计周期名称，如“2025年Q3”", example = "芋艿")
    private String statCycleName;

    @Schema(description = "部件大类ID，关联监测部件分类表", example = "13432")
    private String compMajorId;

    @Schema(description = "部件大类名称，与大类ID同步，关联监测部件分类表", example = "张三")
    private String compMajorName;

    @Schema(description = "部件小类ID，关联监测部件分类表", example = "15434")
    private String compMinorId;

    @Schema(description = "部件小类名称，与小类ID同步，关联监测部件分类表", example = "赵六")
    private String compMinorName;

    @Schema(description = "部件总数量，周期内该分类监测部件总数", example = "17670")
    private Integer totalCompCount;

    @Schema(description = "在线部件数，周期内该分类在线监测部件数", example = "32148")
    private Integer onlineCompCount;

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