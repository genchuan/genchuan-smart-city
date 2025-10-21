package cn.iocoder.yudao.module.datacenter.controller.admin.assetrelrule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 资产关联规则配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AssetRelRuleRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6991")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "父级编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "25172")
    @ExcelProperty("父级编号")
    private Long parentId;

    @Schema(description = "名字", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("名字")
    private String name;

    @Schema(description = "关联规则ID（UUID）", requiredMode = Schema.RequiredMode.REQUIRED, example = "19823")
    @ExcelProperty("关联规则ID（UUID）")
    private String relRuleId;

    @Schema(description = "资产分类ID（小类）", requiredMode = Schema.RequiredMode.REQUIRED, example = "18297")
    @ExcelProperty("资产分类ID（小类）")
    private String assetCategoryId;

    @Schema(description = "资产分类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("资产分类名称")
    private String assetCategoryName;

    @Schema(description = "关联对象类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("关联对象类型")
    private String relObjType;

    @Schema(description = "关联对象ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17845")
    @ExcelProperty("关联对象ID")
    private String relObjId;

    @Schema(description = "关联对象名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("关联对象名称")
    private String relObjName;

    @Schema(description = "关联必填标识：1必选/0可选", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @ExcelProperty("关联必填标识：1必选/0可选")
    private String isRequired;

    @Schema(description = "关联校验规则")
    @ExcelProperty("关联校验规则")
    private String relCheckRule;

    @Schema(description = "启用状态：1启用/0禁用", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("启用状态：1启用/0禁用")
    private String enableStatus;

    @Schema(description = "创建人（用户ID）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人（用户ID）")
    private String createUser;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createdTime;

    @Schema(description = "更新人（用户ID）")
    @ExcelProperty("更新人（用户ID）")
    private String updateUser;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updatedTime;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCategory1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCategory2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间（系统）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间（系统）")
    private LocalDateTime createTime;

}