package cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.gridspatialdata.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 网格空间数据分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GridSpatialDataPageReqVO extends PageParam {

    @Schema(description = "空间数据ID")
    private String spatialId;

    @Schema(description = "关联网格ID")
    private String gridId;

    @Schema(description = "网格类型")
    private String gridType;

    @Schema(description = "边界坐标")
    private String boundaryCoords;

    @Schema(description = "拓扑关系")
    private String topoRelation;

    @Schema(description = "坐标系")
    private String coordSystem;

    @Schema(description = "数据格式")
    private String dataFormat;

    @Schema(description = "备注")
    private String remark;

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

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;
}