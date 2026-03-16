package cn.iocoder.yudao.module.envirhealth.controller.admin.dept.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 部门 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DeptRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26627")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "12261")
    @ExcelProperty("业务主键（UUID）")
    private String sysDeptId;

    @Schema(description = "部门名称", example = "张三")
    @ExcelProperty("部门名称")
    private String name;

    @Schema(description = "父部门ID（关联自身sys_dept_id，顶级部门填0）", example = "5865")
    @ExcelProperty("父部门ID（关联自身sys_dept_id，顶级部门填0）")
    private String parentId;

    @Schema(description = "部门编码")
    @ExcelProperty("部门编码")
    private String deptCode;

    @Schema(description = "状态（可选值：0-禁用/1-启用）", example = "1")
    @ExcelProperty("状态（可选值：0-禁用/1-启用）")
    private Integer status;

    @Schema(description = "排序号")
    @ExcelProperty("排序号")
    private Integer sort;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    @ExcelProperty("通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    @ExcelProperty("通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}