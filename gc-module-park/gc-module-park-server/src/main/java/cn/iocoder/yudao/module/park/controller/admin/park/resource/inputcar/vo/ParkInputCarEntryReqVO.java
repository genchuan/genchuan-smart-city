package cn.iocoder.yudao.module.park.controller.admin.park.resource.inputcar.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 车辆进场 Request VO")
@Data
public class ParkInputCarEntryReqVO {

    @Schema(description = "目标泊位编号", required = true, example = "A001")
    @NotBlank(message = "目标泊位编号不能为空")
    private String targetBerthNo;

    @Schema(description = "车牌号", required = true, example = "浙A12345")
    @NotBlank(message = "车牌号不能为空")
    private String carNumber;

    @Schema(description = "车辆类型", example = "1")
    private Integer carType;

    @Schema(description = "车牌颜色", example = "1")
    private Integer plateColor;

    @Schema(description = "入场时间", required = true)
    @NotNull(message = "入场时间不能为空")
    private LocalDateTime entryTime;
}