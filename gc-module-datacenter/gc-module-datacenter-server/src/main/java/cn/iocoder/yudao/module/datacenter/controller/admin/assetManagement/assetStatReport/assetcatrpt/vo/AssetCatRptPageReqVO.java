package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetStatReport.assetcatrpt.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 资产分类统计分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AssetCatRptPageReqVO extends PageParam {

    @Schema(description = "统计ID", example = "10923")
    private String assetCatStatId;

    @Schema(description = "统计周期")
    private String statCycle;

    @Schema(description = "统计周期名称", example = "赵六")
    private String statCycleName;

    @Schema(description = "行政区划代码")
    private String regionCode;

    @Schema(description = "行政区划名称", example = "芋艿")
    private String regionName;

    @Schema(description = "资产大类ID", example = "16206")
    private String assetMajorId;

    @Schema(description = "资产大类名称", example = "李四")
    private String assetMajorName;

    @Schema(description = "资产中类ID", example = "30060")
    private String assetMidId;

    @Schema(description = "资产中类名称", example = "李四")
    private String assetMidName;

    @Schema(description = "资产小类ID", example = "6870")
    private String assetMinorId;

    @Schema(description = "资产小类名称", example = "赵六")
    private String assetMinorName;

    @Schema(description = "资产总数", example = "27027")
    private Integer totalAssetCount;

    @Schema(description = "启用资产数", example = "16972")
    private Integer enabledAssetCount;

    @Schema(description = "禁用资产数", example = "24470")
    private Integer disabledAssetCount;

    @Schema(description = "新增资产数", example = "12295")
    private Integer newAssetCount;

    @Schema(description = "更新资产数", example = "1347")
    private Integer updateAssetCount;

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