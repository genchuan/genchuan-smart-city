package cn.iocoder.yudao.module.studentmgmt.controller.admin.dutymgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 出车申请 Request VO")
@Data
public class DutyMgmtVehicleApplyReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long[] ids;

    @Schema(description = "出车事由）", requiredMode = Schema.RequiredMode.REQUIRED, example = "出差")
    private String carReason;

    @Schema(description = "出车目的地", requiredMode = Schema.RequiredMode.REQUIRED, example = "北京")
    private String carDestination;


}