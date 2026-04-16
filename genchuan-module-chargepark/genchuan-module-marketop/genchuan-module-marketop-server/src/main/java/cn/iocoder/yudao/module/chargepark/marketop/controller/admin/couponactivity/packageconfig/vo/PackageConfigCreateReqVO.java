package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.packageconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 券包配置创建 Request VO")
@Data
public class PackageConfigCreateReqVO {

    @Schema(description = "券包名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "券包名称不能为空")
    private String name;

    @Schema(description = "券包类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "券包类型不能为空")
    private String type;

    @Schema(description = "包含优惠券ID列表")
    private String couponIds;

    @Schema(description = "价格", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "价格不能为空")
    private BigDecimal price;

    @Schema(description = "券包描述")
    private String description;

    @Schema(description = "适用范围")
    private String scope;

}
