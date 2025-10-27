package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetStatReport.assetcatrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 资产分类统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AssetCatRptRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11660")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "统计ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10923")
    @ExcelProperty("统计ID")
    private String assetCatStatId;

    @Schema(description = "统计周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计周期")
    private String statCycle;

    @Schema(description = "统计周期名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("统计周期名称")
    private String statCycleName;

    @Schema(description = "行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划代码")
    private String regionCode;

    @Schema(description = "行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("行政区划名称")
    private String regionName;

    @Schema(description = "资产大类ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16206")
    @ExcelProperty("资产大类ID")
    private String assetMajorId;

    @Schema(description = "资产大类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("资产大类名称")
    private String assetMajorName;

    @Schema(description = "资产中类ID", example = "30060")
    @ExcelProperty("资产中类ID")
    private String assetMidId;

    @Schema(description = "资产中类名称", example = "李四")
    @ExcelProperty("资产中类名称")
    private String assetMidName;

    @Schema(description = "资产小类ID", example = "6870")
    @ExcelProperty("资产小类ID")
    private String assetMinorId;

    @Schema(description = "资产小类名称", example = "赵六")
    @ExcelProperty("资产小类名称")
    private String assetMinorName;

    @Schema(description = "资产总数", requiredMode = Schema.RequiredMode.REQUIRED, example = "27027")
    @ExcelProperty("资产总数")
    private Integer totalAssetCount;

    @Schema(description = "启用资产数", requiredMode = Schema.RequiredMode.REQUIRED, example = "16972")
    @ExcelProperty("启用资产数")
    private Integer enabledAssetCount;

    @Schema(description = "禁用资产数", requiredMode = Schema.RequiredMode.REQUIRED, example = "24470")
    @ExcelProperty("禁用资产数")
    private Integer disabledAssetCount;

    @Schema(description = "新增资产数", example = "12295")
    @ExcelProperty("新增资产数")
    private Integer newAssetCount;

    @Schema(description = "更新资产数", example = "1347")
    @ExcelProperty("更新资产数")
    private Integer updateAssetCount;

    @Schema(description = "统计时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计时间")
    private LocalDateTime statTime;

    @Schema(description = "统计人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计人")
    private String statUser;

    @Schema(description = "报表备注", example = "你说的对")
    @ExcelProperty("报表备注")
    private String rptRemark;

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