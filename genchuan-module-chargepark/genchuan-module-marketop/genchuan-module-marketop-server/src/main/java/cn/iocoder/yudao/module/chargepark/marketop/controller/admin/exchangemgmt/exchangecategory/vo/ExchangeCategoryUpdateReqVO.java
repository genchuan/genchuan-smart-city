package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangecategory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 兑换类目更新 Request VO")
@Data
public class ExchangeCategoryUpdateReqVO {

    @Schema(description = "兑换类目ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "兑换类目ID不能为空")
    private Long id;

    @Schema(description = "类目名称")
    private String name;

    @Schema(description = "所需积分")
    private Integer point;

    @Schema(description = "库存")
    private Integer stock;

    @Schema(description = "备注")
    private String remark;

}
