package cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.gridspatialdata.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 网格空间数据新增/修改 Request VO")
@Data
public class GridSpatialDataSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "空间数据ID")
    private String spatialId;

    @Schema(description = "关联网格ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联网格ID不能为空")
    private String gridId;

    @Schema(description = "网格类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "网格类型不能为空")
    private String gridType;

    @Schema(description = "边界坐标", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "边界坐标不能为空")
    private String boundaryCoords;

    @Schema(description = "拓扑关系")
    private String topoRelation;

    @Schema(description = "坐标系", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "坐标系不能为空")
    private String coordSystem;

    @Schema(description = "数据格式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "数据格式不能为空")
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

}