package cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 在停状态告警 Request VO")
@Data
public class InParkStatusAlarmReqVO {

    @Schema(description = "记录主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "记录主键ID不能为空")
    private Long id;

    @Schema(description = "告警内容", requiredMode = Schema.RequiredMode.REQUIRED, example = "您的车辆已超时长停放，请尽快驶离")
    @NotBlank(message = "告警内容不能为空")
    private String alarmContent;

}