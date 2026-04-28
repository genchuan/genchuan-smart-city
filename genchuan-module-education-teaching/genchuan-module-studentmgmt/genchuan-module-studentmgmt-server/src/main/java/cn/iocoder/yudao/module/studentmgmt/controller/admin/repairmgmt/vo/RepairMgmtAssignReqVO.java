package cn.iocoder.yudao.module.studentmgmt.controller.admin.repairmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 派单 Request VO")
@Data
public class RepairMgmtAssignReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5870")
    private Long[] ids;

    @Schema(description = "维修人")
    private String repairUser;

    @Schema(description = "备注", example = "你猜")
    private String remark;

}