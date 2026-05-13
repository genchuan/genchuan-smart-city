package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.offtimerule.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 错时规则创建 Request VO")
@Data
public class OfftimeRuleCreateReqVO {

    @Schema(description = "所属场站ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "所属场站不能为空")
    private Long stationId;

    @Schema(description = "空闲时段", requiredMode = Schema.RequiredMode.REQUIRED, example = "22:00-08:00")
    @NotEmpty(message = "空闲时段不能为空")
    private String offTime;

    @Schema(description = "错时费率", requiredMode = Schema.RequiredMode.REQUIRED, example = "0.80")
    @NotNull(message = "错时费率不能为空")
    private BigDecimal offFee;

    @Schema(description = "备注", example = "夜间错时优惠")
    private String remark;

    @Schema(description = "备用字段1", example = "备用信息1")
    private String reserve1;

    @Schema(description = "备用字段2", example = "备用信息2")
    private String reserve2;

}
