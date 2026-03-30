package cn.iocoder.yudao.module.facility.controller.admin.sysuser.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 系统用户分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SysUserPageReqVO extends PageParam {

    @Schema(description = "[用户名称]用户姓名", example = "李四")
    private String nickname;

    @Schema(description = "[唯一用户名] ", example = "李四")
    private String username;

    @Schema(description = "[登录账号] 登录账号，需保证业务唯一，必填", example = "10970")
    private String accountId;

    @Schema(description = "[角色ID] 用户角色ID，关联sys_role.id，必填", example = "13868")
    private Long roleId;

    @Schema(description = "[部门ID] 所属部门ID，关联sys_dept.id", example = "32084")
    private Long deptId;

    @Schema(description = "[联系电话] 联系电话")
    private String phone;

    @Schema(description = "[账号状态]如:启用/停用", example = "2")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间，自动生成")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

}
