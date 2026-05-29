package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import cn.iocoder.yudao.module.vehiclepass.constants.common.PhoneConstants;

@Schema(description = "管理后台 - 无牌入场新增 Request VO")
@Data
public class UnplateEnterCreateReqVO {

    @Schema(description = "车辆类型：小型车/中型车/大型车/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "小型车")
    @NotBlank(message = "车辆类型不能为空")
    private String carType;

    @Schema(description = "车辆颜色", example = "白色")
    private String carColor;

    @Schema(description = "联系电话", requiredMode = Schema.RequiredMode.REQUIRED, example = "13812345678")
    @NotBlank(message = "联系电话不能为空")
    @Pattern(regexp = PhoneConstants.PHONE_REGEX, message = PhoneConstants.PHONE_REGEX_MESSAGE)
    private String phone;

    @Schema(description = "场站ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "场站ID不能为空")
    private Long stationId;

    @Schema(description = "备注", example = "无牌车入场登记")
    private String remark;

}