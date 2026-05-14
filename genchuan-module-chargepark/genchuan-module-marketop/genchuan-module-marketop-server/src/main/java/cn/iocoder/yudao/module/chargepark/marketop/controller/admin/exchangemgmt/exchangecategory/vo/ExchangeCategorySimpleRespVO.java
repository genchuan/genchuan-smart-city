package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangecategory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 兑换类目精简信息 Response VO")
@Data
public class ExchangeCategorySimpleRespVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "类目名称")
    private String name;

}
