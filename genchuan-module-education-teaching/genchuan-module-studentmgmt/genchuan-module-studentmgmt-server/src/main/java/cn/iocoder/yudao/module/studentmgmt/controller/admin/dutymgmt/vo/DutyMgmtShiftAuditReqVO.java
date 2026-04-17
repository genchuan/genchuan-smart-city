package cn.iocoder.yudao.module.studentmgmt.controller.admin.dutymgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 调班、出车审批 Request VO")
@Data
public class DutyMgmtShiftAuditReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "审批结果（通过 / 驳回）", requiredMode = Schema.RequiredMode.REQUIRED)
    private String auditResult;

    @Schema(description = "备注", example = "你猜")
    private String remark;


}