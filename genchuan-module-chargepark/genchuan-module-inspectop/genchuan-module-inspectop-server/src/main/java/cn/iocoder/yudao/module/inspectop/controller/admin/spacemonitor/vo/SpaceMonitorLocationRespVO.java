package cn.iocoder.yudao.module.inspectop.controller.admin.spacemonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;

@Schema(description = "巡查巡检 - 车位状态监测定位 Response VO")
@Data
public class SpaceMonitorLocationRespVO {

    @Schema(description = "经度", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal longitude;

    @Schema(description = "纬度", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal latitude;

    @Schema(description = "场站名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String stationName;

    @Schema(description = "车位编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String spaceCode;
}