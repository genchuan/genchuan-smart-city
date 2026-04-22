package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangecategory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 兑换类目更新 Request VO")
@Data
public class ExchangeCategoryUpdateReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "id不能为空")
    private Long id;

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
