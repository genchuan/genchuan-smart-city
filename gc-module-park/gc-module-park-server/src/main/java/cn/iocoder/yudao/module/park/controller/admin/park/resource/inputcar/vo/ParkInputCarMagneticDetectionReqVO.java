package cn.iocoder.yudao.module.park.controller.admin.park.resource.inputcar.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;


@Schema(description = "管理后台 - 地磁检测模拟车辆进入 Request VO")
@Data
public class ParkInputCarMagneticDetectionReqVO {

    @Schema(description = "目标泊位号", required = true, example = "A001")
    @NotBlank(message = "目标泊位号不能为空")
    private String targetBerthNo;

    @Schema(description = "入场时间", required = true)
    @NotNull(message = "入场时间不能为空")
    private LocalDateTime entryTime;
}