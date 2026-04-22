package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 无牌入场修正 Request VO")
@Data
public class UnplateEnterCorrectReqVO {

    @Schema(description = "记录主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "记录主键ID不能为空")
    private Long id;

    @Schema(description = "修正后车辆类型：小型车/中型车/大型车/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "中型车")
    @NotBlank(message = "车辆类型不能为空")
    private String carType;

    @Schema(description = "修正后车辆颜色", example = "黑色")
    private String carColor;

    @Schema(description = "修正后联系电话", requiredMode = Schema.RequiredMode.REQUIRED, example = "13987654321")
    @NotBlank(message = "联系电话不能为空")
    private String phone;

    @Schema(description = "场站ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "场站ID不能为空")
    private Long stationId;

    @Schema(description = "修正备注", example = "修正无牌车信息")
    private String remark;

}