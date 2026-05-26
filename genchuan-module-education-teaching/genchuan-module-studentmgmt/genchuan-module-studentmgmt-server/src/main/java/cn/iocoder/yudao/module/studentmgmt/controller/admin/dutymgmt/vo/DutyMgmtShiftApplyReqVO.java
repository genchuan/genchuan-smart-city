package cn.iocoder.yudao.module.studentmgmt.controller.admin.dutymgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 调班申请 Request VO")
@Data
public class DutyMgmtShiftApplyReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long[] ids;

    @Schema(description = "调班原因", requiredMode = Schema.RequiredMode.REQUIRED)
    private String transferReason;

    @Schema(description = "调班替代人", requiredMode = Schema.RequiredMode.REQUIRED)
    private String transferUser;



}