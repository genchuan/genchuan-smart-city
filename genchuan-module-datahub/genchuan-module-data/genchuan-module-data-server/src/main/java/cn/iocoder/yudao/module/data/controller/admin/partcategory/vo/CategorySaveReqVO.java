package cn.iocoder.yudao.module.data.controller.admin.partcategory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 管理部件分类新增/修改 Request VO")
@Data
public class CategorySaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "7377")
    private Long id;

    @Schema(description = "分类名称", example = "王五")
    private String categoryName;

    @Schema(description = "分类代码")
    private String categoryCode;

    @Schema(description = "编码排序类型", example = "1")
    private String codeSortType;

    @Schema(description = "上级分类ID", example = "14362")
    private String parentId;

    @Schema(description = "上级分类名称", example = "张三")
    private String parentCategoryName;

    @Schema(description = "关联图标", example = "26428")
    private String iconName;

    @Schema(description = "图示审核状态", example = "655")
    private String auditStatus;

    @Schema(description = "分类类型", example = "27463")
    private String categoryType;

    @Schema(description = "状态", example = "0,1,2")
    private String status;

    @Schema(description = "关联审核状态ID", example = "26882")
    private String auditStatusId;

    @Schema(description = "关联实例数", example = "9526")
    private Integer instanceCount;

    @Schema(description = "用途说明")
    private String purpose;

    @Schema(description = "分类变更通知标识")
    private Boolean notifyFlag;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建人")
    private String creator;

}