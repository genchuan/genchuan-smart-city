package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetarea.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 资产关联行政区划分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AssetAreaPageReqVO extends PageParam {

    @Schema(description = "关联ID", example = "27104")
    private String assetRelRegionId;

    @Schema(description = "关联资产ID", example = "30397")
    private String relAssetId;

    @Schema(description = "关联资产名称", example = "王五")
    private String relAssetName;

    @Schema(description = "行政区划代码")
    private String regionCode;

    @Schema(description = "行政区划名称", example = "赵六")
    private String regionName;

    @Schema(description = "行政区划级别")
    private String regionLevel;

    @Schema(description = "关联时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] relTime;

    @Schema(description = "操作人")
    private String operUser;

    @Schema(description = "关联说明")
    private String relDesc;

    @Schema(description = "更新人")
    private String updateUser;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updatedTime;

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