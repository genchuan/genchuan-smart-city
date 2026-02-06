package cn.iocoder.yudao.module.park.controller.admin.park.resource.inputcar.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 车辆进场 Request VO")
@Data
public class ParkInputCarEntryReqVO {

    @Schema(description = "主键ID", required = true, example = "1024")
    @NotNull(message = "主键ID不能为空")
    private Long id;

    @Schema(description = "车场ID", required = true, example = "1")
    @NotNull(message = "车场ID不能为空")
    private String parkId;

    @Schema(description = "目标泊位编号", required = true, example = "A001")
    @NotBlank(message = "目标泊位编号不能为空")
    private String targetBerthNo;

    @Schema(description = "车牌号", required = true, example = "浙A12345")
    @NotBlank(message = "车牌号不能为空")
    private String carNumber;

    @Schema(description = "车辆类型", example = "小型汽车")
    private String carType;

    @Schema(description = "车牌颜色", example = "白色")
    private String plateColor;

    @Schema(description = "车辆照片")
    private String extCommon1;

//    @Schema(description = "入场时间", required = true)
//    @NotNull(message = "入场时间不能为空")
//    private LocalDateTime entryTime;
}
