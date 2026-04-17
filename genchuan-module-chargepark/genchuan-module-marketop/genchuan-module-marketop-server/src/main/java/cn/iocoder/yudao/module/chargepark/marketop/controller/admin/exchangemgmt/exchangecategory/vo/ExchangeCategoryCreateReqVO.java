package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangecategory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 兑换类目创建 Request VO")
@Data
public class ExchangeCategoryCreateReqVO {

    @Schema(description = "类目名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "类目名称不能为空")
    private String name;

    @Schema(description = "所需积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "所需积分不能为空")
    private Integer point;

    @Schema(description = "库存", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "库存不能为空")
    private Integer stock;

    @Schema(description = "备注")
    private String remark;

}
