package cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisdevice.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 按设备分域分析研判统计分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AnalysisDevicePageReqVO extends PageParam {

    @Schema(description = "统计ID，唯一编码，UUID生成", example = "14296")
    private String statAnalysisId;

    @Schema(description = "统计周期，格式：年/季/月，如YYYY/YYYYQn/YYYYMM")
    private String statCycle;

    @Schema(description = "统计周期名称，如“2025年09月”", example = "张三")
    private String statCycleName;

    @Schema(description = "设备大类ID，关联设备分类表", example = "15345")
    private String deviceMajorId;

    @Schema(description = "设备大类名称，与大类ID同步，关联设备分类表", example = "张三")
    private String deviceMajorName;

    @Schema(description = "设备小类ID，关联设备分类表", example = "4362")
    private String deviceMinorId;

    @Schema(description = "设备小类名称，与小类ID同步，关联设备分类表", example = "王五")
    private String deviceMinorName;

    @Schema(description = "设备总数量，周期内该分类设备总数量", example = "2887")
    private Integer totalDeviceCount;

    @Schema(description = "设备在线率，在线设备数/总数量×100，0.00-100.00")
    private BigDecimal deviceOnlineRate;

    @Schema(description = "数据上传频率，周期内该分类设备平均每小时上传数据次数，次/小时")
    private BigDecimal dataUploadFreq;

    @Schema(description = "设备故障率，故障设备数/总数量×100，0.00-100.00")
    private BigDecimal deviceFaultRate;

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