package cn.iocoder.yudao.module.vehiclecharging.controller.admin.abnormalorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Schema(description = "异常订单 - 处理 Request VO")
@Data
public class AbnormalOrderHandleReqVO {

    @Schema(description = "异常订单ID集合", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "异常订单ID不能为空")
    private List<Long> ids;

    @Schema(description = "处理措施", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "处理措施不能为空")
    private String handleMeasure;
}