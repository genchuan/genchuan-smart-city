package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnasset.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 按资产分域预警告警统计新增/修改 Request VO")
@Data
public class EarlyWarnAssetSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "统计ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计ID不能为空")
    private String warnAssetStatId;

    @Schema(description = "统计周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期不能为空")
    private String statCycle;

    @Schema(description = "资产大类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "资产大类ID不能为空")
    private String assetMajorId;

    @Schema(description = "资产大类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "资产大类名称不能为空")
    private String assetMajorName;

    @Schema(description = "资产小类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "资产小类ID不能为空")
    private String assetMinorId;

    @Schema(description = "资产小类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "资产小类名称不能为空")
    private String assetMinorName;

    @Schema(description = "预警总数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "预警总数不能为空")
    private Integer totalWarnCount;

    @Schema(description = "主要故障类型")
    private String mainFaultType;

    @Schema(description = "涉及资产数")
    private Integer assetCount;

    @Schema(description = "维修总成本")
    private BigDecimal mntCost;

    @Schema(description = "已修复资产数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "已修复资产数不能为空")
    private Integer repairedAssetCount;

    @Schema(description = "修复率", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "修复率不能为空")
    private BigDecimal repairRate;

    @Schema(description = "统计人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计人不能为空")
    private String statUserId;

    @Schema(description = "统计时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "统计时间不能为空")
    private LocalDateTime statTime;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}