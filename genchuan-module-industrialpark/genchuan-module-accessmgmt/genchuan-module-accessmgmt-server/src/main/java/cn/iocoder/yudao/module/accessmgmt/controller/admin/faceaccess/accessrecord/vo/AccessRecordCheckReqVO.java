package cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.accessrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 异常核查 Request VO")
@Data
public class AccessRecordCheckReqVO {

    @Schema(description = "通行记录ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "通行记录ID不能为空")
    private Long id;

    @Schema(description = "核查结果", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "核查结果不能为空")
    private String checkResult;

}
