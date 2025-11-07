package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetattrrulecfg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 资产属性规则配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AssetAttrRuleCfgRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11052")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "属性规则ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24587")
    @ExcelProperty("属性规则ID")
    private String assetAttrRuleId;

    @Schema(description = "关联资产分类ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27262")
    @ExcelProperty("关联资产分类ID")
    private String relAssetCatId;

    @Schema(description = "关联资产分类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("关联资产分类名称")
    private String relAssetCatName;

    @Schema(description = "属性名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("属性名称")
    private String assetAttrName;

    @Schema(description = "属性代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("属性代码")
    private String assetAttrCode;

    @Schema(description = "属性数据类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("属性数据类型")
    private String attrDataType;

    @Schema(description = "属性长度")
    @ExcelProperty("属性长度")
    private String attrLength;

    @Schema(description = "属性值域")
    @ExcelProperty("属性值域")
    private String attrValueRange;

    @Schema(description = "是否必选", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否必选")
    private String isRequired;

    @Schema(description = "属性说明")
    @ExcelProperty("属性说明")
    private String attrDesc;

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