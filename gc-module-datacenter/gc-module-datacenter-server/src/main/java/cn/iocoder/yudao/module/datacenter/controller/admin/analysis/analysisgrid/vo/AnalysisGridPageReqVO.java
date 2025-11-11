package cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisgrid.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 按网格分域分析研判统计分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AnalysisGridPageReqVO extends PageParam {

    @Schema(description = "统计ID，唯一编码，UUID生成", example = "20347")
    private String statAnalysisId;

    @Schema(description = "统计周期，格式：年/季/月，如YYYY/YYYYOn/YYYYMM")
    private String statCycle;

    @Schema(description = "统计周期名称，如“2025年09月”", example = "张三")
    private String statCycleName;

    @Schema(description = "网格ID，关联单元网格划分表", example = "4641")
    private String gridId;

    @Schema(description = "网格名称，与网格ID同步，关联单元网格划分表", example = "李四")
    private String gridName;

    @Schema(description = "网格类型，标识网格类型，如单元网格/管理网格", example = "2")
    private String gridType;

    @Schema(description = "所在街道代码，关联行政区划表街道级代码")
    private String streetCode;

    @Schema(description = "所在街道名称，与街道代码同步，关联行政区划表", example = "赵六")
    private String streetName;

    @Schema(description = "监测部件在线率，周期内网格内在线监测部件占比，0.00-100.00")
    private BigDecimal monCompOnlineRate;

    @Schema(description = "事件处置及时率，周期内网格内及时处置事件占比，0.00-100.00")
    private BigDecimal evtTimelyHandleRate;

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