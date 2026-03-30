package cn.iocoder.yudao.module.facility.controller.admin.sysuser.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 系统用户 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SysUserRespVO {

    @Schema(description = "[主键ID] 用户唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "2024")
    @ExcelProperty("[主键ID] 用户唯一标识")
    private Long id;

    @Schema(description = "[用户名称]用户姓名", example = "李四")
    @ExcelProperty("[用户名称]用户姓名")
    private String nickname;

    @Schema(description = "[唯一用户名] ", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("[唯一用户名] ")
    private String username;

    @Schema(description = "[登录账号] 登录账号，需保证业务唯一，必填", requiredMode = Schema.RequiredMode.REQUIRED, example = "10970")
    @ExcelProperty("[登录账号] 登录账号，需保证业务唯一，必填")
    private String accountId;

    @Schema(description = "[角色ID] 用户角色ID，关联sys_role.id，必填", requiredMode = Schema.RequiredMode.REQUIRED, example = "13868")
    @ExcelProperty("[角色ID] 用户角色ID，关联sys_role.id，必填")
    private Long roleId;

    @Schema(description = "[部门ID] 所属部门ID，关联sys_dept.id", example = "32084")
    @ExcelProperty("[部门ID] 所属部门ID，关联sys_dept.id")
    private Long deptId;

    @Schema(description = "[联系电话] 联系电话")
    @ExcelProperty("[联系电话] 联系电话")
    private String phone;

    @Schema(description = "[账号状态]如:启用/停用", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("[账号状态]如:启用/停用")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间，自动生成")
    @ExcelProperty("[创建时间] 记录创建时间，自动生成")
    private LocalDateTime createTime;

    @Schema(description = "[通用扩展字段1]")
    @ExcelProperty("[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    @ExcelProperty("[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    @ExcelProperty("[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    @ExcelProperty("[通用扩展字段4]")
    private String extCommon4;

}
