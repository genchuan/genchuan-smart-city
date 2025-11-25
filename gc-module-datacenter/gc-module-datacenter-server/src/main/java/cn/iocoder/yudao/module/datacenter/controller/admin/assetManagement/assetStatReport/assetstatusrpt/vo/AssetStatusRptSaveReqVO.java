package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetStatReport.assetstatusrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 资产状态统计新增/修改 Request VO")
@Data
public class AssetStatusRptSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28352")
    private Long id;

    @Schema(description = "统计ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28661")
//    @NotEmpty(message = "统计ID不能为空")
    private String assetStatusStatId;

    @Schema(description = "统计周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期不能为空")
    private String statCycle;

    @Schema(description = "统计周期名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "统计周期名称不能为空")
    private String statCycleName;

    @Schema(description = "行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划代码不能为空")
    private String regionCode;

    @Schema(description = "行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "行政区划名称不能为空")
    private String regionName;

    @Schema(description = "资产类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "资产类型不能为空")
    private String assetType;

    @Schema(description = "资产状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "资产状态不能为空")
    private String assetStatus;

    @Schema(description = "资产数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "13141")
    @NotNull(message = "资产数量不能为空")
    private Integer assetCount;

    @Schema(description = "占比", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "占比不能为空")
    private BigDecimal assetRatio;

    @Schema(description = "故障资产主要原因", example = "不对")
    private String faultReason;

    @Schema(description = "维护优先级")
    private String mntPriority;

    @Schema(description = "统计时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "统计时间不能为空")
    private LocalDateTime statTime;

    @Schema(description = "统计人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计人不能为空")
    private String statUser;

    @Schema(description = "报表备注", example = "你说的对")
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