package cn.iocoder.yudao.module.studentmgmt.controller.admin.accessapply.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 审核 Request VO")
@Data
public class AccessApplyAuditReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private Long[] ids;


    @Schema(description = "备注", example = "随便")
    private String remark;


}