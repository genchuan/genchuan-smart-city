package cn.iocoder.yudao.module.park.controller.admin.park.resource.inputcar.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 车辆离场 Request VO")
@Data
public class ParkInputCarExitReqVO {

    @Schema(description = "主键ID", required = true, example = "1024")
    @NotNull(message = "主键ID不能为空")
    private Long id;

    @Schema(description = "出场时间", required = true)
    @NotNull(message = "出场时间不能为空")
    private LocalDateTime exitTime;
}