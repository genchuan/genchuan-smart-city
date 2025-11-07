package cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.gridspatialdata.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 网格空间数据 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GridSpatialDataRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "空间数据ID")
    @ExcelProperty("空间数据ID")
    private String spatialId;

    @Schema(description = "关联网格ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联网格ID")
    private String gridId;

    @Schema(description = "网格类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("网格类型")
    private String gridType;

    @Schema(description = "边界坐标", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("边界坐标")
    private String boundaryCoords;

    @Schema(description = "拓扑关系")
    @ExcelProperty("拓扑关系")
    private String topoRelation;

    @Schema(description = "坐标系", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("坐标系")
    private String coordSystem;

    @Schema(description = "数据格式", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("数据格式")
    private String dataFormat;

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

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;
}