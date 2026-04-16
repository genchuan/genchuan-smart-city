package cn.iocoder.yudao.module.vehiclecharging.controller.admin.abnormalorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Schema(description = "异常订单 - 核实 Request VO")
@Data
public class AbnormalOrderVerifyReqVO {

    @Schema(description = "异常订单ID集合", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "异常订单ID不能为空")
    private List<Long> ids;

    @Schema(description = "核实结果：正常/异常/误报", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "核实结果不能为空")
    private String verifyResult;

    @Schema(description = "核实备注")
    private String verifyRemark;
}