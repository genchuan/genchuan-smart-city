package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetspatialdata.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 资产空间数据 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AssetSpatialDataRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3246")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "空间数据ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16354")
    @ExcelProperty("空间数据ID")
    private String assetSpatialId;

    @Schema(description = "关联资产ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5840")
    @ExcelProperty("关联资产ID")
    private String relAssetId;

    @Schema(description = "关联资产名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("关联资产名称")
    private String relAssetName;

    @Schema(description = "坐标系类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("坐标系类型")
    private String coordSystemType;

    @Schema(description = "坐标X", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("坐标X")
    private BigDecimal coordX;

    @Schema(description = "坐标Y", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("坐标Y")
    private BigDecimal coordY;

    @Schema(description = "高程")
    @ExcelProperty("高程")
    private BigDecimal elevation;

    @Schema(description = "边界坐标")
    @ExcelProperty("边界坐标")
    private String boundaryCoords;

    @Schema(description = "空间数据来源", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("空间数据来源")
    private String spatialDataSource;

    @Schema(description = "录入时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("录入时间")
    private LocalDateTime inputTime;

    @Schema(description = "操作人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("操作人")
    private String operUser;

    @Schema(description = "更新人")
    @ExcelProperty("更新人")
    private String updateUser;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updatedTime;

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

    @Schema(description = "系统创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTime;

}