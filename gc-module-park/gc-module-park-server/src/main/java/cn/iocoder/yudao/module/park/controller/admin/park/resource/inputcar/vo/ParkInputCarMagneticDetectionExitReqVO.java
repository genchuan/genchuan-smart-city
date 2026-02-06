package cn.iocoder.yudao.module.park.controller.admin.park.resource.inputcar.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 地磁检测模拟车辆离场 Request VO")
@Data
public class ParkInputCarMagneticDetectionExitReqVO {

    @Schema(description = "车辆记录ID", required = true, example = "1")
    @NotNull(message = "车辆记录ID不能为空")
    private Long id;

    @Schema(description = "离场时间", required = true)
    @NotNull(message = "离场时间不能为空")
    private LocalDateTime exitTime;
}
