package cn.iocoder.yudao.module.vehiclecharging.controller.admin.charginglot.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "汽车充电 - 更新充电车位状态 Request VO")
@Data
public class ChargingLotUpdateStatusReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "编号不能为空")
    private Long id;

    @Schema(description = "占用时长（分钟），状态为“占用”时必填")
    private Integer occupyTime;

    @Schema(description = "车位状态（0-空闲，1-占用，2-维护中）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "车位状态不能为空")
    private String lotStatus;
}