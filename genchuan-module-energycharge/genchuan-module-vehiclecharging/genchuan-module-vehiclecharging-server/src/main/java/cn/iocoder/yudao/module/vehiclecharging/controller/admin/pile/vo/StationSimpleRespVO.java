package cn.iocoder.yudao.module.vehiclecharging.controller.admin.pile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "汽车充电 - 场站简易列表 Response VO")
@Data
public class StationSimpleRespVO {

    @Schema(description = "场站ID", example = "1")
    private Long value;

    @Schema(description = "场站名称", example = "深圳南山充电站")
    private String label;
}
