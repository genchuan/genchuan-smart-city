package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangecategory.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 兑换类目分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExchangeCategoryPageReqVO extends PageParam {

    @Schema(description = "类目名称，支持模糊查询")
    private String name;

    @Schema(description = "类目状态（未生效/已生效/已禁用）")
    private String status;

    @Schema(description = "适用范围")
    private String scope;
}
