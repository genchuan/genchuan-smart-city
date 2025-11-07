package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetrelrulecfg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 资产关联规则配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AssetRelRuleCfgRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5490")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "关联规则ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "710")
    @ExcelProperty("关联规则ID")
    private String assetRelRuleId;

    @Schema(description = "关联资产分类ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8418")
    @ExcelProperty("关联资产分类ID")
    private String relAssetCatId;

    @Schema(description = "关联资产分类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("关联资产分类名称")
    private String relAssetCatName;

    @Schema(description = "关联对象类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("关联对象类型")
    private String relObjectType;

    @Schema(description = "关联对象名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("关联对象名称")
    private String relObjectName;

    @Schema(description = "是否必选", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否必选")
    private String isRequired;

    @Schema(description = "关联数量限制", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联数量限制")
    private Integer relQuantityLimit;

    @Schema(description = "关联对象来源表", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联对象来源表")
    private String relObjectSourceTable;

    @Schema(description = "关联说明")
    @ExcelProperty("关联说明")
    private String relDesc;

    @Schema(description = "创建人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人")
    private String createUser;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createdTime;

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