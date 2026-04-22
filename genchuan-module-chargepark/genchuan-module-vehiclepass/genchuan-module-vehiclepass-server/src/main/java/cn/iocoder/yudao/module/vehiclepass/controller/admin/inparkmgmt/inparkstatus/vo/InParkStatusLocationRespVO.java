package cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 在停状态定位 Response VO")
@Data
public class InParkStatusLocationRespVO {

    @Schema(description = "车位经度")
    private BigDecimal lon;

    @Schema(description = "车位纬度")
    private BigDecimal lat;

    @Schema(description = "车位名称")
    private String spaceName;

    @Schema(description = "场站名称")
    private String stationName;

}