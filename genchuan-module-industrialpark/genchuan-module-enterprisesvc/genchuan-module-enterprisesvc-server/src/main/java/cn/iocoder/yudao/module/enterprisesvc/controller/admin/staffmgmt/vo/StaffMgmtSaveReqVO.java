package cn.iocoder.yudao.module.enterprisesvc.controller.admin.staffmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 企业员工新增/修改 Request VO")
@Data
public class StaffMgmtSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "员工姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "员工姓名不能为空")
    private String staffName;

    @Schema(description = "企业ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "企业ID不能为空")
    private Long enterpriseId;

    @Schema(description = "所属部门")
    private String deptName;

    @Schema(description = "岗位")
    private String postName;

    @Schema(description = "权限状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "权限状态不能为空")
    private String authStatus;

    @Schema(description = "通行区域")
    private String accessArea;

    @Schema(description = "授权人账号")
    private String authUser;

    @Schema(description = "操作人账号")
    private String handleUser;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}