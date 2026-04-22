package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangecategory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Schema(description = "管理后台 - 兑换类目创建 Request VO")
@Data
public class ExchangeCategoryCreateReqVO {

    @Schema(description = "类目名称(唯一)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "类目名称不能为空")
    private String name;

    @Schema(description = "适用范围(全平台/指定场站)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "适用范围不能为空")
    private String scope;

    @Schema(description = "排序权重")
    private Integer sort;

    @Schema(description = "类目描述")
    private String description;

}
