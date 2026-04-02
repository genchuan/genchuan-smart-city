package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderalarm.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Schema(description = "管理后台 - 订单告警处理 Request VO")
@Data
public class OrderAlarmHandleReqVO {

    @Schema(description = "告警 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotEmpty(message = "告警 ID 不能为空")
    private Long id;

    @Schema(description = "处理措施", requiredMode = Schema.RequiredMode.REQUIRED, example = "协助用户重新启动充电流程，恢复充电服务")
    @NotEmpty(message = "处理措施不能为空")
    private String handleMeasure;

}