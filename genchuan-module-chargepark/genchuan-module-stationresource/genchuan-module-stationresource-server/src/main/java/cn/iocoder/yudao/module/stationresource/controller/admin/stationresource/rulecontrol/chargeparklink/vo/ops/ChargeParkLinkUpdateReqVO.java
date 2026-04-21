package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.chargeparklink.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 充停联动更新 Request VO")
@Data
public class ChargeParkLinkUpdateReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "主键ID不能为空")
    private Long id;

    @Schema(description = "所属场站ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "所属场站不能为空")
    private Long stationId;

    @Schema(description = "优惠类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "停车减免")
    @NotEmpty(message = "优惠类型不能为空")
    private String discountType;

    @Schema(description = "优惠幅度 %", requiredMode = Schema.RequiredMode.REQUIRED, example = "100.00")
    @NotNull(message = "优惠幅度不能为空")
    private BigDecimal discount;

    @Schema(description = "适用车型", requiredMode = Schema.RequiredMode.REQUIRED, example = "新能源车")
    @NotEmpty(message = "适用车型不能为空")
    private String carType;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;
}
