package cn.iocoder.yudao.module.evaluate.controller.admin.baseinfo.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 系统用户新增/修改 Request VO")
@Data
public class UserSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17545")
    private Long id;

    @Schema(description = "用户ID（UUID，业务主键）", requiredMode = Schema.RequiredMode.REQUIRED, example = "15597")
    @NotEmpty(message = "用户ID（UUID，业务主键）不能为空")
    private String userId;

    @Schema(description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "用户名不能为空")
    private String userName;

    @Schema(description = "联系电话")
    private String userPhone;

    @Schema(description = "所属部门名称", example = "王五")
    private String deptName;

    @Schema(description = "角色ID（关联角色表主键）", example = "26780")
    private String roleId;

    @Schema(description = "状态ID（关联sys_status.status_id）", example = "896")
    private Integer statusId;

    @Schema(description = "创建人ID（关联sys_user.user_id）")
    private String createBy;

    @Schema(description = "更新人ID（关联sys_user.user_id）")
    private String updateBy;

    @Schema(description = "业务创建时间")
    private LocalDateTime bizCreateTime;

    @Schema(description = "业务更新时间")
    private LocalDateTime bizUpdateTime;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}