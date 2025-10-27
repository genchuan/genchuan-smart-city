package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetcatrulecfg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 资产分类规则配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AssetCatRuleCfgRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19245")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "分类规则ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "32430")
    @ExcelProperty("分类规则ID")
    private String assetCatRuleId;

    @Schema(description = "规则名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("规则名称")
    private String ruleName;

    @Schema(description = "大类编码位数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("大类编码位数")
    private Integer majorCodeLength;

    @Schema(description = "中类编码位数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("中类编码位数")
    private Integer midCodeLength;

    @Schema(description = "小类编码位数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("小类编码位数")
    private Integer minorCodeLength;

    @Schema(description = "编码生成逻辑", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("编码生成逻辑")
    private String codeGenLogic;

    @Schema(description = "适用资产领域", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("适用资产领域")
    private String assetDom;

    @Schema(description = "启用状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("启用状态")
    private String enableStatus;

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