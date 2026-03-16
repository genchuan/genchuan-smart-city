package cn.iocoder.yudao.module.envirhealth.controller.admin.dept.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 部门新增/修改 Request VO")
@Data
public class DeptSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26627")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "12261")
    private String sysDeptId;

    @Schema(description = "部门名称", example = "张三")
    private String name;

    @Schema(description = "父部门ID（关联自身sys_dept_id，顶级部门填0）", example = "5865")
    private String parentId;

    @Schema(description = "部门编码")
    private String deptCode;

    @Schema(description = "状态（可选值：0-禁用/1-启用）", example = "1")
    private Integer status;

    @Schema(description = "排序号")
    private Integer sort;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}