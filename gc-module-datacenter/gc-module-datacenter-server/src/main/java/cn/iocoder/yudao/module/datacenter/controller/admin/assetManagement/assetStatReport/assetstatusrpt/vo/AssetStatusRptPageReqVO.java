package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetStatReport.assetstatusrpt.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 资产状态统计分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AssetStatusRptPageReqVO extends PageParam {

    @Schema(description = "统计ID", example = "28661")
    private String assetStatusStatId;

    @Schema(description = "统计周期")
    private String statCycle;

    @Schema(description = "统计周期名称", example = "赵六")
    private String statCycleName;

    @Schema(description = "行政区划代码")
    private String regionCode;

    @Schema(description = "行政区划名称", example = "赵六")
    private String regionName;

    @Schema(description = "资产类型", example = "1")
    private String assetType;

    @Schema(description = "资产状态", example = "2")
    private String assetStatus;

    @Schema(description = "资产数量", example = "13141")
    private Integer assetCount;

    @Schema(description = "占比")
    private BigDecimal assetRatio;

    @Schema(description = "故障资产主要原因", example = "不对")
    private String faultReason;

    @Schema(description = "维护优先级")
    private String mntPriority;

    @Schema(description = "统计时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] statTime;

    @Schema(description = "统计人")
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

    @Schema(description = "系统创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "排序的字段，如：createdTime")
    private String orderByColumn;
    @Schema(description = "排序方式，asc升序、desc降序")
    private String isAsc;

}