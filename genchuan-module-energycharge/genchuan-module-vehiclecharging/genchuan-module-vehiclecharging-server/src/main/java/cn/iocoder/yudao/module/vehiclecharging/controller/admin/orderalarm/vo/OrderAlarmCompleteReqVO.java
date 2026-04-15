package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderalarm.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Schema(description = "汽车充电 - 订单告警完结 Request VO")
@Data
public class OrderAlarmCompleteReqVO {

    @Schema(description = "告警 ID 列表", requiredMode = Schema.RequiredMode.REQUIRED, example = "[1, 2, 3]")
    @NotNull(message = "告警 ID 列表不能为空")
    private List<Long> ids;

}