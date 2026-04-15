package cn.iocoder.yudao.module.vehiclecharging.controller.admin.pile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "汽车充电 - 车位简易列表 Response VO")
@Data
public class LotSimpleRespVO {

    @Schema(description = "车位ID", example = "1")
    private Long value;

    @Schema(description = "车位编号", example = "LOT-001")
    private String label;
}
