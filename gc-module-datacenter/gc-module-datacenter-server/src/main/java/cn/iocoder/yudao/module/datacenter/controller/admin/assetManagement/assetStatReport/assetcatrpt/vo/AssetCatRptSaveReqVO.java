package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetStatReport.assetcatrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 资产分类统计新增/修改 Request VO")
@Data
public class AssetCatRptSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11660")
    private Long id;

    @Schema(description = "统计ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10923")
//    @NotEmpty(message = "统计ID不能为空")
    private String assetCatStatId;

    @Schema(description = "统计周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期不能为空")
    private String statCycle;

    @Schema(description = "统计周期名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "统计周期名称不能为空")
    private String statCycleName;

    @Schema(description = "行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划代码不能为空")
    private String regionCode;

    @Schema(description = "行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "行政区划名称不能为空")
    private String regionName;

    @Schema(description = "资产大类ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16206")
    @NotEmpty(message = "资产大类ID不能为空")
    private String assetMajorId;

    @Schema(description = "资产大类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "资产大类名称不能为空")
    private String assetMajorName;

    @Schema(description = "资产中类ID", example = "30060")
    private String assetMidId;

    @Schema(description = "资产中类名称", example = "李四")
    private String assetMidName;

    @Schema(description = "资产小类ID", example = "6870")
    private String assetMinorId;

    @Schema(description = "资产小类名称", example = "赵六")
    private String assetMinorName;

    @Schema(description = "资产总数", requiredMode = Schema.RequiredMode.REQUIRED, example = "27027")
    @NotNull(message = "资产总数不能为空")
    private Integer totalAssetCount;

    @Schema(description = "启用资产数", requiredMode = Schema.RequiredMode.REQUIRED, example = "16972")
    @NotNull(message = "启用资产数不能为空")
    private Integer enabledAssetCount;

    @Schema(description = "禁用资产数", requiredMode = Schema.RequiredMode.REQUIRED, example = "24470")
    @NotNull(message = "禁用资产数不能为空")
    private Integer disabledAssetCount;

    @Schema(description = "新增资产数", example = "12295")
    private Integer newAssetCount;

    @Schema(description = "更新资产数", example = "1347")
    private Integer updateAssetCount;

    @Schema(description = "统计时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "统计时间不能为空")
    private LocalDateTime statTime;

    @Schema(description = "统计人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计人不能为空")
    private String statUser;

    @Schema(description = "报表备注", example = "你说的对")
    private String rptRemark;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}