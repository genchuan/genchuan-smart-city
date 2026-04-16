package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderalarm.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "汽车充电 - 订单告警备注 Request VO")
@Data
public class OrderAlarmRemarkReqVO {

    @Schema(description = "告警 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "告警 ID 不能为空")
    private Long id;

    @Schema(description = "备注内容", requiredMode = Schema.RequiredMode.REQUIRED, example = "用户充电过程中误触停止按钮导致中断，已协助恢复")
    @NotNull(message = "备注内容不能为空")
    private String remark;
}