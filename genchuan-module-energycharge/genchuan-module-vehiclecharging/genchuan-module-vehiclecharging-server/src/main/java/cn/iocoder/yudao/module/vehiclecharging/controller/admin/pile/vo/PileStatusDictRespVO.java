package cn.iocoder.yudao.module.vehiclecharging.controller.admin.pile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "汽车充电 - 充电桩状态字典 Response VO")
@Data
public class PileStatusDictRespVO {

    @Schema(description = "状态ID", example = "1")
    private String value;

    @Schema(description = "状态名称", example = "已启用")
    private String label;

}
