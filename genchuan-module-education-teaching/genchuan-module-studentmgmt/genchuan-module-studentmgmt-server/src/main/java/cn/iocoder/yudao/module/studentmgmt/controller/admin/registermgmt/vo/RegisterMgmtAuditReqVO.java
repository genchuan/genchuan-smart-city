package cn.iocoder.yudao.module.studentmgmt.controller.admin.registermgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 报名管理审核 Request VO")
@Data
public class RegisterMgmtAuditReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1,2,4")
    private Long[] ids;

    @Schema(description = "审核人")
    private String auditUser;

    @Schema(description = "审核时间", example ="1776220634000")
    private LocalDateTime auditTime;

}