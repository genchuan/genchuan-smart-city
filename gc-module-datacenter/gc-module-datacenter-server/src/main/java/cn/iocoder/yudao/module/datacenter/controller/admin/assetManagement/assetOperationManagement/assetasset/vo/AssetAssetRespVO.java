package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetasset.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 资产关联资产 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AssetAssetRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28875")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "关联ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2409")
    @ExcelProperty("关联ID")
    private String assetRelAssetId;

    @Schema(description = "从资产ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25474")
    @ExcelProperty("从资产ID")
    private String slaveAssetId;

    @Schema(description = "从资产名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("从资产名称")
    private String slaveAssetName;

    @Schema(description = "主资产ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26859")
    @ExcelProperty("主资产ID")
    private String masterAssetId;

    @Schema(description = "主资产名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("主资产名称")
    private String masterAssetName;

    @Schema(description = "关联类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("关联类型")
    private String relType;

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

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}