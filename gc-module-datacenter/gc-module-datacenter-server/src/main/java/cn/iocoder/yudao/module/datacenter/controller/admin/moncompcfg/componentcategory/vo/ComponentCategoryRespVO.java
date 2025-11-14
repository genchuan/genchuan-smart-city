package cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.componentcategory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 监测部件分类 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ComponentCategoryRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20709")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "分类ID，UUID", example = "7280")
    @ExcelProperty("分类ID，UUID")
    private String categoryId;

    @Schema(description = "父类ID，0表示大类", example = "22250")
    @ExcelProperty("父类ID，0表示大类")
    private String parentId;

    @Schema(description = "层级：1-大类/2-中类/3-小类")
    @ExcelProperty("层级：1-大类/2-中类/3-小类")
    private String level;

    @Schema(description = "分类代码：01-99")
    @ExcelProperty("分类代码：01-99")
    private String code;

    @Schema(description = "分类名称", example = "赵六")
    @ExcelProperty("分类名称")
    private String name;

    @Schema(description = "分类说明", example = "随便")
    @ExcelProperty("分类说明")
    private String description;

    @Schema(description = "状态：1-启用/0-禁用", example = "2")
    @ExcelProperty("状态：1-启用/0-禁用")
    private String status;

    @Schema(description = "创建人ID")
    @ExcelProperty("创建人ID")
    private String createUser;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人ID")
    @ExcelProperty("更新人ID")
    private String updateUser;

    @Schema(description = "扩展字段1")
    @ExcelProperty("扩展字段1")
    private String ext1;

    @Schema(description = "扩展字段2")
    @ExcelProperty("扩展字段2")
    private String ext2;

    @Schema(description = "扩展字段3")
    @ExcelProperty("扩展字段3")
    private String ext3;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}