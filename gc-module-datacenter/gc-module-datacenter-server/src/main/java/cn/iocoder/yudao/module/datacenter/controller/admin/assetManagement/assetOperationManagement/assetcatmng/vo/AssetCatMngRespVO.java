package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetcatmng.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 资产分类管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AssetCatMngRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2024")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "资产分类ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30359")
    @ExcelProperty("资产分类ID")
    private String assetCatId;

    @Schema(description = "关联分类规则ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30418")
    @ExcelProperty("关联分类规则ID")
    private String relCatRuleId;

    @Schema(description = "资产分类编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("资产分类编码")
    private String assetCatCode;

    @Schema(description = "资产分类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("资产分类名称")
    private String assetCatName;

    @Schema(description = "分类层级", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分类层级")
    private String catLevel;

    @Schema(description = "上级分类ID", example = "25267")
    @ExcelProperty("上级分类ID")
    private String parentCatId;

    @Schema(description = "上级分类名称", example = "王五")
    @ExcelProperty("上级分类名称")
    private String parentCatName;

    @Schema(description = "分类说明")
    @ExcelProperty("分类说明")
    private String catDesc;

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