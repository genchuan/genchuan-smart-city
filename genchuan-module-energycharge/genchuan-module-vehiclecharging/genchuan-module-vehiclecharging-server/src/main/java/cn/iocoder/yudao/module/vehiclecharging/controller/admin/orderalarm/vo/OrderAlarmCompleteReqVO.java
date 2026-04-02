package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderalarm.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 订单告警完结 Request VO")
@Data
public class OrderAlarmCompleteReqVO {

    @Schema(description = "告警 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "告警 ID 不能为空")
    private Long id;

}