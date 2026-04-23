package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.couponmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 优惠券创建 Request VO")
@Data
public class CouponMgmtCreateReqVO {

    @Schema(description = "券名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "券名称不能为空")
    private String name;

    @Schema(description = "券类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "券类型不能为空")
    private String type;

    @Schema(description = "面额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "面额不能为空")
    private BigDecimal amount;

    @Schema(description = "使用条件")
    private String useCondition;

    @Schema(description = "有效期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "有效期不能为空")
    private LocalDateTime validTime;

    @Schema(description = "券描述")
    private String description;

    @Schema(description = "适用场站")
    private String stationIds;

}
