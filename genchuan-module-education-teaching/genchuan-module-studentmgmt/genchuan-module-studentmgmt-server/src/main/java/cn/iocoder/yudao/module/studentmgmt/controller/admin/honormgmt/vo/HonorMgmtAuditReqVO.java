package cn.iocoder.yudao.module.studentmgmt.controller.admin.honormgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;


@Schema(description = "管理后台 - 荣誉管理推送 Request VO")
@Data
public class HonorMgmtAuditReqVO {
    @Schema(description = "审核备注")
    private String auditRemark;
    @Schema(description = "审核状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private String status;
    @Schema(description = "荣誉 ID 列表", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024,1025")
    private List<Long> ids;

}