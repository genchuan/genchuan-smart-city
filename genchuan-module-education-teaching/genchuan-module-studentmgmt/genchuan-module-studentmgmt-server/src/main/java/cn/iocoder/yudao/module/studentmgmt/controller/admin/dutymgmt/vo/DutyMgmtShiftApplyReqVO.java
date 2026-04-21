package cn.iocoder.yudao.module.studentmgmt.controller.admin.dutymgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 调班申请 Request VO")
@Data
public class DutyMgmtShiftApplyReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "调班原因", requiredMode = Schema.RequiredMode.REQUIRED)
    private String transferReason;

    @Schema(description = "备注", example = "你猜")
    private String remark;


}