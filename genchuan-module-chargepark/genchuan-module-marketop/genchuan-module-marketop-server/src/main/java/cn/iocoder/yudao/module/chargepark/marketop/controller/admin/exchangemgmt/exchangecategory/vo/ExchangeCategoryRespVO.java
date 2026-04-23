package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangecategory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 兑换类目 Response VO")
@Data
public class ExchangeCategoryRespVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "类目名称")
    private String name;

    @Schema(description = "类目描述")
    private String description;

    @Schema(description = "商品数量")
    private Integer goodsCount;

    @Schema(description = "类目状态(未生效/已生效/已禁用)")
    private String status;

    @Schema(description = "审核人")
    private Long auditorId;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "生效时间")
    private LocalDateTime effectTime;

    @Schema(description = "排序权重")
    private Integer sort;

    @Schema(description = "适用范围(全平台/指定场站)")
    private String scope;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}
