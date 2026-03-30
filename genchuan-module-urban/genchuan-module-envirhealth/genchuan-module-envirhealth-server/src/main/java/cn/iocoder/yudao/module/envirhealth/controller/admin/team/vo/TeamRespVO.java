package cn.iocoder.yudao.module.envirhealth.controller.admin.team.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 班组 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TeamRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25199")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "24234")
    @ExcelProperty("业务主键（UUID）")
    private String sysTeamId;

    @Schema(description = "班组名称", example = "芋艿")
    @ExcelProperty("班组名称")
    private String name;

    @Schema(description = "所属部门（关联sys_dept.sys_dept_id）", example = "15172")
    @ExcelProperty("所属部门（关联sys_dept.sys_dept_id）")
    private String deptId;

    @Schema(description = "班组长（关联sys_user.id）", example = "159")
    @ExcelProperty("班组长（关联sys_user.id）")
    private String teamLeaderId;

    @Schema(description = "状态（可选值：0-禁用/1-启用）", example = "2")
    @ExcelProperty("状态（可选值：0-禁用/1-启用）")
    private Integer status;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "班组合影URL", example = "https://www.iocoder.cn")
    @ExcelProperty("班组合影URL")
    private String teamPhotoUrl;

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