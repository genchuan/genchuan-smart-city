package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.packageconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 券包配置更新 Request VO")
@Data
public class PackageConfigUpdateReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "主键ID不能为空")
    private Long id;

    @Schema(description = "券包名称")
    private String name;

    @Schema(description = "包含优惠券ID列表")
    private String couponIds;

    @Schema(description = "价格")
    private BigDecimal price;

    @Schema(description = "券包描述")
    private String description;

    @Schema(description = "适用范围")
    private String scope;

}
