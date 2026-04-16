package cn.iocoder.yudao.module.vehiclecharging.controller.admin.pile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "汽车充电 - 充电桩充电模式统计 Response VO")
@Data
public class PileChargeModeStatRespVO {

    @Schema(description = "充电模式ID（1=直流，2=交流，3=交直流混合）", example = "1")
    private Long chargeMode;

    @Schema(description = "充电模式名称", example = "直流")
    private String chargeModeName;

    @Schema(description = "该类型的充电桩数量", example = "280")
    private Integer count;

}
