package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnasset.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 按资产分域预警告警统计分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EarlyWarnAssetPageReqVO extends PageParam {

    @Schema(description = "统计ID")
    private String warnAssetStatId;

    @Schema(description = "统计周期")
    private String statCycle;

    @Schema(description = "资产大类ID")
    private String assetMajorId;

    @Schema(description = "资产大类名称")
    private String assetMajorName;

    @Schema(description = "资产小类ID")
    private String assetMinorId;

    @Schema(description = "资产小类名称")
    private String assetMinorName;

    @Schema(description = "预警总数")
    private Integer totalWarnCount;

    @Schema(description = "主要故障类型")
    private String mainFaultType;

    @Schema(description = "涉及资产数")
    private Integer assetCount;

    @Schema(description = "维修总成本")
    private BigDecimal mntCost;

    @Schema(description = "已修复资产数")
    private Integer repairedAssetCount;

    @Schema(description = "修复率")
    private BigDecimal repairRate;

    @Schema(description = "统计人")
    private String statUserId;

    @Schema(description = "统计时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] statTime;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}