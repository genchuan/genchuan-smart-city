package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.reserve.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 预约评价 Request VO")
@Data
public class ReserveListEvaluateReqVO {

    @Schema(description = "预约 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "预约 ID 不能为空")
    private Long id;

    @Schema(description = "评分 1-5", requiredMode = Schema.RequiredMode.REQUIRED, example = "5")
    @NotNull(message = "评分不能为空")
    @Min(value = 1, message = "评分最小为 1")
    @Max(value = 5, message = "评分最大为 5")
    private Integer score;

    @Schema(description = "评价内容")
    private String evaluateContent;

}
