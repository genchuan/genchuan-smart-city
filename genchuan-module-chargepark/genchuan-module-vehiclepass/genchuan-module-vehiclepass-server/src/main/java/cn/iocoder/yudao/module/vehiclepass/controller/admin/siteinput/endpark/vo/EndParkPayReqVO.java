package cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.endpark.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Schema(description = "管理后台 - 结束停车支付 Request VO")
@Data
public class EndParkPayReqVO {

    @Schema(description = "记录主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "记录主键ID不能为空")
    private Long id;

}