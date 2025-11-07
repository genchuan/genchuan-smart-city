package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetspatialdata.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 资产空间数据分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AssetSpatialDataPageReqVO extends PageParam {

    @Schema(description = "空间数据ID", example = "16354")
    private String assetSpatialId;

    @Schema(description = "关联资产ID", example = "5840")
    private String relAssetId;

    @Schema(description = "关联资产名称", example = "芋艿")
    private String relAssetName;

    @Schema(description = "坐标系类型", example = "2")
    private String coordSystemType;

    @Schema(description = "坐标X")
    private BigDecimal coordX;

    @Schema(description = "坐标Y")
    private BigDecimal coordY;

    @Schema(description = "高程")
    private BigDecimal elevation;

    @Schema(description = "边界坐标")
    private String boundaryCoords;

    @Schema(description = "空间数据来源")
    private String spatialDataSource;

    @Schema(description = "录入时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] inputTime;

    @Schema(description = "操作人")
    private String operUser;

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