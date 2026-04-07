package cn.iocoder.yudao.module.vehiclecharging.controller.admin.abnormalorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "异常订单完结请求")
public class AbnormalOrderCompleteReqVO {

    @Schema(description = "异常订单ID集合", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "ids 不能为空")
    private List<Long> ids;

    @Schema(description = "完结备注")
    private String completeRemark;
}