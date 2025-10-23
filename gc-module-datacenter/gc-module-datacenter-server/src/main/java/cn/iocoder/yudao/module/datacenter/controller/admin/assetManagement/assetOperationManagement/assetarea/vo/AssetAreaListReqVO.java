package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetarea.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 资产关联行政区划列表 Request VO")
@Data
public class AssetAreaListReqVO {

    @Schema(description = "父级编号", example = "2893")
    private Long parentId;

    @Schema(description = "名字", example = "芋艿")
    private String name;

    @Schema(description = "关联ID", example = "1754")
    private String assetRelRegionId;

    @Schema(description = "关联资产ID", example = "11850")
    private String relAssetId;

    @Schema(description = "关联资产名称", example = "张三")
    private String relAssetName;

    @Schema(description = "行政区划代码")
    private String regionCode;

    @Schema(description = "行政区划名称", example = "李四")
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

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}