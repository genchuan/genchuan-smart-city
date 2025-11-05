package cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridtopiclayer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 网格专题图层管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GridTopicLayerRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "图层ID")
    @ExcelProperty("图层ID")
    private String layerId;

    @Schema(description = "图层名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("图层名称")
    private String layerName;

    @Schema(description = "网格类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("网格类型")
    private String gridType;

    @Schema(description = "比例尺", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("比例尺")
    private String scale;

    @Schema(description = "边界线样式ID")
    @ExcelProperty("边界线样式ID")
    private String boundaryStyleId;

    @Schema(description = "注记样式ID")
    @ExcelProperty("注记样式ID")
    private String annotateStyleId;

    @Schema(description = "显示状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("显示状态")
    private String displayStatus;

    @Schema(description = "图层顺序", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("图层顺序")
    private Integer layerWo;

    @Schema(description = "创建人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人")
    private String createUserId;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}