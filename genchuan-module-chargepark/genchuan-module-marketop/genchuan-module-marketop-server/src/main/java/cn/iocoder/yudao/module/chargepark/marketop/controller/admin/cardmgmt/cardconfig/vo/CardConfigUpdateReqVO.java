package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 卡种配置更新 Request VO")
@Data
public class CardConfigUpdateReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "主键ID不能为空")
    private Long id;

    @Schema(description = "卡种名称")
    private String name;

    @Schema(description = "适用范围")
    private String scope;

    @Schema(description = "价格")
    private BigDecimal price;

    @Schema(description = "有效天数")
    private Integer validDays;

    @Schema(description = "卡种描述")
    private String description;

}
