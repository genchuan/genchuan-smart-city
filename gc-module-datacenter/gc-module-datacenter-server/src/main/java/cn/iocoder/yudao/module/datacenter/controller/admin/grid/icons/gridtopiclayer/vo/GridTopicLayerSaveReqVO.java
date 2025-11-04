package cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridtopiclayer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 网格专题图层管理新增/修改 Request VO")
@Data
public class GridTopicLayerSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "图层ID")
    private String layerId;

    @Schema(description = "图层名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "图层名称不能为空")
    private String layerName;

    @Schema(description = "网格类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "网格类型不能为空")
    private String gridType;

    @Schema(description = "比例尺", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "比例尺不能为空")
    private String scale;

    @Schema(description = "边界线样式ID")
    private String boundaryStyleId;

    @Schema(description = "注记样式ID")
    private String annotateStyleId;

    @Schema(description = "显示状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "显示状态不能为空")
    private String displayStatus;

    @Schema(description = "图层顺序", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "图层顺序不能为空")
    private Integer layerWo;

    @Schema(description = "创建人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人不能为空")
    private String createUserId;

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