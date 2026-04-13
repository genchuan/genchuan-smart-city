package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderalarm.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Schema(description = "汽车充电 - 订单告警处理 Request VO")
@Data
public class OrderAlarmHandleReqVO {

    @Schema(description = "告警 ID 列表", requiredMode = Schema.RequiredMode.REQUIRED, example = "[1, 2, 3]")
    @NotEmpty(message = "告警 ID 列表不能为空")
    private List<Long> ids;

    @Schema(description = "处理措施", requiredMode = Schema.RequiredMode.REQUIRED, example = "协助用户重新启动充电流程，恢复充电服务")
    @NotEmpty(message = "处理措施不能为空")
    private String handleMeasure;

}