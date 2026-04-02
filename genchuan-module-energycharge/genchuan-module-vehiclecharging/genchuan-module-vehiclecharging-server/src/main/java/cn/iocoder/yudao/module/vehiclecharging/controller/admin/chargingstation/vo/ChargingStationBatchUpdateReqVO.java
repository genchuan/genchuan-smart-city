package cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


import java.util.List;

@Schema(description = "充电站 - 批量编辑合作模式和负责人 Request VO")
@Data
public class ChargingStationBatchUpdateReqVO {

    @Schema(description = "场站ID列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "场站ID列表不能为空")
    private List<Long> ids;

    @Schema(description = "合作模式")
    private String coopMode;

    @Schema(description = "负责人")
    private String manager;
}