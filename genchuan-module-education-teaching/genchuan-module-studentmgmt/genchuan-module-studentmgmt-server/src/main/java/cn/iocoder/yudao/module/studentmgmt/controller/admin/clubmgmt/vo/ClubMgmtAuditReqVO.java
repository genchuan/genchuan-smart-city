package cn.iocoder.yudao.module.studentmgmt.controller.admin.clubmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Schema(description = "管理后台 - 社团管理审核 Request VO")
@Data
public class ClubMgmtAuditReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23257")
    private Long id;

    @Schema(description = "状态：待审核/已通过/已建档", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "状态：待审核/已通过/已建档不能为空")
    private String status;

    @Schema(description = "审核备注")
    private String auditRemark;

}