package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.receiverecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 领用记录核查 Request VO")
@Data
public class ReceiveRecordCheckReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "id不能为空")
    private Long id;

    @Schema(description = "核查结果", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "核查结果不能为空")
    private String checkResult;

}
