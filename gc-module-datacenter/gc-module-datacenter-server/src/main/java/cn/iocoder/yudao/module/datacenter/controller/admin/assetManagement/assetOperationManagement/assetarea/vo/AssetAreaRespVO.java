package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetarea.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 资产关联行政区划 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AssetAreaRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12714")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "关联ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27104")
    @ExcelProperty("关联ID")
    private String assetRelRegionId;

    @Schema(description = "关联资产ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30397")
    @ExcelProperty("关联资产ID")
    private String relAssetId;

    @Schema(description = "关联资产名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("关联资产名称")
    private String relAssetName;

    @Schema(description = "行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划代码")
    private String regionCode;

    @Schema(description = "行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("行政区划名称")
    private String regionName;

    @Schema(description = "行政区划级别", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划级别")
    private String regionLevel;

    @Schema(description = "关联时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联时间")
    private LocalDateTime relTime;

    @Schema(description = "操作人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("操作人")
    private String operUser;

    @Schema(description = "关联说明")
    @ExcelProperty("关联说明")
    private String relDesc;

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