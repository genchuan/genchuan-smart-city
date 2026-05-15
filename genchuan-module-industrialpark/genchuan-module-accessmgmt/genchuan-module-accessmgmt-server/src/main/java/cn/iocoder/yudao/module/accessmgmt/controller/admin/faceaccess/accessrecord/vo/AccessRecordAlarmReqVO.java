package cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.accessrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 异常告警 Request VO")
@Data
public class AccessRecordAlarmReqVO {

    @Schema(description = "通行记录ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "通行记录ID不能为空")
    private Long id;

    @Schema(description = "告警内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "告警内容不能为空")
    private String alarmContent;

}
