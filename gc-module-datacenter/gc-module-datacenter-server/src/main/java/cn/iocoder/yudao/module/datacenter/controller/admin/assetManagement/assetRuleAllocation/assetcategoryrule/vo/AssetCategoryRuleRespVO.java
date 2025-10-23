package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetcategoryrule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 资产分类规则配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AssetCategoryRuleRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8714")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "父级编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "26645")
    @ExcelProperty("父级编号")
    private Long parentId;

    @Schema(description = "名字", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("名字")
    private String name;

    @Schema(description = "分类规则ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17326")
    @ExcelProperty("分类规则ID")
    private String categoryRuleId;

    @Schema(description = "父类规则ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16844")
    @ExcelProperty("父类规则ID")
    private String parentCategoryRuleId;

    @Schema(description = "分类层级", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分类层级")
    private String categoryLevel;

    @Schema(description = "分类代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分类代码")
    private String categoryCode;

    @Schema(description = "分类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("分类名称")
    private String categoryName;

    @Schema(description = "分类说明")
    @ExcelProperty("分类说明")
    private String categoryDesc;

    @Schema(description = "启用状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("启用状态")
    private String enableStatus;

    @Schema(description = "创建人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人")
    private String createdUser;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createdTime;

    @Schema(description = "更新人")
    @ExcelProperty("更新人")
    private String updatedUser;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updatedTime;

    @Schema(description = "扩展字段1")
    @ExcelProperty("扩展字段1")
    private String extCategory1;

    @Schema(description = "扩展字段2")
    @ExcelProperty("扩展字段2")
    private String extCategory2;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}