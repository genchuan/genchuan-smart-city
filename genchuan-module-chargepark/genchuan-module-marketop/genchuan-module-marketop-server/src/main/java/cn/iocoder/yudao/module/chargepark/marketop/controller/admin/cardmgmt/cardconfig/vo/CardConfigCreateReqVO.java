package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 卡种配置创建 Request VO")
@Data
public class CardConfigCreateReqVO {

    @Schema(description = "卡种名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "卡种名称不能为空")
    private String name;

    @Schema(description = "卡种类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "卡种类型不能为空")
    private String type;

    @Schema(description = "适用范围", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "适用范围不能为空")
    private String scope;

    @Schema(description = "价格", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "价格不能为空")
    private BigDecimal price;

    @Schema(description = "有效天数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "有效天数不能为空")
    private Integer validDays;

    @Schema(description = "卡种描述")
    private String description;

    @Schema(description = "场站ID，逗号分隔")
    private String stationId;
}
