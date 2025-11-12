package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domassetmonrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 资产分域监测报表新增/修改 Request VO")
@Data
public class DomAssetMonRptSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "报表ID")
    private String rptId;

    @Schema(description = "统计周期")
    private String statCycle;

    @Schema(description = "统计周期名称")
    private String statCycleName;

    @Schema(description = "行政区划代码")
    private String adminCode;

    @Schema(description = "行政区划名称")
    private String adminName;

    @Schema(description = "资产大类ID")
    private String assetMajorId;

    @Schema(description = "资产大类名称")
    private String assetMajorName;

    @Schema(description = "资产小类ID")
    private String assetMinorId;

    @Schema(description = "资产小类名称")
    private String assetMinorName;

    @Schema(description = "分域ID")
    private String domId;

    @Schema(description = "分域名称")
    private String domName;

    @Schema(description = "资产总数")
    private Integer totalAssetCount;

    @Schema(description = "在用资产数")
    private Integer inUseAssetCount;

    @Schema(description = "资产在用率")
    private BigDecimal assetInUseRate;

    @Schema(description = "维护总次数")
    private Integer totalMntCount;

    @Schema(description = "平均资产寿命")
    private BigDecimal avgAssetLife;

    @Schema(description = "资产故障事件数")
    private Integer assetFaultEvtCount;

    @Schema(description = "报表生成时间")
    private LocalDateTime rptCreateTime;

    @Schema(description = "报表生成人")
    private Long rptCreateUser;

    @Schema(description = "报表备注")
    private String rptRemark;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}