package cn.iocoder.yudao.module.facility.controller.admin.sysuser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 系统用户新增/修改 Request VO")
@Data
public class SysUserSaveReqVO {

    @Schema(description = "[主键ID] 用户唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "2024")
    private Long id;

    @Schema(description = "[用户名称]用户姓名", example = "李四")
    private String nickname;

    @Schema(description = "[唯一用户名] ", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "[唯一用户名] 不能为空")
    private String username;

    @Schema(description = "[登录账号] 登录账号，需保证业务唯一，必填", requiredMode = Schema.RequiredMode.REQUIRED, example = "10970")
    @NotEmpty(message = "[登录账号] 登录账号，需保证业务唯一，必填不能为空")
    private String accountId;

    @Schema(description = "[角色ID] 用户角色ID，关联sys_role.id，必填", requiredMode = Schema.RequiredMode.REQUIRED, example = "13868")
    @NotNull(message = "[角色ID] 用户角色ID，关联sys_role.id，必填不能为空")
    private Long roleId;

    @Schema(description = "[部门ID] 所属部门ID，关联sys_dept.id", example = "32084")
    private Long deptId;

    @Schema(description = "[联系电话] 联系电话")
    private String phone;

    @Schema(description = "[账号状态]如:启用/停用", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "[账号状态]如:启用/停用不能为空")
    private String status;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

}
