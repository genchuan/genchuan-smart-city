package cn.iocoder.yudao.module.inspectop.controller.admin.devicemonitor.bikechargemonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;

@Schema(description = "巡查巡检 - 两轮充电监测定位 Response VO")
@Data
public class BikeChargeMonitorLocationRespVO {

    @Schema(description = "经度", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal longitude;

    @Schema(description = "纬度", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal latitude;

    @Schema(description = "场站名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String stationName;

    @Schema(description = "设备编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String deviceCode;

}