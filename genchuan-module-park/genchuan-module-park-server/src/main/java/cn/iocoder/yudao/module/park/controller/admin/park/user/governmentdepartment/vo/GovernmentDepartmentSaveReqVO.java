package cn.iocoder.yudao.module.park.controller.admin.park.user.governmentdepartment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Schema(description = "管理后台 - 政府部门新增/修改 Request VO")
@Data
public class GovernmentDepartmentSaveReqVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "12283")
    private Long id;

    @Schema(description = "[部门名称]", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "[部门名称]不能为空")
    private String deptName;

    @Schema(description = "[部门编码]")
    private String deptCode;

    @Schema(description = "[联系人]")
    private String contactPerson;

    @Schema(description = "[联系电话]")
    private String contactPhone;

    @Schema(description = "[负责区域编码] 关联park_area.area_code")
    private String regionCode;

    @Schema(description = "[职责范围]")
    private String responsibility;

    @Schema(description = "[备注]", example = "你说的对")
    private String remark;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

}
