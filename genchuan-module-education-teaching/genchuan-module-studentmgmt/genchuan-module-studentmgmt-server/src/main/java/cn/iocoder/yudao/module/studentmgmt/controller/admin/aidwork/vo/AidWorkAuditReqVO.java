package cn.iocoder.yudao.module.studentmgmt.controller.admin.aidwork.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 奖助勤贷审核 Request VO")
@Data
public class AidWorkAuditReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19452")
    private Long id;


    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "审核结果（通过 / 驳回）")
    private String auditResult;

    @Schema(description = "备用字段 2")
    private String reserve2;

}