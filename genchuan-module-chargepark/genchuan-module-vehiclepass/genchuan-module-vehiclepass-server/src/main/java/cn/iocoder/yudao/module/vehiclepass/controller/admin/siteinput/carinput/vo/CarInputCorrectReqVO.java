package cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.carinput.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 车辆录入修正 Request VO")
@Data
public class CarInputCorrectReqVO {

    @Schema(description = "记录主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "记录主键ID不能为空")
    private Long id;

    @Schema(description = "修正后车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "车牌不能为空")
    private String plateNo;

    @Schema(description = "修正后车位ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "车位ID不能为空")
    private Long spaceId;

    @Schema(description = "片区ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "片区ID不能为空")
    private Long areaId;

    @Schema(description = "修正备注")
    private String remark;

}